<%@ page import="java.util.Date" %>
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
		<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
		<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<style type="text/css">
			.radioClass{
				cursor: hand;
			}
		</style>
		<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
		<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
		<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		<script type='text/javascript' src='dwr/interface/AreaManageDwr.js'></script>
		<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>
		<script type="text/javascript">
		function clearLog() {
			Dialog.confirm('清理后无法恢复，是否确定清理？',function(){ 
				myForm.action='clearSysLog.action';
				myForm.submit();
			},'','','','2');
		}
	</script>
	</head>
	<body id="mouseRight">
		<div class="mainDiv">
			<%--<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
				<div class="ydlg-hd">区划选择器[双击选中]</div>
				<div class="ydlg-bd">
					<ul id="tree_area" class="ztree" ></ul>
				</div>
			</div>--%>
			<dl class="mtab">
				<dt>
				<p class="position">
					当前位置：<a>网站管理</a><a>领取人管理</a>
				</p>
				</dt>
				<dd>
					<form id="myForm" name="myForm" action="queryHFWContantsList.action" method="post">
						<ul class="inputinfo">
							<li>
								<span>姓名：</span>
								<input type="text" name="q.linkMan" value="<s:property value='q.linkMan' />" class="ipt_s" />
								<span>手机：</span>
								<input type="text" name="q.linkPhone" value="<s:property value='q.linkPhone' />" class="ipt_s" />
								<span>是否领取：</span>
								<label>
								<input type="radio" name="q.status" value="0" onclick="serach();" checked="checked" class="radioClass"
									   style="float: left;height:23px; line-height:23px;"/><span>全部</span></label>
								<label>
								<input type="radio" name="q.status" value="1" onclick="serach();" class="radioClass"
									   <s:if test="q.status == 1">checked="checked" </s:if>
									   style="float: left;height:23px; line-height:23px;"/><span>未领取</span></label>
								<label>
								<input type="radio" name="q.status" value="2" onclick="serach();" class="radioClass"
									   <s:if test="q.status == 2">checked="checked" </s:if>
									   style="float: left;height:23px; line-height:23px;"/><span>已领取</span></label>
								<input type="submit" value="查询" class="btn" />
							</li>
						</ul>
					</form>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
						<caption><p class="title">领取人列表</p></caption>
						<tr>
							<td class="datatitle" style="width: 5%">序号</td>
							<td class="datatitle" style="width: 6%">姓名</td>
							<td class="datatitle" style="width: 10%">手机</td>
							<td class="datatitle" >楼盘/活动</td>
							<td class="datatitle" style="width: 10%">红包名称</td>
							<td class="datatitle" style="width: 10%">红包金额</td>
							<td class="datatitle" >有效时间</td>
							<td class="datatitle" style="width: 10%">领取时间</td>
							<td class="datatitle" style="width: 10%">操作</td>
						</tr>			
						<s:iterator value="rp.resultList" status="st">
							<tr>
								<td class="box"><s:property value="#st.count" /></td>
								<td><s:property value="linkMan" /></td>
								<td style="text-align: center;"><s:property value="linkPhone" /></td>
								<s:if test="packet.housePro != null">
									<td style="text-align: center;cursor:hand;" title="<s:property value='packet.housePro.proj_name'/>"><s:property value="packet.housePro.proj_name" /></td>
								</s:if>
								<s:if test="packet.news != null">
									<td style="text-align: center;cursor:pointer;" title="<s:property value='packet.news.contentMainTitle'/>"><s:property value="packet.news.contentMainTitle" /></td>
								</s:if>
								<td style="text-align: center;cursor:pointer;" title="<s:property value='packet.packetName'/>"><s:property value="packet.packetName" /></td>
								<td style="text-align: center;"><s:property value="packet.packetMoney" /></td>
								<td><s:date name="packet.startTime" format="yyyy/MM/dd"/>-<s:date name="packet.endTime" format="yyyy/MM/dd"/></td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td>
									<s:if test="(packet.isDel==1 && status == 1) || currentDate.compareTo(packet.endTime) > 0 ">
										活动已结束
									</s:if>
									<s:else>
										<s:if test="status == 1" >
											<%--<a href='HfwContactsEdit.action?oid=""&h_flag=1'/><span style="color: blue;">领取</span></a>--%>
											<a href='javascript:markHandle("<s:property value='oid' />",2)'/><span style="color: blue;">标记为领取</span></a>
										</s:if>
										<s:else>
											已领取
										</s:else>
									</s:else>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="9" class="datatitle">
								<%@ include file="../../../include/pagination.inc" %>
							</td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
	</body>
	<script type="text/javascript">
		function markHandle(oid,flag){
			common.confirm("您确定要领取该红包吗？",function(){
				jQuery.ajax({
					type: "POST",
					url: "hfwContactsEdit.action?random=" + Math.random(),
					data: 'q.oid=' + oid + '&h_flag=' + flag,
					success: function(msg){
						if(msg!=''){
							location.reload();
						}
					}
				});
			},50);
		}
		
		function serach(){
			document.myForm.submit();
		}

	</script>
</html>
