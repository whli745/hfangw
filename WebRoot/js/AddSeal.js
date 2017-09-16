var sealClassId="CLSID:C1FB7513-9A44-4C64-B653-63C6965D7F4C";
var obj;
var ii=0;
var x=0;
var y=0;
var temppos21 = 0;
var temppos22 = 0;
var count = 0;
function getobj(){
	//alert(getElementById("position").value);
//	count++;
//	alert(obj.style.left);
//	alert(obj.style.top);
//	x = obj.style.left;
//	y = obj.style.top;
//	alert(document.getElementById("auto").value );//
//	temppos1 = x+","+y+";";
//	if(count==1){
//		temppos22 = temppos21;
//	}else {
//		temppos22 = temppos21+temppos22;
//	}
//	document.getElementById("position").value = temppos22;
//	alert(document.getElementById("position").value);
}
function AddSeal(OnGetProtectedData,SignedDataStoreElement)
{
	//创建一个Object对象(印章)
	obj=document.createElement("object");
	obj.width=158;
	obj.height=158;
	var mt=document.getElementById("name");
	mt.appendChild(obj);  //追加Object对象
	
	//Obj的初始位置
	obj.style.left=1+"px";
	obj.style.top=1+"px";
	
	obj.style.position="absolute";   //绝对位置
	obj.classid=sealClassId;
	obj.Authority=8;    //禁止移动
	obj.OnGetProtectedData=OnGetProtectedData;  // 受保护的字段
	
	//印章的显示模式
	//obj.DrawModeUnsign=8;
	obj.DrawMode=18;
	
	//触发签章或签名、批注的方法；为'true'时签章，否则为签名或批注。
	obj.SignSeal(true);
	
	if (obj.CurrentState==0||ii>4)  //（CurrentState）的状态，0－尚未签章或签名；1－已经签章；2－已经签名。
	{
		obj.parentElement.removeChild(obj);
		if(ii>4){
			alert('no');
		}
		return false;
	}
	
	ii++;
	
	//赋值
	document.getElementById("sealdata").value = obj.SignedData+";"+document.getElementById("sealdata").value;
	//alert("---"+document.getElementById("position").value);
	document.getElementById("position").value = x+"px,"+y+"px;"+document.getElementById("position").value;
	document.getElementById("number").value = ii;
	document.getElementById("auto").value = "no";
	//alert(document.getElementById("auto").value );
	return true;
}

function AddSeal_bak(OnGetProtectedData,SignedDataStoreElement)
{
	//创建一个Object对象(印章)
	obj=document.createElement("object");
	obj.width=158;
	obj.height=158;
	var mt=document.getElementById("name");
	mt.appendChild(obj);  //追加Object对象
	
	//Obj的初始位置
	obj.style.left=233+"px";
	obj.style.top=347+"px";
	
	obj.style.position="absolute";   //绝对位置
	obj.classid=sealClassId;
	obj.Authority=3;    //禁止移动
	obj.OnGetProtectedData=OnGetProtectedData;  // 受保护的字段
	
	//印章的显示模式
	obj.DrawModeUnsign=8;
	obj.DrawMode=18;
	
	//触发签章或签名、批注的方法；为'true'时签章，否则为签名或批注。
	obj.SignSeal(true);
	
	if (obj.CurrentState==0||ii>4)  //（CurrentState）的状态，0－尚未签章或签名；1－已经签章；2－已经签名。
	{
		obj.parentElement.removeChild(obj);
		if(ii>4){
			alert(no);
		}
		return false;
	}
	
	//签章或签名的次数
	if(ii==0)   
		{
				x=160;
				y=225;
		}else if(ii==1){
				x=440;
				y=225;
		}else if(ii==2){
				x=730;
				y=225;
		}else if(ii==3){
			x=140;
			y=380;
		}else if(ii==4){
			x=420;
			y=380;
		}
	//印章的位置
	obj.style.left=x+"px";	
	obj.style.top=y+"px";	
	ii++;
	
	//赋值
	document.getElementById("sealdata").value = obj.SignedData+";"+document.getElementById("sealdata").value;
	document.getElementById("position").value = x+"px,"+y+"px;"+document.getElementById("position").value;
	document.getElementById("number").value = ii;
	document.getElementById("auto").value = "no";
	//alert(document.getElementById("auto").value );
	return true;
}

function AddSign(OnGetProtectedData,SignedDataStoreElement)
{
	obj=document.createElement("object");  //创建一个Object对象(印章)
	obj.width=158;
	obj.height=158;
	var mt=document.getElementById("name");   
	mt.appendChild(obj);  //追加Object对象
	//Obj的初始位置
	obj.style.left=233+"px";
	obj.style.top=347+"px";
	
	obj.style.position="absolute";  //绝对位置
	obj.classid=sealClassId;
	obj.Authority=3;   //禁止移动
	obj.OnGetProtectedData=OnGetProtectedData;   //受保护的字段
	
	//印章的显示模式
	obj.DrawModeUnsign=8;
	obj.DrawMode=18;
	//触发签章或签名、批注的方法；为'true'时签章，否则为签名或批注。
	obj.SignSeal(false);
	
	if (obj.CurrentState==0||ii>4) //（CurrentState）的状态，0－尚未签章或签名；1－已经签章；2－已经签名。
	{
		obj.parentElement.removeChild(obj);
		if(ii>4){
			alert(no);
		}
		return false;
	}
	//签章或签名的次数
	if(ii==0)       
		{
				x=160;
				y=225;
		}else if(ii==1){
				x=440;
				y=225;
		}else if(ii==2){
				x=730;
				y=225;
		}else if(ii==3){
			x=140;
			y=380;
		}else if(ii==4){
			x=420;
			y=380;
		}
	
	//印章的位置
	obj.style.left=x+"px";	
	obj.style.top=y+"px";	
	ii++;
	
	//赋值
	document.getElementById("sealdata").value = obj.SignedData+";"+document.getElementById("sealdata").value;
	document.getElementById("position").value = x+"px,"+y+"px;"+document.getElementById("position").value;
	//document.getElementById("number").value = ii;
	//document.getElementById("auto").value = "no";
	return true;
}


