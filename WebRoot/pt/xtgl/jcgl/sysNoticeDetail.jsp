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
		<%@include file="../../../include/heard.inc" %>
		<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">
						当前位置：<a>系统管理</a><a>基础管理</a><a href="queryNoticeList.action">公告管理</a><a>查看</a>
					</p>
					<a href="javascript:void(0)" class="back" onclick="history.back()">返回</a>
				</dt>
				<dd>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
						<tr>
							<th colspan="4"><p class="title">查看公告</p></th>
						</tr>
						<tr>
							<td width="15%" class="txtR">公告标题：</td>
							<td width="35%"><s:property value='sysNotice.noticeTitle' /></td>
							<td width="15%" class="txtR">公告类型：</td>
							<td width="35%"><s:property value="@util.BaseParameter@NOTICE_TYPE[sysNotice.noticeType]"/></td>
						</tr>
						<tr>
							<td width="15%" class="txtR">公告标题：</td>
							<td width="85%" colspan="3"><s:property value='sysNotice.sysArea.areaName' /></td>
						</tr>
						<tr>
							<td class="txtR">公告时间类型：</td>
							<td colspan="3"><s:property value='@util.BaseParameter@NOTICE_DATE_TYPE[sysNotice.noticeDateType]' /></td>
							<%-- <td class="txtR">业务类型：</td>
							<td><s:property value='getDictName(sysNotice.ywType)' /></td> --%>
						</tr>
						<s:if test="sysNotice.noticeDateType==@util.BaseParameter@NOTICE_DATE_TYPE_2">
						<tr>
							<td class="txtR">公告失效日期：</td>
							<td colspan="3"><s:date name='sysNotice.noticeEndDate' format="yyyy-MM-dd"/></td>

						</tr>
						</s:if>
						<tr>
							<td class="txtR">发布时间：</td>
							<td colspan="3"><s:date name='sysNotice.insertDate' format="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td class="txtR">附件：</td>
							<td colspan="3">
								<s:if test="sysNotice.noticeAtta!=null&&sysNotice.noticeAtta!=''">
									<p><a href="FileDownload?filepath=<s:property value='sysNotice.noticeAtta'/>&dispname=公告附件">点击下载</a></p>
								</s:if>
								<s:else><span style="color:#999999;">无附件</span></s:else>
							</td>
						</tr>
						<tr>
							<td class="txtR">公告内容：</td>
							<td colspan="3" >
								<!-- 加载编辑器的容器 -->
								<script id="noticeMemo" name="sysNotice.noticeMemo" type="text/plain" style="width:100%;margin:0 auto;">
										<s:property value='sysNotice.noticeMemo' escape='false'/>
    					 	 		</script>
								<!-- 实例化编辑器 -->
								<script type="text/javascript">
								//页面加载完成后执行JS
								jQuery(document).ready(function() {
									var ueditor= UE.ui.Editor({
										toolbars: null,
										readonly : true,
										wordCount: false,
										elementPathEnabled: false
									});
									ueditor.render('noticeMemo');
								});
								</script>
							</td>
						</tr>
					</table>
					<div class="btnbarBig">
						<a href="javascript:void(0)" class="btnO" onclick="history.back()">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
