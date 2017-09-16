<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<%@ include file="../../../include/heard.inc"%>
		<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
		<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
		<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
		<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
		<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
	</head>

	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysHolidayList.action">节假日管理</a><a>查看</a>
					</p>
				</dt>
				<dd>
					<form name="form1" action="saveSysHoliday.action" method="post">
						<input type="hidden" name="holiday.oid"	value="<s:property value="holiday.oid"/>" />
						<input type="hidden" name="areaId" value="<s:property value='areaId'/>"/>
						<input type="hidden" name="areaName" value="<s:property value='areaName'/>"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">查看</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">日期：</td>
								<td width="35%" ><s:date name="holiday.holidayDate" format="yyyy-MM-dd" /></td>
								<td width="15%" class="txtR">类型：</td>
								<td><s:property value='@util.BaseParameter@HOLIDAY_TYPE[holiday.holidayType]'/></td>
							</tr>
							<tr>
								<td class="txtR">备注：</td>
								<td colspan="3"><s:property value="holiday.holidayMemo" escape="false"/></td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="querySysHolidayList.action?areaId=<s:property value='areaId' />&areaName=<s:property value='areaName' />" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
