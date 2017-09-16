<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
	<%@ include file="../../../include/heard.inc"%>
	<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
	<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
	<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
	<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
	<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
	<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
	<script type='text/javascript' src='dwr/interface/AreaManageDwr.js'></script>
	<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
    	<dt>
        	<p class="notice">查看内容发布</p>
        </dt>
		<dd>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
				<tr>
					<th colspan="4"><p class="title" style="text-align: left;padding-left: 26px; font-weight: bold;">内容发布信息</p></th>
				</tr>
				<tr>
					<td width="15%" class="txtR">所属栏目：</td>
					<td width="35%">
						<div style="float: left;">
							<s:property value="infoTcontent.infoTcategory.columnName"/>
						</div>
					</td>
			        <td width="15%" class="txtR">所属区划：</td>
			        <td width="35%">
			        	<s:property value='infoTcontent.sysArea.areaName'/>
			        </td>
			    </tr>
				<tr>
					<td width="15%" class="txtR">新闻主标题：</td>
					<td width="35%" colspan="3">
						<s:property value='infoTcontent.contentMainTitle'/>
					</td>
				</tr>
				<tr>
					<td class="txtR">新闻副标题：</td>
					<td colspan="3">
						<s:property value='infoTcontent.contentSubTitle'/>
					</td>
				</tr>
				<tr>
					<td width="15%" class="txtR">发布单位：</td>
					<td width="35%">
						<s:property value='infoTcontent.issueOrgan'/>
					</td>
					<td width="15%" class="txtR">发布人：</td>
					<td width="35%" >
						<s:property value='infoTcontent.issueUsername'/>
					</td>
				</tr>
				<tr>
					<td class="txtR">是否置顶：</td>
					<td>
						<s:if test="infoTcontent.isTop == 1">是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
					<td class="txtR">推荐：</td>
					<td>
						<s:if test="infoTcontent.recommend == 1">
							是
						</s:if>
						<s:else >
							否
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="txtR">发布时间：</td>
					<td>
						<s:date name='infoTcontent.issurDate' format='yyyy-MM-dd'/>
					</td>
					<td class="txtR">失效时间：</td>
					<td colspan='3'>
						<s:date name='infoTcontent.loseDate' format='yyyy-MM-dd'/>
					</td>
				</tr>
				<tr>
					<td class="txtR">访问量：</td>
					<td>
						<s:property value='infoTcontent.visitorVolume'/>
					</td>
					<td class="txtR">图片或视频新闻：</td>
					<td>
						<s:if test="infoTcontent.isPicVideo == 0">
							普通新闻
						</s:if>
						<s:elseif test='infoTcontent.isPicVideo==1'>
							图片新闻
						</s:elseif>
						<s:else >
							视频新闻
						</s:else >
					</td>
					<td class="txtR">是否启用：</td>
					<td>
						<s:if test="infoTcontent.usingFlag == 1">
							禁用
						</s:if>
						<s:else>
							启用
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="txtR">新闻简介：</td>
					<td colspan="3" >
						<s:property value='infoTcontent.contentAbstract' escape="false"/>
					</td>
				</tr>
				<tr>
					<td class="txtR">新闻内容：</td>
					<td colspan="3">
						<s:property value='infoTcontent.content' escape="false"/>
					</td>
				</tr>
			</table>
			<div class="btnbar">
				<a href="javascript:void(0)" onclick="history.back();"><img src="images/ico_btn_back.gif" />返回</a>
       		</div>
		</dd>
	</dl>
</div>
</body>
</html>
