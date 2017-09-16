<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<%@ include file="../include/heard.inc"%>
	<script type="text/javascript">
		function showImage( id ){
			window.open("certificationPhotoShow?netUserCertificationId=" + id);
		}
	</script>
</head>
<body>
	<div class="mainDiv">
		<dl class="mtab" style="width:100%">
	   		<dt><p class="notice">查看企业</p></dt>
	       <dd>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formTab">
				<tr>
					<th colspan=4><p class="title" style="text-align: left;padding-left: 10px; font-weight: bold;">企业信息</p></th>
				</tr>
			    <tr>
			        <td width="15%" class="txtR">用户名：</td><td width="35%"><s:property value='netUser.userName'/></td>		
					<td width="15%" class="txtR">登录名：</td><td width="35%"><s:property value='netUser.userName'/></td>
			    </tr>
				<tr>
			        <td width="15%" class="txtR">用户类型：</td><td width="35%">
			        	<s:iterator value="@util.BaseParameter@MENU_OWNER">
			        		<s:if test="key==netUser.userType"><s:property value="value"/></s:if>
			        	</s:iterator>
			        </td>
			        <td width="15%" class="txtR">组织机构代码证：</td><td width="35%"><s:property value='netUser.companyOrganCode'/></td>		
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
			        <td width="15%" class="txtR">单位名称：</td>
			        <td width="35%"><s:property value='netUser.companyName'/></td>
			        <td width="15%" class="txtR">税务登记证号：</td>
			        <td width="35%"><s:property value='netUser.taxNo'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">邮政编码：</td>
			        <td width="35%"><s:property value='netUser.zipCode'/></td>
			        <td width="15%" class="txtR">单位网址：</td>
			        <td width="35%"><s:property value='netUser.webSite'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">微博网址：</td>
			        <td width="35%"><s:property value='netUser.weiBoSite'/></td>
			        <td width="15%" class="txtR">开户银行：</td>
			        <td width="35%"><s:property value='netUser.depositBank'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">银行账号：</td>
			        <td width="35%"><s:property value='netUser.bankAccount'/></td>
			        <td width="15%" class="txtR">联系人：</td>
			        <td width="35%"><s:property value='netUser.contacterName'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">联系人电话：</td>
			        <td width="35%"><s:property value='netUser.contacterTel'/></td>
			        <td width="15%" class="txtR">联系人邮箱：</td>
			        <td width="35%"><s:property value='netUser.contacterEmail'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">单位信息网址：</td>
			        <td width="35%"><s:property value='netUser.companyInfoSite'/></td>
			        <td width="15%" class="txtR">单位介绍：</td>
			        <td width="35%"><s:property value='netUser.companyIntroduction'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">工商注册号：</td>
			        <td width="35%"><s:property value='netUser.companyBusinessLicense'/></td>
			        <td width="15%" class="txtR">企业类型：</td>
			        <td width="35%"><s:property value='netUser.companyType'/></td>
			    </tr>
			    <tr>
			        <td width="15%" class="txtR">注册资金：</td><td width="35%"><s:property value='netUser.companyRegCapital'/></td>		
			        <td width="15%" class="txtR">单位负责人：</td><td width="35%"><s:property value='netUser.companyLegal'/></td>
			    </tr>
			</table>
			</dd>
			
			<dt><p class="notice">用户证件</p></dt>
			<dd id="t1">
				<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="dataTab" id="listTab1">
					<tr>
						<th><input type="checkbox" class="check-tit"/></th>
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
							<td>
								<input type="checkbox" name="userCertificateId" value='<s:property value="id" />'/>
								<input type="hidden" value="<s:property value='id' />" id="ncId"/>
								<input type="hidden" value="" id="checkType"/>
								<input type="hidden" value="<s:property value='ids' />" id="userId"/>
							</td>
							<td class='box'><s:property value="#st.count" /></td>
							<td><s:property value="certificateName" /></td>
							<td><s:property value="certificateNo" /></td>
							<td><s:property value="certificateBeginDate" /></td>
							<td><s:property value="certificateEndDate" /></td>
							<td><a  href="javascript:;" onclick="showImage('<s:property value='id' />');">查看证件</a></td>
							<td>
								<s:if test="certificationStatus.equals(@util.BaseParameter@YES)">
									<a href="javascript:;" style="color: #33CC00;cursor: default;text-decoration: none;">已审核</a>
								</s:if>
								<s:else>
									<a href="javascript:;" style="color: #FF0000;cursor: default;text-decoration: none;">未审核</a>
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
