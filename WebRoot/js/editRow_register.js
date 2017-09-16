	function insertRow(){
		var tables = document.getElementById("listTab1");
		var sizes= document.getElementById("sizes").value==''?1:parseInt(document.getElementById("sizes").value)+1;
		var newTr = tables.insertRow();
	    var newTd1 = newTr.insertCell();
	    var newTd2 = newTr.insertCell();
	    var newTd3 = newTr.insertCell();
	    var newTd4 = newTr.insertCell();
	    newTd1.style.textAlign="center";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd1.className='box';
	    newTd1.innerHTML = sizes;
	    newTd2.innerHTML = "<input class='ipt_s'size='30' type='text' id='attaname"+sizes+"' name='attanames' /> ";
	    newTd3.innerHTML = "<input class='input_text' type='text' id='txt"+sizes+"' readonly/>&nbsp;&nbsp;<input type='button' value='上传...' class='btn_select' /><input class='input_file' size='30' type='file' name='attafiles' onchange='setFileName(this.value,"+sizes+");'/>";
	    newTd4.innerHTML = "<input type='button' value='删除' class='btn_select'  onclick='deleteRow("+sizes+");' />";
	    document.getElementById("sizes").value = sizes;
	}
	function deleteRow(index){
		var tables = document.getElementById("listTab1");
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	function deleteFile(index,fileId){
		Dialog.confirm('确定要删除吗？',function(){
				if(document.getElementById('fileIds').value=='')
					document.getElementById('fileIds').value=fileId;
				else document.getElementById('fileIds').value=document.getElementById('fileIds').value+","+fileId;
				deleteRow(index);
		});
	}
	function setFileName(filevalue,index){
		
		if(document.getElementById("attaname"+index)&&filevalue!=""&&document.getElementById("attaname"+index).value==""){
			document.getElementById("attaname"+index).value = filevalue.substr(filevalue.lastIndexOf('\\')+1,filevalue.length);
		}
		document.getElementById("txt"+index).value=filevalue;
	}
