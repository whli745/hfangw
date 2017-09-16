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
		<script type="text/javascript">
			
			function saveHoliday() {
				if(jQuery.trim(jQuery("#holidayDate").val())==""){
					Dialog.alertFocus("日期不能为空！", 'holidayDate');
					return false;
				}
				if(jQuery.trim(jQuery("#holidayMemo").val())==""){
					Dialog.alertFocus("备注不能为空！", 'holidayMemo');
					return false;
				}
				var holidayDate = jQuery.trim(jQuery("#holidayDate").val());
                var holiday = new Date(holidayDate.replace(/-/g, "/"));
				var holidayDays = holiday.getDay();
				var holidayTypes = document.getElementsByName("holiday.holidayType");
				var holidayType = "<s:property value='@util.BaseParameter@HOLIDAY_TYPE_W'/>";
				for(var i = 0; i < holidayTypes.length; i++) {
					if(holidayTypes[i].checked) {
						holidayType = holidayTypes[i].value;
					}
				}
				if(holidayDays == 0 || holidayDays == 6) {
					if(holidayType != "<s:property value='@util.BaseParameter@HOLIDAY_TYPE_W'/>") {
						Dialog.alert("周六、周日只能添加为加班！");
						return false;
					}
				} else {
					if(holidayType != "<s:property value='@util.BaseParameter@HOLIDAY_TYPE_H'/>") {
						Dialog.alert("周一至周五只能添加为休息！");
						return false;
					}
				}

				Dialog.confirm('是否确定此操作？',function(){
					document.form1.submit();
				});
			}
		
	</script>
	</head>

	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysHolidayList.action">节假日管理</a><a><s:if test="holiday==null">添加</s:if><s:else>修改</s:else></a>
					</p>
					<a href="querySysHolidayList.action?areaId=<s:property value='areaId' />&areaName=<s:property value='areaName' />" class="back">返回</a>
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
									<p class="title">
									<s:if test="holiday==null">添加</s:if><s:else>修改</s:else>节假日	</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">日期：</td>
								<td width="35%" >
									<input type="text" size="30" id="holidayDate" name="holiday.holidayDate"  readonly class="ipt Wdate" onfocus="WdatePicker()" value="<s:date name="holiday.holidayDate" format="yyyy-MM-dd" />" /><font color="red">&nbsp;*</font>
								</td>
								<td width="15%" class="txtR">类型：</td>
								<td >
									<s:if test="holiday==null">
										<s:iterator value="@util.BaseParameter@HOLIDAY_TYPE">
											<label><input type="radio" name="holiday.holidayType" value='<s:property value="key"/>' <s:if test="key.equals(@util.BaseParameter@HOLIDAY_TYPE_W)">checked</s:if>/><s:property value="value"/></label>
										</s:iterator>
									</s:if>
									<s:else>
										<s:iterator value="@util.BaseParameter@HOLIDAY_TYPE">
										<label><input type="radio" name="holiday.holidayType" value='<s:property value="key"/>' <s:if test="key.equals(holiday.holidayType)">checked</s:if>/><s:property value="value"/></label>
									</s:iterator>
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="txtR">备注：</td>
								<td colspan="3"><textarea class="input_area" id="holidayMemo" name="holiday.holidayMemo" data-maxlength="255"><s:property value="holiday.holidayMemo" escape="false"/></textarea><font color="red">&nbsp;*</font></td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(null)" onclick="saveHoliday();" class="btnG">确定</a>
						<a href="querySysHolidayList.action?areaId=<s:property value='areaId' />&areaName=<s:property value='areaName' />" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
