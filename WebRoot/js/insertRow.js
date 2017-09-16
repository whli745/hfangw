	rowNum = 2;
	rowNum1=2;
	rowNum2=2;
	function insertRow(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd2.valign = "middle";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    var strHtml = '';
	    if(document.getElementById('div_progress') == null) {
	    	strHtml = "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='attanames' /> ";
	    	strHtml += "<input class='ipt_s' type='hidden' id='attanameid"+rowNum+"' name='attanamesid' /> ";
	    	strHtml += "<input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' />";
	    	strHtml += "<input type='button' value='上传...' class='btn_select' />";
	    	strHtml += "<input class='input_file' size='30' type='file' name='attafiles' onchange='setFileName(this.value,"+rowNum+");' />";
	    	newTd2.innerHTML = strHtml;
	    }
	    else {
	    	strHtml = "<div style='float:left; margin-top: 5px; width:80%;'>";
	    	strHtml += "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='attanames' />";
	    	strHtml += "<input class='ipt_s' type='hidden' id='attanameid"+rowNum+"' name='attanamesid' /> ";
	    	strHtml += "<input readonly='readonly' class='input_text' name='fileNames' type='text' id='txt"+rowNum+"' /></div>";
	    	strHtml += "<input type='file' name='uploadify' id='uploadify"+rowNum+"' />";
	    	strHtml += "<input type='hidden' name='fileMainIds' id='fileMainId"+rowNum+"' />";
	    	strHtml += "<input type='hidden' name='savePaths' id='savePath"+rowNum+"' />";
	    	strHtml += "<input type='hidden' name='saveFileNames' id='saveFileName"+rowNum+"' />";
	    	newTd2.innerHTML = strHtml;
	    }
//	    newTd4.innerHTML = "<input type='button'  id='delButton"+rowNum+"' value='删除' class='btn_select'  onclick='deleteRow("+rowNum+");' />";
//	    newTd1.className='box';
	    if(document.getElementById('div_progress') != null) 
	    	initUploadify(rowNum);
	    rowNum ++;
	}
	
	
	
	
	function insertRow_view(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd2.valign = "middle";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    newTd2.innerHTML = "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='fileVos["+rowNum+"].attaName' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' />	<input type='button' value='上传...' class='btn_select' />	<input class='input_file' size='30' type='file' name='fileVos["+rowNum+"].attaFile' onchange='setSameFileName(this.value,"+rowNum+");' /><input type='hidden' name='fileVos["+rowNum+"].attaPath' id='savePath"+rowNum+"' />";
	    newTd4.innerHTML = "<input type='button'  id='delButton"+rowNum+"' value='删除' class='btn_select'  onclick='deleteRow("+rowNum+");' />";
	    newTd1.className='box';
	    rowNum ++;
	}
	
	// 文件名显示框居左
	function insertRow_upload(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="left";
	    newTd2.valign = "middle";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    newTd2.innerHTML = "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='fileVos["+rowNum+"].attaName' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' />	<input type='button' value='上传...' class='btn_select' />	<input class='input_file' size='30' type='file' name='fileVos["+rowNum+"].attaFile' onchange='setSameFileName(this.value,"+rowNum+");' /><input type='hidden' name='fileVos["+rowNum+"].attaPath' id='savePath"+rowNum+"' />";
	    newTd4.innerHTML = "<input type='button'  id='delButton"+rowNum+"' value='删除' class='btn_select'  onclick='deleteRow("+rowNum+");' />";
	    newTd1.className='box';
	    rowNum ++;
	}
	
	// 带有下载
	function insertRow_down(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="left";
	    newTd2.valign = "middle";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    newTd2.innerHTML = "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='fileVos["+rowNum+"].attaName' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' />	<input type='button' value='上传...' class='btn_select' />	<input class='input_file' size='30' type='file' name='fileVos["+rowNum+"].attaFile' onchange='setSameFileName(this.value,"+rowNum+");' /><input type='hidden' name='fileVos["+rowNum+"].attaPath' id='savePath"+rowNum+"' />&nbsp;&nbsp;<input type='button' value='下载' id='down"+rowNum+"' class='btn_select' />";
	    newTd4.innerHTML = "<input type='button'  id='delButton"+rowNum+"' value='删除' class='btn_select'  onclick='deleteRow("+rowNum+");' />";
	    newTd1.className='box';
	    rowNum ++;
	}
	
	//企业管理内使用
	function insertRow_qiye(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd2.valign = "middle";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    newTd2.innerHTML = "<div style='float:left; margin-top: 5px; width:80%;'><input class='ipt_s' type='text' id='attaname"+rowNum+"' name='attanames' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' /></div><input type='file' name='uploadify' id='uploadify"+rowNum+"' /><input type='hidden' name='fileMainIds' id='fileMainId"+rowNum+"' /><input type='hidden' name='savePath' id='savePath"+rowNum+"' />";
	    newTd1.className='box';
	    if(document.getElementById('div_progress') != null) 
	    	initUploadify(rowNum);
	    rowNum ++;
	}
	
	function insertRow_zyzg(){//职业资格
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd2.valign = "middle";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    if(document.getElementById('div_progress') == null) 
	    	newTd2.innerHTML = "<input class='ipt_s' type='text' id='attaname"+rowNum+"' name='attanames' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' />	<input type='button' value='上传...' class='btn_select' />	<input class='input_file' size='30' type='file' name='attafiles' onchange='setFileName(this.value,"+rowNum+");' />";
	    else
	    	newTd2.innerHTML = "<div style='float:left; margin-top: 5px; width:80%;'><input class='ipt_s' type='text' id='attaname"+rowNum+"' name='attanames' /> <input readonly='readonly' class='input_text' type='text' id='txt"+rowNum+"' /></div><input type='file' name='uploadify' id='uploadify"+rowNum+"' /><input type='hidden' name='fileMainIds' id='fileMainId"+rowNum+"' /><input type='hidden' name='savePath' id='savePath"+rowNum+"' />";
	    newTd4.innerHTML = "<input type='button'  id='delButton"+rowNum+"' value='删除' class='btn_select'  onclick='deleteRow("+rowNum+");' />";
	    newTd1.className='box';
	    if(document.getElementById('div_progress') != null) 
	    	initUploadify_setName(rowNum);
	    rowNum ++;
	}
	
	
	
	
	function insertRow_zhengshu(){
		var tables = document.getElementById("listTab2");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd3 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    var newTd5 = newTr.insertCell(-1);
	    var newTd6 = newTr.insertCell(-1);
	    var newTd7 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd5.style.textAlign="center";
	    newTd6.style.textAlign="center";
	    newTd7.style.textAlign="center";
	    newTd0.innerHTML = rowNum2;
	    newTd1.innerHTML = rowNum2;
	    newTd2.innerHTML = "<input class='ipt_s'  id='enterpriseNumber'"+rowNum2+" name='enterpriseNumbers'/>";
	    newTd3.innerHTML = "<input class='ipt_s'  id='registrationNumber'"+rowNum2+" name='registrationNumbers'/> ";
	    newTd4.innerHTML = "<input type='hidden'  id='professionalGrading"+rowNum2+"' name='professionalGradings'/><input class='ipt_s' readonly='readonly'  id='professionalGradingName"+rowNum2+"'/><input type='button' class='btn_select' value='资质' onclick=\"$('#statusId').val("+rowNum2+");$('#m-pm1').click();\" style='float: right;margin: 1px auto;'/>";
	    newTd5.innerHTML = "<input id='issueDate'"+rowNum2+" name='issueDates' class='Wdate'  type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd6.innerHTML = "<input class='Wdate'  id='validity'"+rowNum2+" name='validitys' type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd7.innerHTML = "<input type='button' value='删除' class='btn_select'  onclick='deleteRow_zhengshu("+rowNum2+");' />";
	    newTd1.className='box';
	    rowNum2 ++;
	}
	function deleteRow_zhengshu(index){ 
		var tables = document.getElementById("listTab2"); 
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	function insertRow_zhengshu_sgdw1(){  
		var tables = document.getElementById("listTab2"); 
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd3 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    var newTd5 = newTr.insertCell(-1);
	    var newTd6 = newTr.insertCell(-1);
	    var newTd7 = newTr.insertCell(-1);
	    var newTd8 = newTr.insertCell(-1); 
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd5.style.textAlign="center";
	    newTd6.style.textAlign="center";
	    newTd7.style.textAlign="center";
	    newTd8.style.textAlign="center";  
	    newTd0.innerHTML = rowNum1;
	    newTd1.innerHTML = rowNum1;
	    newTd2.innerHTML = "<input class='ipt_s' style='width:100px;'  id='registrationNumber"+rowNum1+"' name='registrationNumbers'/> ";
	    newTd3.innerHTML = "<input class='ipt_s' style='width:100px;'  id='enterpriseNumber"+rowNum1+"' name='enterpriseNumbers'/> ";
	    newTd4.innerHTML = "<input class='ipt_s' style='width:100px;'  id='registerNumber"+rowNum1+"' name='registerNumbers'/> ";
	    newTd5.innerHTML = "<input type='hidden'  id='professionalGrading"+rowNum1+"' name='professionalGradings'/><input class='ipt_s' readonly='readonly'  id='professionalGradingName"+rowNum1+"'/><input type='button' class='btn_select' value='资质' onclick=\"$('#statusId').val("+rowNum1+");$('#m-pm1').click();\" style='float: right;margin: 1px auto;'/>"; 
	    newTd6.innerHTML = "<input id='issueDate"+rowNum1+"' name='issueDates' class='Wdate'  type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd7.innerHTML = "<input class='Wdate'  id='validity"+rowNum1+"' name='validitys' type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd8.innerHTML = "<input type='button' value='删除' class='btn_select'  onclick='deleteRow_zhengshu_sgdw1("+rowNum1+");' />";
	    newTd1.className='box';
	    rowNum1 ++;
	}
	function deleteRow_zhengshu_sgdw1(index){  
		var tables = document.getElementById("listTab2"); 
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	function insertRow_zhengshu_sgdw2(){   
		var tables = document.getElementById("listTab3"); 
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd3 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    var newTd5 = newTr.insertCell(-1);
	    var newTd6 = newTr.insertCell(-1);
	    var newTd7 = newTr.insertCell(-1); 
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd5.style.textAlign="center";
	    newTd6.style.textAlign="center";
	    newTd7.style.textAlign="center";  
	    newTd0.innerHTML = rowNum2;
	    newTd1.innerHTML = rowNum2;
	    newTd2.innerHTML = "<input class='ipt_s'  id='registrationNumber'"+rowNum2+" name='registrationNumbers_0'/> ";
	    newTd3.innerHTML = "<input class='ipt_s'  id='professionalGrading'"+rowNum2+" name='professionalGradings_0'/>"; 
	    newTd4.innerHTML = "<input id='issueDate'"+rowNum2+" name='issueDates_0' class='Wdate'  type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd5.innerHTML = "<input class='Wdate'  id='validity'"+rowNum2+" name='validitys_0' type='text' onfocus='WdatePicker()' readonly='readonly'/>";
	    newTd6.innerHTML = "<input   size='30'style='width:90%;' id='file_'"+rowNum2+" type='file' name='zhengshufiles_0'/>";
	    newTd7.innerHTML = "<input type='button' value='删除' class='btn_select'  onclick='deleteRow_zhengshu_sgdw2("+rowNum2+");' />";
	    newTd1.className='box';
	    rowNum2 ++;
	}
	function deleteRow_zhengshu_sgdw2(index){ 
		var tables = document.getElementById("listTab3"); 
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	function deleteRow(index){ 
		var tables = document.getElementById("listTab1"); 
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				if(jQuery('#fileMainId'+(index)).val() != '') {
					addfileOid('fileIds',jQuery('#fileMainId'+(index)).val());
				}
				tables.deleteRow(i);
			}
		}
	}
	function addfileOid(elementId, fileoid){
		var t = document.getElementById(elementId);
		if(t.value=="") t.value = fileoid;
		else t.value += "," + fileoid;
	}
	function setFileName(filevalue,index){
		
		if(filevalue!=""&&document.getElementById("attaname"+index).value==""){
			document.getElementById("attaname"+index).value = filevalue.substr(filevalue.lastIndexOf('\\')+1,filevalue.length);
		}
		document.getElementById("txt"+index).value=filevalue;
	}
	function setSameFileName(filevalue,index){
		
		if(filevalue!=""){
			document.getElementById("attaname"+index).value = filevalue.substr(filevalue.lastIndexOf('\\')+1,filevalue.length);
		}
		document.getElementById("txt"+index).value=filevalue;
	}
	function insertFeeDetailRow(){
		var tables = document.getElementById("listTab1");
		var newTr = tables.insertRow(-1);
	    var newTd0 = newTr.insertCell(-1);
	    var newTd1 = newTr.insertCell(-1);
	    var newTd2 = newTr.insertCell(-1);
	    var newTd3 = newTr.insertCell(-1);
	    var newTd4 = newTr.insertCell(-1);
	    var newTd5 = newTr.insertCell(-1);
	    newTd0.style.display="none";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd5.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd0.innerHTML = rowNum;
	    newTd1.innerHTML = rowNum;
	    newTd2.innerHTML = "<input class='ipt' type='text' id='detailName"+rowNum+"' name='cgInfFeeDetail.detailName' />";
	    newTd3.innerHTML = "<input class='ipt' type='text' id='detailMn"+rowNum+"' name='cgInfFeeDetail.detailMn' onkeyup='addDetailMnCapital(this)'/>";
	    newTd4.innerHTML = "<input class='ipt' type='text' id='detailMnCapital"+rowNum+"' name='cgInfFeeDetail.detailMnCapital' readonly='readonly'/>";
	    newTd5.innerHTML = "<input type='button' value='删除' class='btn_select'  onclick='deleteFeeDetailRow("+rowNum+");' />";
	    newTd1.className='box';
	    rowNum ++;
	}
	function deleteFeeDetailRow(index){
		var tables = document.getElementById("listTab1");
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	var addDetailMnCapital=function(detailMn){
		var detailMnCapital = detailMn.id.substring(0,detailMn.id.length-1)+"Capital"+detailMn.id.substring(detailMn.id.length-1,detailMn.id.length);
		if(jQuery(detailMn).val()>=0){
			if(jQuery(detailMn).val()==0){
				jQuery('#'+detailMnCapital).val('');
			}else{
				if(jQuery("#flag").val()==1){
					jQuery('#'+detailMnCapital).val(amountToChinese((parseInt(jQuery.trim(jQuery(detailMn).val()))*10000).toString()));
				}else{
					jQuery('#'+detailMnCapital).val(amountToChinese(jQuery.trim(jQuery(detailMn).val())));
				}
			}
		}else{
			$('#'+detailMnCapital).val('');
			Dialog.alertFocus("金额必须是正数！",detailMn.id);
		}
	}
	
	//form submit delete empty file table
	function delEmptyFileTable() {
		var arrDelNum = new Array();
		var tmpSort = 0;
		$("[name=attanames]").each(function(i){
			var attanamesId = $(this).attr("id");
			   var delNum = attanamesId.replace('attaname','');
			   var delFileInfo = '';
			   if(document.getElementsByName('attafiles') !=null && document.getElementsByName('attafiles')[i] != null)
				   delFileInfo = document.getElementsByName('attafiles')[i].value;
			   var delFileInfoPath = '';
			   if(document.getElementsByName('savePath') != null && document.getElementsByName('savePath')[i] != null) 
				   delFileInfoPath = document.getElementsByName('savePath')[i].value;  
			   if(delFileInfo== null || delFileInfo == '') {
				   if(delFileInfoPath== null || delFileInfoPath == '') {
					   arrDelNum[tmpSort] = parseInt(delNum);
					   tmpSort++;
				   }
			   }
		});
		for(var i= 0;i<arrDelNum.length;i++) {
			deleteRow(arrDelNum[i]);
		}
	}
	
	function initUploadify(rowNum) {
		jQuery("#uploadify" + rowNum).uploadify({
			'swf' : './js/uploadify/uploadify.swf', //flash文件的相对路径
			'uploader'  : './doUploadUploadify.action?fileSaveLocation=&ver=' + Math.random(), //后台处理程序的相对路径
			'fileObjName' : 'file', //设置上传文件名称,默认为Filedata
			'queueID' : 'div_progress', //文件队列的ID，该ID与存放文件队列的div的ID一致
			'fileTypeDesc' : 'jpg、gif、jpeg、png、txt、doc、docx、xls、xlsx、ppt、pptx、pdf文件', //用来设置选择文件对话框中的提示文本        
			'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png;*.txt;*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.pdf', //设置可以选择的文件的类型
			'auto' : true, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
			'multi' : true, //设置为true时可以上传多个文件
			'queueSizeLimit' : 30,
			'uploadLimit'  : 30, //允许同时上传的个数 默认值：1 此处不能修改，因为上传的顺序对预览很重要，同一时间只能上传一个
			'fileSizeLimit'  : '10240KB', //上传文件的大小限制1m
			'buttonText' : '上传...', //浏览按钮的文本，默认值：BROWSE
			'width' : 110,//设置浏览按钮的宽度，默认值：110。
			'height' : 30,//设置浏览按钮的高度，默认值：30。 
			'preventCaching' : true,
			//在单文件或多文件上传时，选择文件时触发。该函数有两个参数event，data
			'onSelect' : function(file) {
				$('#txt' + rowNum).val('');
				$('#saveFileName' + rowNum).val('');
				$('#savePath' + rowNum).val('');
				//打开遮罩层
				$("#shadeDiv").css("display","block"); 
				$("#div_progress").css("display","block"); 
			},
			'onSelectError':function(file, errorCode, errorMsg){
				if(errorCode==-110){
					alert("文件最大为10M");
					$("#div_progress").css("display","none"); 
					$("#shadeDiv").css("display","none"); 
				}
			},
			'onUploadSuccess' : function(file, data, response) {
	            var fileName = file.name;
				$('#txt' + rowNum).val($('#txt' + rowNum).val() + fileName + ";");
				$('#saveFileName' + rowNum).val($('#saveFileName' + rowNum).val() + fileName + ";");
				$('#savePath' + rowNum).val($('#savePath' + rowNum).val() + data + ";");
				//关闭遮罩层
				$("#div_progress").css("display","none"); 
				$("#shadeDiv").css("display","none"); 
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	            alert('文件 ' + file.name + '不能上传: ' + errorString);
	        }
		});
	}

