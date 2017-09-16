	var rowNum = 1;
	function insertRow(){
		var tables = document.getElementById("listTab1");
		var sizes= document.getElementById("sizes").value;
		var newTr = tables.insertRow();
	    var newTd0 = newTr.insertCell();
	    var newTd1 = newTr.insertCell();
	    var newTd2 = newTr.insertCell();
	    var newTd3 = newTr.insertCell();
	    var newTd4 = newTr.insertCell();
	    var newTd5 = newTr.insertCell();
	    var newTd6 = newTr.insertCell();
	    var newTd7 = newTr.insertCell();
	    var newTd8 = newTr.insertCell();
	    newTd0.style.display="none";
	    newTd1.style.textAlign="center";
	    newTd2.style.textAlign="center";
	    newTd3.style.textAlign="center";
	    newTd4.style.textAlign="center";
	    newTd1.className='box';
	    newTd0.innerHTML = rowNum+parseInt(sizes);
	    newTd1.innerHTML = rowNum+parseInt(sizes);
	    document.getElementById("maxCount").value = rowNum+parseInt(sizes);
	    newTd2.innerHTML = "<input class='ipt' readonly type='text' id='name"+(rowNum+parseInt(sizes))+"' name='name' />";
	    newTd3.innerHTML = "<input class='ipt' readonly type='text' id='price"+(rowNum+parseInt(sizes))+"' name='price' />";
	    newTd4.innerHTML = "<input class='ipt' readonly type='text' id='priceRate"+(rowNum+parseInt(sizes))+"' name='priceRate' />";
	    newTd5.innerHTML = "<input class='ipt' readonly type='text' id='agreementPrice"+(rowNum+parseInt(sizes))+"' name='agreementPrice' />";
	    newTd6.innerHTML = "<input class='ipt' readonly type='hidden' id='priceId"+(rowNum+parseInt(sizes))+"' name='priceId' />";
	    newTd7.innerHTML = "<input class='ipt' type='text' id='num"+(rowNum+parseInt(sizes))+"' name='num' />";
	    newTd8.innerHTML = "<a href='javascript:void(0);' onclick='deleteRow("+(rowNum+parseInt(sizes))+");'>删除</a>";
	    newTd6.style.display="none";
	    rowNum ++;
	}
	function deleteRow(index){
		var tables = document.getElementById("listTab1");
		for(var i=0;i<tables.rows.length;i++){
			if(tables.rows[i].cells[0].innerHTML==index){
				tables.deleteRow(i);
			}
		}
	}
	function deleteBill(index,billId){
		Dialog.confirm('是否确定删除？',function(){
				if(document.getElementById('billId').value=='')
					document.getElementById('billId').value=billId;
				else document.getElementById('billId').value=document.getElementById('billId').value+","+billId;
				deleteRow(index);
		});
	}
	function setFileName(filevalue,index){
		
		if(filevalue!=""&&document.getElementById("attaname"+index).value==""){
			document.getElementById("attaname"+index).value = filevalue.substr(filevalue.lastIndexOf('\\')+1,filevalue.length);
		}
		document.getElementById("txt"+index).value=filevalue;
	}
