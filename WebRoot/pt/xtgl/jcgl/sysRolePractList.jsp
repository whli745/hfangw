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
		<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				jQuery("#listTab1 tr:even").addClass("alt");
				jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
				initTableTitle('listTab1');
			});
	</script>
	</head>
	<body id='mouseRight'>
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>政府采购</a><a>企业管理</a><a>角色管理</a>
					</p>
				</dt>
				<dd>
					<ul class="inputinfo">
						<li>
							<form name="form1" action="querySysRolePractList.action" method="get">
								<span>角色名称</span>
								<input type="text" name="roleName" value="<s:property value="roleName"/>" class="ipt_s" />
								<input type="submit" value="查询" class="btn" />
							</form>
						</li>
					</ul>
					<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="listTab" id="listTab1">
						<caption>
								<p class="title">角色权限列表	</p>
								<p class="btnbars">
									<a href="initSysRolePract.action">
									<img src="./images/ico_btn_plus.gif" />添加</a>
								</p>
						</caption>
						<tr>
							<td class="datatitle" style="width: 6%">序号</td>
							<td class="datatitle">角色名称</td>
							<td class="datatitle" style="width: 16%">角色状态</td>
							<td class="datatitle" style="width: 16%">操作</td>
						</tr>
						<s:iterator value="sysRolePractList" status="idx">
							<tr>
								<td class='box'><s:property value="#idx.count" /></td>
								<td><s:property value="roleName" /></td>
								<td>
									<s:iterator value='@util.BaseParameter@STATUS'>
										<s:if test="key.equals(roleStatus)">
											<s:property value="value" />
										</s:if>
									</s:iterator>
								</td>
								<td>
									<a href="initSysRolePract.action?roleId=<s:property value='roleId'/>">修改</a>&nbsp;|&nbsp;<a href="javascript:void(0)">删除</a>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="4" class="datatitle">
								<%@ include file="../../../include/pagination.inc"%>
							</td>
						</tr>
					</table>
				</dd>
			</dl>
		</div>
	</body>
</html>
