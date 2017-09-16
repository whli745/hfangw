<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<%@ include file="../../../include/heard.inc"%>
		<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				jQuery("#listTab1 tr:even").addClass("alt");
				jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
				initTableTitle('listTab1');
			})
			function query(){
			document.myForm.submit();
			}
			
		</script>
	</head>
	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a>节假日管理</a>
					</p>
				</dt>
				<dd>
					<form name="myForm" method="get" action="querySysHolidayList.action">
						<ul class="inputinfo">
							<li>
								<span>日期：</span>
								<span><input type="text" id="holidayDate" name="holiday.holidayDate" readonly class="ipt Wdate" onfocus="WdatePicker()" value="<s:date name="holiday.holidayDate" format="yyyy-MM-dd" />" />
								<font color="red">&nbsp;*</font></span>
								<span>类型：</span>
								<span><select name="holiday.holidayType"  style="width:auto;"  class="xiala">
								<option value="">==请选择==</option>
								<s:iterator value="@util.BaseParameter@HOLIDAY_TYPE">
								<option value='<s:property value="key"/>' <s:if test="key.equals(holiday.holidayType)">selected="selected"</s:if>><s:property value="value"/></option>
								</s:iterator></select>
								</span>
								<input type="button" value="查询" class="btn" onclick="query()" />
							</li>
						</ul>
					</form>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
						<caption>
							<p class="title">节假日管理</p>
							<p class="btnbars"><a href="initSysHoliday.action"><img src="images/ico_btn_plus.gif" />添加</a></p>
						</caption>
						<tr>
							<td class="datatitle" style="width: 6%">序号</td>
							<td class="datatitle" style="width: 20%">日期</td>
							<td class="datatitle" style="width: 15%">类型</td>
							<td class="datatitle">备注</td>
							<td class="datatitle" style="width: 18%">操作</td>
						</tr>
						<s:iterator value="holidayList" status="st">
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td><s:date name="holidayDate" format="yyyy-MM-dd" /></td>
								<td><s:property value='@util.BaseParameter@HOLIDAY_TYPE[holidayType]'/></td>
								<td style="text-align: left"><s:property value="holidayMemo" /></td>
								<td>
									<a href="initSysHoliday.action?oid=<s:property value="oid"/>">修改</a>
									|
									<a href="getSysHoliday.action?oid=<s:property value="oid"/>">查看</a>
									|
									<a href="javascript:void(0);" onclick="Dialog.confirm('是否确定删除？',function(){window.location.href='deleteSysHoliday.action?oid=<s:property value="oid"/>'},'','','','2')">删除</a>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5" class="datatitle">
								<%@ include file="../../../include/pagination.inc" %>
							</td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
	</body>
</html>