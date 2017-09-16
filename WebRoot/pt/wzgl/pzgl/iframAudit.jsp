<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath%>personalSpace/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>personalSpace/js/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>personalSpace/js/common.js"></script>
<link href="<%=basePath%>personalSpace/style/userCenter.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
			if($("#checkContent").val().length > 48){
				alert("审核意见字数不能超过48个字！");
				return false;
			}
			$("#form").removeAttr("onsubmit");
			var options = {
					type:'POST',
			    	url: 'doAuditCertifiction.action',
		    	success: function(msg) {
		    		if( msg == "success" ){
		    			parent.location.reload();
		    		}else{
		    			layer.alert("审核失败...",8);
		    		}
	    		} 
			};
			$('#form').ajaxForm(options);
		});
	});
	
</script>
</head>
<body>
	<div class="iframMain">
		<form id="form" method="POST" onsubmit="return false;">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"
							style="border: 1px solid #afd4f1; background: #FFF;" class="tableForm mainTable">
				<tr>
					<td style="text-align:right" width="16%">是否通过：</td>
					<td>
						<label><input checked="checked" type="radio" name="checkType" value="pass" style="width: 20px;height: auto;">通过</label>
						<label><input type="radio" name="checkType" value="noPass" style="width: 20px;height: auto;">不通过</label>
						<input type="hidden" name="ucId" value="<s:property value='userCertificate.id'/>">
						<input type="hidden" name="ids" value="<s:property value='netUser.id'/>">
					</td>
				</tr>
				<tr>
					<td style="text-align:right" width="16%">审核意见：</td>
					<td>
						<textarea rows="7" cols="60" id='checkContent' name="checkContent"></textarea>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center" height="50">
				 		<input id="submit" type="submit" value="确定" style="padding:2px 8px;width: 50px;cursor: pointer;">&nbsp;&nbsp;
				 		<input type="button" value="取消" style="padding:2px 8px;width: 50px;cursor: pointer;" onclick="parent.layer.closeAll();">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>