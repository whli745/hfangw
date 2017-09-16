<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="../../../include/heard.inc"%>
<script type="text/javascript" src="js/zDialog/zDialog.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery("#listTab1 tr:even").addClass("alt");
		jQuery(".listTab tr:not(first,second)").hover(function() {
			jQuery(this).addClass("over")
		}, function() {
			jQuery(this).removeClass("over")
		});
		initTableTitle('listTab1');
	})
	function query(){
		document.form1.submit();
		}
	function setTab(name, cursel, n) {
		for (i = 1; i <= n; i++) {
			var menu = document.getElementById(name + i);
			var con = document.getElementById("con_" + name + "_" + i);
			menu.className = i == cursel ? "hover" : "";
			con.style.display = i == cursel ? "block" : "none";
		}
	}
</script>
</head>

<body id="mouseRight">
    <form action="querySysAreaList.action" name="form1" method="get">
    <input type="hidden" name="topId" value="<s:property value="topId" />" class="ipt_s" />
	<div class="mainDiv">
		<dl class="mtab">
			<dt>
				<p class="position">
					当前位置：<a>系统管理</a><a>基础管理</a><a href="querySysAreaList.action?topId=">区划管理</a>
				</p>
				<s:if test="!topId.equals('20130419093149037845634429844034')"><a href="querySysAreaList.action?topId=" class="back">返回</a></s:if>
			</dt>
			<dd>
				<ul class="inputinfo">
					<li>
						<span>区划代码</span> <input type="text" name="areaCodes" value="<s:property value='areaCodes' />" class="ipt_s" />&nbsp;&nbsp;
						<span>区划名称</span> <input type="text" name="areaNames" value="<s:property value='areaNames' />" class="ipt_s" />
						<input type="button" value="查询" class="btn" onclick="query()" />
					</li>
				</ul>
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
					<caption>
							<p class="title">区划管理</p>
							<p class="btnbars">
								<script type="text/javascript">btnRole('3','?topId=<s:property value="topId" />&areaId=<s:property value="areaId" />&areaCode=<s:property value="areaCode" />&areaName=<s:property value="areaName" />')</script>
							</p>
					</caption>
					<tr>
						<td class="datatitle" style="width: 6%">序号</td>
						<td class="datatitle">区划名称</td>
						<td class="datatitle" style="width: 10%">区划编号</td>
						<td class="datatitle" style="width: 20%">区划级别</td>
						<td class="datatitle"style="width: 25%">操作</td>
					</tr>
					<s:iterator value="sysAreaList" status="index">
						<tr>
							<td class='box'><s:property value="#index.count" />
							</td>
							<td>
								<s:property value="areaName" />
							</td>
							<td>
								<s:property value="areaCode" />
							</td>
							<td>
							   <s:iterator value="@util.BaseParameter@AREA_LEVEL">
           					   <s:if test="key.equals(areaLevel)"><s:property value='value'/></s:if>
           					   </s:iterator>
							</td>
							<td>
								<script type="text/javascript">
									btnRole('1',<s:property value="#request.btnModuleRole" escape="false"/>,'?nextTopId=<s:property value="areaId" />&areaId=<s:property value="areaId" />&topId=<s:property value="topId" />&areaCodes=<s:property value="areaCodes" />&areaNames=<s:property value="areaNames" />')
								</script>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="5">
							<%@ include file="../../../include/pagination.inc" %>
						</td>
					</tr>
				</table>
			</dd>
		</dl>
	</div>
	</form>
</body>
</html>
