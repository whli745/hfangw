/**
 * jQuery :  省市县联动插件
 * @author   kxt
 * @example  $("#test").province_city_county();
 */
$.fn.province_city_county = function(v_province,v_city,v_county){
	var _self = this;
	//插入3个空的下拉框
    _self.html("<select id='province' name='sm.province' style='width: 110px'></select>" +
    		"<select id='city' name='sm.city' style='width: 100px'></select>" +
    		"<select id='county' name='sm.county' style='width: 100px'></select>");
	//分别获取3个下拉框
	var sel1 = $('#province');
	var sel2 = $('#city');
	var sel3 = $('#county');
	
	//定义3个默认值
	_self.data("province",["请选择", ""]);
	_self.data("city",["请选择", ""]);
	_self.data("county",["请选择", ""]);
	//默认省级下拉
	if(_self.data("province")){
		sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");
	}
	//默认城市下拉
	if(_self.data("city")){
		sel2.append("<option value='"+_self.data("city")[1]+"'>"+_self.data("city")[0]+"</option>");
	}
	//默认县区下拉
	if(_self.data("county")){
		sel3.append("<option value='"+_self.data("county")[1]+"'>"+_self.data("county")[0]+"</option>");
	}
	$.get('xml/province_city.xml', function(data){
		var arrList = [];
		$(data).find('province').each(function(){
			var $province = $(this);
			sel1.append("<option value='"+$province.attr('value')+"'>"+$province.attr('value')+"</option>");
		});
		if(typeof v_province != 'undefined'){
			sel1.val(v_province);
			sel1.change();
		}
	});
	
	var index1 = "" ;
	var provinceValue = "";
	var cityValue = "";
	sel1.change(function(){
		//清空其它2个下拉框
		sel2[0].options.length=0;
		sel3[0].options.length=0;
		index1 = this.selectedIndex;
		if(index1 == 0){	//当选择的为 "请选择" 时
			if(_self.data("city")){
				sel2.append("<option value='"+_self.data("city")[1]+"'>"+_self.data("city")[0]+"</option>");
			}
			if(_self.data("county")){
				sel3.append("<option value='"+_self.data("county")[1]+"'>"+_self.data("county")[0]+"</option>");
			}
		} else{
			provinceValue = $('#province').val();
			$.get('xml/province_city.xml', function(data){
				$(data).find("province[value='"+provinceValue+"'] > city").each(function(){
					var $city = $(this);
					sel2.append("<option value='"+$city.attr('value')+"'>"+$city.attr('value')+"</option>");
				});
				cityValue = $("#city").val();
				$(data).find("city[value='"+cityValue+"'] > county").each(function(){
					var $county = $(this);
					sel3.append("<option value='"+$county.attr('value')+"'>"+$county.attr('value')+"</option>");
				});

                if(typeof v_city != 'undefined'){
                    sel2.val(v_city);
                    sel2.change();
                }

                if(typeof v_county != 'undefined'){
                    sel3.val(v_county);
                }
			});
		}
	}).change();
	sel2.change(function(){
		sel3[0].options.length=0;
		var cityValue2 = $('#city').val();
		$.get('xml/province_city.xml', function(data){
			$(data).find("city[value='"+cityValue2+"'] > county").each(function(){
				var $county = $(this);
				sel3.append("<option value='"+$county.attr('value')+"'>"+$county.attr('value')+"</option>");
			});
            if(typeof v_county != 'undefined'){
            	sel3.val(v_county);
            }
		});
	}).change();
	return _self;
};