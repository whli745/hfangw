<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css" />
</head>

<body id="mouseRight">
<div class="mainDiv">
  	<dl class="mtab">
    	<dt>
       	 	<p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysAreaList.action?topId=">区划管理</a><a>查看</a></p><a href="#" class="back" onclick="history.go(-1)">返回</a>
        </dt>
        <dd>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
			<tr><th colspan=4><p class="title">查看区划</p></th></tr>	
			<tr>
				<td width="15%" class="txtR">区划代码：</td>
		        <td width="35%" ><s:property value="sysArea.areaCode" /></td>
				<td width="15%" class="txtR">区划名称：</td>
				<td width="35%" ><s:property value="sysArea.areaName" /></td>
		    </tr>
			<tr>
				<td width="15%" class="txtR">父级区划：</td>
				<td width="35%" ><s:property value="sysAreaTop.areaName" /></td>
				<td width="15%" class="txtR">序号：</td>
				<td width="35%"><s:property value="sysArea.areaSort" /></td>        	
		    </tr>
		    <tr>
		    	<td width="15%" class="txtR">区划级别：</td>
				<td width="35%"  colspan="3">
					<s:iterator value="@util.BaseParameter@AREA_LEVEL">
						<s:if test="key>sysAreaTop.areaLevel">
						<s:if test="key.equals(sysArea.areaLevel)"><s:property value='value'/></s:if>
						</s:if> 
					</s:iterator>
				</td>
			</tr>
		</table>         
		<div class="btnbarBig"><a href="javascript:history.back()" class="btnO">返回</a></div>
	</dd>
	</dl>
</body>
</html>
