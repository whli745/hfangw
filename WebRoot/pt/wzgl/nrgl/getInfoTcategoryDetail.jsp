<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<%@ include file="../../../include/heard.inc"%>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
    	<dt>
        	<p class="notice">查看栏目</p>
        </dt>
		<dd>
			<form name="myForm" id="myForm" method="post" action="saveInfoTcategory.action">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
					<tr>
						<th colspan="4"><p class="title" style="text-align: left;padding-left: 10px; font-weight: bold;">栏目信息</p></th>
					</tr>
					<tr>
						<td width="15%" class="txtR">栏目名称：</td>
						<td width="35%">
							<s:property value='infoTcategory.columnName'/>
						</td>
						<td width="15%" class="txtR">排序：</td>
						<td width="35%">
							<s:property value='infoTcategory.sort'/>
						</td>
					</tr>
					<tr>
						<td width="15%" class="txtR">栏目描述：</td>
						<td colspan="3" width="35%">
							<s:property value='infoTcategory.columnDescribe'/>
						</td>
					</tr>
				</table>
				<div class="btnbar">
					<a href="javascript:void(0)" onclick="back()"><img src="images/ico_btn_back.gif" />返回</a>
        		</div>
			</form>
		</dd>
	</dl>
</div>
</body>
</html>
