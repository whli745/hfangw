<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<%@ include file="../include/heard.inc"%>
<!-- jQuery核心库 & 界面组件  -->
<script type="text/javascript" src="<%=basePath%>zwdt/js/jquery-1.8.2.min.js"></script>
<!-- 弹出层JS -->
<script type="text/javascript" src="<%=basePath%>zwdt/js/layer/layer.min.js"></script>
<!-- 常用JS -->
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript">
function check() {
	if(jQuery.trim(jQuery("#checkFlag").val()) == 'refuse') {//审核不通过，一定要填写审核意见 
		var checkOpinion=jQuery.trim(jQuery("#checkOpinion").val());
		 if(checkOpinion==""){
			 common.alertFocus("审核不通过时，审核意见不能为空！", 'checkOpinion');
			return false;
		}
		if(checkOpinion.length>48){
			common.alertFocus("审核意见不能超过48个字！", 'checkOpinion');
			return false;
		}
	}
	common.confirm('是否确定此操作？',function(){
		$.ajax({
			type:"post",
			cache:false,
			url:"doAuditCertifiction.action",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			data:$('#form1').serialize(),
			success:function(data){
				parent.location.reload();
				parent.layer.closeAll();
			},
			error: function(request) {
				layer.alert('审核失败', 8); //风格一
				return false;
			}
		});
	});
}
</script>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
		<dd>
		<form name="form1" id="form1">
			<input type="hidden" value="<s:property value='ucId' />" name="ucId"/>
			<input type="hidden" value="<s:property value='ids' />" name="ids" id="userId"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
				<tr>
					<th colspan="4"><p class="title" style="text-align: left;padding-left: 10px; font-weight: bold;">审核</p></th>
				</tr>
				<tr>
					<td width="15%" class="txtR">是否通过：</td>
					<td colspan="3" >
						<select id="checkFlag" name="checkType">
							<option value="pass">通过</option>
							<option value="refuse">不通过</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%" class="txtR">审核意见：</td>
					<td colspan="3" >
						<textarea id="checkOpinion" name="checkContent" rows="6" cols="100"></textarea>
					</td>
				</tr>
			</table>
			<div class="btnbar">
				<a href="javascript:void(0)" class="btnG" onclick="check()"><img src="images/ico_btn_save.gif"/>确定</a>
				<a href="javascript:void(0)" onclick="parent.layer.closeAll();"><img src="images/ico_btn_cancel.gif" />关闭</a>
       		</div>
       	</form>
		</dd>
	</dl>
</div>
</body>
</html>
