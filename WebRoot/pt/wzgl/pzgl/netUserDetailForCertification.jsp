<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
	<%@ include file="../include/heard.inc"%>
	<script type="text/javascript">
		function doAudit( id ){
			
			$.layer({
			    type: 2,
			    shade: [0],
			    fix: false,
			    title: "用户证件审核",
			    maxmin: true,
			    iframe: {src : 'openAuditPage?ids=' + $("#userId").val() + "&ucId=" + id},
			    area: ['500px' , '300px'],
			    close: function(index){
			        //layer.msg('您获得了子窗口标记：' + layer.getChildFrame('#name', index).val(),3,1);
			    }
			}); 
		}
		
		function doSubmit(){
			location.href = "doAuditCertifiction?ucId=" + $("#ncId").val()
			+ "&checkType=" + $("#checkType").val() + "&ids="
			+ $("#userId").val() + "&checkContent=" + encodeURI($("#content").val(),"utf-8");
		}
		
		function showImage( id ){
			window.open("certificationPhotoShow?netUserCertificationId=" + id);
		}
	</script>
</head>
<body>
	<div class="mainDiv">
		<dl class="mtab" style="width:100%">
	   		<dt><p class="notice">查看用户</p></dt>
	       <dd>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTab">
				<tr>
					<th colspan=4><p class="title" style="text-align: left;padding-left: 10px; font-weight: bold;">用户信息</p></th>
				</tr>
			    <tr>
			        <td width="15%" class="txtR">用户名：</td><td width="35%"><s:property value='netUser.userName'/></td>		
					<td width="15%" class="txtR">登录名：</td><td width="35%"><s:property value='netUser.userName'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">性别：</td><td width="35%">
			        	<s:iterator value="@util.BaseParameter@PERSON_SEX">
			        		<s:if test="key==netUser.sex"><s:property value="value"/></s:if>
			        	</s:iterator>
			        </td>		
					<td width="15%" class="txtR">出生年月日：</td><td width="35%">
						<s:date name="netUser.birthday" format="yyyy-MM-dd"/>
					</td>
			    </tr>
				<tr>
			        <td width="15%" class="txtR">用户类型：</td><td width="35%">
			        	<s:iterator value="@util.BaseParameter@MENU_OWNER">
			        		<s:if test="key==netUser.userType"><s:property value="value"/></s:if>
			        	</s:iterator>
			        </td>
			        <td width="15%" class="txtR">证件号码：</td><td width="35%"><s:property value='netUser.userCardno'/></td>		
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">账号状态(是否激活)：</td><td width="35%">
			        	<s:iterator value="@util.BaseParameter@YES_NO">
			        		<s:if test="key==netUser.userActivating"><s:property value="value"/></s:if>
			        	</s:iterator>
			        </td>
			        <td width="15%" class="txtR">账号创建时间：</td><td width="35%">
			        	<s:date name="netUser.userCreatedate" format="yyyy-MM-dd"/>
			        </td>		
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">邮政编码：</td><td width="35%"><s:property value='netUser.zipCode'/></td>
			        <td width="15%" class="txtR">QQ号码：</td><td width="35%"><s:property value='netUser.qq'/></td>		
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">目前所在区域：</td>
			        <td width="35%"><s:property value='netUser.userCurrentArea'/></td>
			        <td width="15%" class="txtR">是否本地户：</td>
			        <td width="35%"><s:property value='netUser.userCensus'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">联系地址：</td><td width="35%"><s:property value='netUser.userAddress'/></td>		
			        <td width="15%" class="txtR">联系电话：</td><td width="35%"><s:property value='netUser.userTel'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">移动电话：</td><td width="35%"><s:property value='netUser.userPhone'/></td>		
			        <td width="15%" class="txtR">电子邮箱：</td><td width="35%"><s:property value='netUser.userEmail'/></td>
			    </tr>
			</table>
			</dd>
			<dd id="t1">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="dataTab" id="listTab1">
					<tr>
						<!-- <th><input type="checkbox" class="check-tit"/></th> -->
						<th style="width:6%">序号</th>
						<th style="">证件名称</th>
						<th style="">证件编码</th>
						<th style="">证件有效开始日期</th>
						<th style="">证件有效结束日期</th>
						<th style="">查看证件</th>
						<th style="">操作</th>
					</tr>
					<s:iterator value="certificates" status="st">
						<tr>
							<%-- <td>
								<input type="checkbox" name="userCertificateId" value='<s:property value="id" />'/>
								<input type="hidden" value="<s:property value='id' />" id="ncId"/>
								<input type="hidden" value="" id="checkType"/>
								<input type="hidden" value="<s:property value='ids' />" id="userId"/>
							</td> --%>
							<td class='box'><s:property value="#st.count" /></td>
							<td><s:property value="certificateName" /></td>
							<td><s:property value="certificateNo" /></td>
							<td><s:property value="certificateBeginDate" /></td>
							<td><s:property value="certificateEndDate" /></td>
							<td><a href="javascript:;" onclick="showImage('<s:property value='id' />');">查看证件</a></td>
							<td>
								<s:if test="!certificationStatus.equals(@util.BaseParameter@NO)">
									<a href="javascript:;" style="color: #BBBBBB;cursor: default;text-decoration: none;">已审核</a>
								</s:if>
								<s:else>
									<a href="javascript:doAudit('<s:property value='id' />');">审核</a> 
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
			</dd>
		</dl>
		<div class="btnbar">
			<a href="javascript:window.history.back();" class="btnO"><img src="images/ico_btn_back.gif" />返回</a>
      	</div>
	</div>
</body>
</html>
