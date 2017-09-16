<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<style type="text/css">
			body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
			#l-map{height:300px;width:100%;}
			#r-result{width:100%;}
		</style>
		<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=xDZQG2UYRx0tKp7zD7sN7LYH6WEVkk41"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />  
	</head>
	<body style="margin: 0;padding: 0;overflow: hidden;">
	<div id="r-result" style="margin: 3px auto;">
		&nbsp;请输入：<input type="text" id="suggestId" size="20" value="" style="width:250px;border: 1px solid #E4E6E7;height: 20px;line-height: 20px;" />
		<input type="button" value="查询" onclick="searchKeywords();"
			style="width: 40px;height: 23px;line-height:23px;;border: 0;text-align: center;background-color: #FF6600;font-family:'Microsoft YaHei','Simsun';color: #fff; cursor: pointer;padding: 0;margin: 0 auto;"/>
	</div>
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:300px; display:none;overflow: scroll;"></div>
	<div id="l-map" style="height: 90%;"></div>
	<script type="text/javascript">
		// 百度地图API功能
		function G(id) {
			return document.getElementById(id);
		}
	
		var map = new BMap.Map("l-map");
		map.centerAndZoom(new BMap.Point(117.238322, 31.823434),12);                   // 初始化地图,设置城市和地图级别。
		map.enableScrollWheelZoom(true);
		var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_RIGHT});   //设置版权控件位置
		map.addControl(cr); //添加版权控件
		var bs = map.getBounds();   //返回地图可视区域
		cr.addCopyright({id: 1, content: "<span style='font-size:14px;background:#FF6600;color:#fff;padding:1px 3px;font-family:\'Microsoft YaHei\';'>华房网</span>", bounds: bs});
		
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
		/*缩放控件type有四种类型:
		BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
// 		map.addControl(top_right_navigation); 
		
		//单击获取点击的经纬度
		map.addEventListener("mousedown",function(e){
			var we=window.event;  
			if(we.button ==2){
				map.clearOverlays();    //清除地图上所有覆盖物
				var pt = new BMap.Point(e.point.lng, e.point.lat);
				var myIcon = new BMap.Icon("./buidingmanage/images/pin_blue_solid_23.148936170213px_1129770_easyicon.net.png", new BMap.Size(32,32));
				var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				marker.enableDragging();
// 				marker.addEventListener("click",getAttr);
			}
		});
	
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
			,"location" : map
		});
	
		ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			
			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
			G("searchResultPanel").innerHTML = str;
		});
	
		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			
			setPlace();
		});
	
		function setPlace(){
			map.clearOverlays();    //清除地图上所有覆盖物
			local = new BMap.LocalSearch(map, { //智能搜索
			  onSearchComplete: myFun
			});
			local.search(myValue);
		}
		var point = new Array(); //存放标注点经纬信息的数组
        var marker = new Array(); //存放标注点对象的数组
        var info = new Array(); //存放提示信息窗口对象的数组
        var searchInfoWindow =new Array();//存放检索信息窗口对象的数组
		var local;
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			var t_p = local.getResults().getCurrentNumPois();
			for(var i=0;i<t_p;i++){
				pp = local.getResults().getPoi(i).point;
				point[i] = pp;
				var poi = local.getResults().getPoi(i);
				var m = new BMap.Marker(pp);  // 创建标注
				marker[i] = m;
// 				marker.setTitle(poi.title);
// 				var html='<span style=\'font-weight:bolder;\'>'+poi.title+'</span><br/>';
// 				html+='地址：'+poi.address;
// 				marker.setLabel(new BMap.Label(html));
				map.addOverlay(m);              // 将标注添加到地图中
// 				marker.addEventListener("click",showInfo);
				
                // 创建信息窗口对象
                info[i] = "<p style='font-size:12px;lineheight:1.8em;'>" + "</br>地址：" + poi.address + "</br></p>";
                //创建百度样式检索信息窗口对象                       
                searchInfoWindow[i] = new BMapLib.SearchInfoWindow(map, info[i], {
                        title  : poi.title,      //标题
                        width  : 290,             //宽度
                        height : 55,              //高度
                        panel  : "panel",         //检索结果面板
                        enableAutoPan : true,     //自动平移
                        searchTypes   :[
                            BMAPLIB_TAB_SEARCH,   //周边检索
                            BMAPLIB_TAB_TO_HERE,  //到这里去
                            BMAPLIB_TAB_FROM_HERE //从这里出发
                        ]
                    });
                
                //添加点击事件
                marker[i].addEventListener("click", 
                    (function(k){
                        // js 闭包
                        return function(){
                            //将被点击marker置为中心
                            map.centerAndZoom(point[k], 18);
                            //在marker上打开检索信息窗口
                            searchInfoWindow[k].open(marker[k]);
                        }
                    })(i)                            
                ); 
				
				var removeMarker = function(e,ee,mymarker){
					map.clearOverlays();    //清除地图上所有覆盖物
					var p = mymarker.getPosition();       //获取marker的位置
					var myIcon = new BMap.Icon("./buidingmanage/images/pin_blue_solid_23.148936170213px_1129770_easyicon.net.png", new BMap.Size(32,32));
					var marker2 = new BMap.Marker(p,{icon:myIcon});  // 创建标注
					map.addOverlay(marker2);              // 将标注添加到地图中
					marker2.enableDragging();
// 					marker2.addEventListener("click",showInfo);
				}
				//创建右键菜单
				var markerMenu=new BMap.ContextMenu();
				markerMenu.addItem(new BMap.MenuItem('选择此处',removeMarker));
				marker[i].addContextMenu(markerMenu);
			}
		}
		var opts = {
		  width : 100,     // 信息窗口宽度
		  height: 50    // 信息窗口高度
		}
		function showInfo(e){
			var infoWindow = new BMap.InfoWindow(this.getLabel(), opts);  // 创建信息窗口对象 
			map.openInfoWindow(infoWindow,e.point); //开启信息窗口
		}
		function getAttr(){
			var p = this.getPosition();       //获取marker的位置
			alert("marker的位置是" + p.lng + "," + p.lat);   
		}
	</script>
	<script type="text/javascript">
		function searchKeywords(){
			if(jQuery('#suggestId').val() == ''){
				return;
			}
			map.clearOverlays();    //清除地图上所有覆盖物
			// 百度地图API功能
			local = new BMap.LocalSearch(map, {
				onMarkersSet: myFun
			});
			local.search(jQuery('#suggestId').val());
		}
	</script>
</body>
</html>