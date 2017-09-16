<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
	<%@ include file="../../../include/heard.inc"%>
</head>

<body id="mouseRight">
<div class="mainDiv">
  	<dl class="mtab">
    	<dt>
         <p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysOrganList.action">机构管理</a><a>查看</a></p>
         <a href="querySysOrganList.action?areaId=<s:property value='areaId' />" class="back">返回</a>
        </dt>
    <dd>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
			<tr><th colspan=4><p class="title">查看区划</p></th></tr>	
			<tr>
				<td width="15%" class="txtR">机构代码：</td><td width="35%"><s:property value='sysOrgan.organCode'/></td>
		        <td width="15%" class="txtR">机构简码：</td><td width="35%"><s:property value='sysOrgan.organSimpCode'/></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">机构名称：</td><td width="35%"><s:property value='sysOrgan.organName'/></td>
		        <td width="15%" class="txtR">机构全称：</td><td width="35%"><s:property value='sysOrgan.organFullName'/></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">联系电话：</td><td width="35%"><s:property value='sysOrgan.organTel'/></td>
		        <td width="15%" class="txtR">联系地址：</td><td width="35%"><s:property value='sysOrgan.organAddr'/></td>		
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">所属区划：</td>
		        <td width="35%"><s:property value='sysOrgan.sysArea.areaName'/></td>
		        <td width="15%" class="txtR">上级机构：</td>
		        <td width="35%"><s:property value='sysParentOrgan.organName'/></td>
		    </tr>
		    <tr>
		        <td width="15%" class="txtR">启用状态：</td>
		        <td width="35%">
		        	<s:iterator value="@util.BaseParameter@STATUS">
						<s:if test="key==sysOrgan.organFlag"><s:property value="value"/></s:if>
					</s:iterator>
		        </td>
		        <td width="15%" class="txtR">排序号：</td><td width="35%"><s:property value='sysOrgan.organSort'/></td>		
		    </tr>
		</table>         
		<div class="btnbarBig">
			<a href="querySysOrganList.action?areaId=<s:property value='areaId' />" class="btnO">返回</a>
		</div>
	</dd>
	</dl>
</div>
</body>
</html>
