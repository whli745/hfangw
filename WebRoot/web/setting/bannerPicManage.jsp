<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="description" content=""/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="text/javascript" src="js/common.js"></script>	
<script type="text/javascript" src="<%=basePath%>js/layer/laypage-v1.3/laypage.js"></script>
<style type="text/css">
	*{font-family:"Microsoft YaHei",微软雅黑,"Microsoft JhengHei",华文细黑,tahoma,Arial,Helvetica,sans-serif; }
</style>
<script>
	String.prototype.endWith=function(endStr){
	  var d=this.length-endStr.length;
	  return (d>=0&&this.lastIndexOf(endStr)==d)
	}
	$(function(){
		$("input[type='button']").click(function(){
			var $val = $(this).siblings("input[type='file']").val();
			if($val == ''){
				common.alert('请先选择磁盘上的图片！');
				return false;
			}
			if(!$val.toLowerCase().endWith('.jpg')){
				common.alert('只能上传jpg格式图片！');
				return false;
			}
			$(this).parent()[0].submit();
		});
	});
</script>
</head>
<body style="background-color: #eee;">
	<div style="font-weight: bolder;color:red;font-size: 14px;">请先选择图片，再点击“确定上传”按钮！</div>
	<div style="font-weight: bolder;color:red;font-size: 14px;">图片格式要求：jpg</div>
	<hr style="border:1px solid #FF9901;"/>
	<table>
		<tr>
			<td>
				<img src="<%=basePath%>web/images/banner1.jpg?random=<%=new java.util.Random().nextDouble()%>" style="width: 100%;" />
			</td>
		</tr>
		<tr>
			<td>
				<form action="<%=basePath%>web/setting/uploadPic.jsp?fileName=banner1.jpg" method="post" enctype="multipart/form-data">
					<span style="font-weight: bolder;color:red;font-size: 14px;">请选择图片：</span>
					<input type="file" name="file"/>&nbsp;<input type="button" value="确定上传"/>
					<span style="font-weight: bolder;color:red;font-size: 14px;">推荐尺寸：1920PX × 401PX</span>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=basePath%>web/images/banner2.jpg?random=<%=new java.util.Random().nextDouble()%>" style="width: 100%;" />
			</td>
		</tr>
		<tr>
			<td>
				<form action="<%=basePath%>web/setting/uploadPic.jsp?fileName=banner2.jpg" method="post" enctype="multipart/form-data">
					<span style="font-weight: bolder;color:red;font-size: 14px;">请选择图片：</span>
					<input type="file" name="file"/>&nbsp;<input type="button" value="确定上传"/>
					<span style="font-weight: bolder;color:red;font-size: 14px;">推荐尺寸：1920PX × 401PX</span>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=basePath%>web/images/banner3.jpg?random=<%=new java.util.Random().nextDouble()%>" style="width: 100%;" />
			</td>
		</tr>
		<tr>
			<td>
				<form action="<%=basePath%>web/setting/uploadPic.jsp?fileName=banner3.jpg" method="post" enctype="multipart/form-data">
					<span style="font-weight: bolder;color:red;font-size: 14px;">请选择图片：</span>
					<input type="file" name="file" />&nbsp;<input type="button" value="确定上传"/>
					<span style="font-weight: bolder;color:red;font-size: 14px;">推荐尺寸：1920PX × 401PX</span>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=basePath%>web/images/weixin.jpg?random=<%=new java.util.Random().nextDouble()%>"/>
			</td>
		</tr>
		<tr>
			<td>
				<form action="<%=basePath%>web/setting/uploadPic.jsp?fileName=weixin.jpg" method="post" enctype="multipart/form-data">
					<span style="font-weight: bolder;color:red;font-size: 14px;">请选择图片：</span>
					<input type="file" name="file" />&nbsp;<input type="button" value="确定上传"/>
					<span style="font-weight: bolder;color:red;font-size: 14px;">推荐尺寸：160PX × 160PX</span>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=basePath%>web/images/hongbao.jpg?random=<%=new java.util.Random().nextDouble()%>"/>
			</td>
		</tr>
		<tr>
			<td>
				<form action="<%=basePath%>web/setting/uploadPic.jsp?fileName=hongbao.jpg" method="post" enctype="multipart/form-data">
					<span style="font-weight: bolder;color:red;font-size: 14px;">请选择图片：</span>
					<input type="file" name="file" />&nbsp;<input type="button" value="确定上传"/>
					<span style="font-weight: bolder;color:red;font-size: 14px;">推荐尺寸：160PX × 160PX</span>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
