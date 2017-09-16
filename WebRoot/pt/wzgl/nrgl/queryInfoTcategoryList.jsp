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
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/ZwdtDwr.js'></script>
<script type="text/javascript">
function using(columnId, usingFlag) {
	var message = "启用？";
	if(usingFlag == 1) message = "禁用？";
	var toUsingFlag = usingFlag == 1 ? 0 :1;
	common.confirm('是否确定'+message, function() {
		var parentId = "<s:property value='infoTcategory.parentId'/>";
		var rank = "<s:property value='infoTcategory.rank'/>";
		location.href = "usingInfoTcategory.action?toUsingFlag="+toUsingFlag+"&columnId="+ columnId + "&infoTcategory.parentId=" + parentId+ "&infoTcategory.rank=" + rank;
	});
}


function del(columnId){
	
	ZwdtDwr.hasNextInfoTcategory(columnId, function(flag){
        if (!flag) {
        	Dialog.alert('有下级栏目，不能删除');
        } else {
        	common.confirm('是否确定删除？',function(){
        		var parentId = "<s:property value='infoTcategory.parentId'/>";
        		var rank = "<s:property value='infoTcategory.rank'/>";
        		location.href="delInfoTcategory.action?columnId="+columnId+"&infoTcategory.parentId="+parentId+"&infoTcategory.rank="+rank;
        	});
        }
    });
}
</script>
</head>
<body>
<div class="mainDiv">
	<div class="infoSearch">
		<form id="form1" name="myForm" method="get" action="queryInfoTcategoryList.action">
			<input type="hidden" id="parentId" name="infoTcategory.parentId" class="ipt_s" value="<s:property value='infoTcategory.parentId'/>" /> 
			<input type="hidden" id="rank" name="infoTcategory.rank" class="ipt_s" value="<s:property value='infoTcategory.rank'/>" /> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTab">
				<tbody>
					<tr>
						<td width="15%" class="txtR">栏目名称：</td>
						<td width="85%">
							<input type="text" name="infoTcategory.columnName" value="<s:property value='infoTcategory.columnName' />" class="ipt_s"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btnbar">
			    <a href="javascript:void(0)" onclick="document.getElementById('form1').submit();"><img src="images/ico_btn_search.gif">搜索</a>
			    <a href="initInfoTcategory.action?parentId=<s:property value='infoTcategory.parentId'/>&rank=<s:property value='infoTcategory.rank'/>"><img src="./images/ico_btn_add.gif" />添加</a> 
		        <s:if test="parentName != null && parentName!=''">
			         <a class="back" href="javascript:void(0)" onclick="history.back()"><img src="images/ico_btn_back.gif" />返回</a>
		        </s:if>
			</div>
		</form>
	</div>
	<dl class="mtab" style="width: 100%">
		<dt>
			<div class="close">
				<a href="#" id="exr1" class="expand" onclick="expand(exr1,t1);"></a>
			</div>
			<p class="notice">栏目信息管理</p>
		</dt>
		<dd id="t1">
			<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="dataTab" id="listTab1">
				<tr>
					<th style="width: 6%;">序号</th>
					<th>栏目名称</th>
					<th style="width: 10%;">栏目级别</th>
					<th style="width: 10%;">排序</th>
					<th style="width: 12%;">创建时间</th>
					<th style="width: 10%;">状态</th>
					<th style="width: 25%;">操作</th>
				</tr>	
				<s:iterator value='infoTcategoryList' status='st'>
					<tr>
						<td class="box"><s:property value="#st.count" /></td>
						<td style="text-align: left; padding-left: 5px;"><s:property value="columnName" /></td>
						<td><s:property value="rank" /></td>
						<td><s:property value="sort" /></td>
						<td><s:date name="createDate" format="yyyy-MM-dd" /></td>
						<td><s:if test="usingFlag==1">启用</s:if><s:elseif test="usingFlag==0">禁用</s:elseif></td>
						<td>
							<a href="initInfoTcategory.action?columnId=<s:property value='columnId'/>&parentId=<s:property value='infoTcategory.parentId'/>&rank=<s:property value='infoTcategory.rank'/>">修改</a>&nbsp;|
							<a href="javascript:void(0)" onclick="del('<s:property value="columnId"/>');">删除</a>&nbsp;|
							<a href="javascript:void(0)" onclick="using('<s:property value="columnId"/>','<s:property value="usingFlag"/>');"><s:if test="usingFlag==0">启用</s:if><s:elseif test="usingFlag==1">禁用</s:elseif></a>&nbsp;|
							<s:if test="infoTcategory.rank<=2">
								<a href="queryInfoTcategoryList.action?infoTcategory.parentId=<s:property value='columnId'/>&infoTcategory.rank=<s:property value='infoTcategory.rank+1'/>">
								<s:if test="infoTcategory.rank ==1">二级栏目</s:if>
								<s:elseif test="infoTcategory.rank ==2">三级栏目</s:elseif>
								</a>&nbsp;|
							</s:if>
							<a href="getInfoTcategoryDetail.action?columnId=<s:property value='columnId'/>">查看</a>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7" class="datatitle"><%@ include file="../../include/pagination.inc" %></td>
				</tr>
			</table>
		</dd>
	</dl>
</div>
</body>
</html>
