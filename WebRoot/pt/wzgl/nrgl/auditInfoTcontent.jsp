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
<!-- 配置文件 -->
<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
function check() {
	if(jQuery.trim(jQuery("#checkFlag").val()) == '2') {//审核不通过，一定要填写审核意见 
		var checkOpinion=jQuery.trim(jQuery("#checkOpinion").val());
		 if(checkOpinion==""){
			 Dialog.alert("审核不通过时，审核意见不能为空！");
			return false;
		}
		if(checkOpinion.length>2500){
			Dialog.alert("审核意见不能超过2500个字！");
			return false;
		}
	}
	Dialog.confirm('是否确定此操作？',function(){
		$.ajax({
	    	type:"post",
	    	cache:false,
	    	url:"addOrUpdateAuditInfoContent.action",
	    	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	data:$('#form1').serialize(),  //表单的id
	    	success:function(data){
	    		window.parent.document.location.reload();
	    		Dialog.close();
	    	},
	    	error: function(request) {
	    		Dialog.alertFocus("审核失败！");
	    	}
	   });
		//document.form1.submit();
	});
}
</script>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
    	<dt>
        	<p class="notice">查看内容发布</p>
        </dt>
		<dd>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab" >
				<tr>
					<th colspan="4"><p class="title" style="text-align: left;padding-left: 26px; font-weight: bold;">栏目信息</p></th>
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
					<td width="25%" class="txtR">发布人：</td>
					<td >
						<s:property value='infoTcontent.issueUsername'/>
					</td>
				</tr>
				<tr>
					<td class="txtR">发布时间：</td>
					<td>
						<s:date name='infoTcontent.issurDate' format='yyyy-MM-dd HH:mm:ss'/>
					</td>
					<td class="txtR">失效时间：</td>
					<td>
						<s:date name='infoTcontent.loseDate' format='yyyy-MM-dd HH:mm:ss'/>
					</td>
				</tr>
				<tr>
					<td class="txtR" width="15%">访问量：</td>
					<td>
						<s:property value='infoTcontent.visitorVolume'/>
					</td>
					<td class="txtR">是否置顶：</td>
					<td>
						<s:if test="infoTcontent.isTop == 1">是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
				</tr>
				<tr>
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
					<td class="txtR" >是否启用：</td>
					<td>
						<s:if test="infoTcontent.usingFlag == 0">
							禁用
						</s:if>
						<s:else>
							启用
						</s:else>
					</td>
				</tr>
				<s:if test="infoTcontent.isPicVideo != 0">					
					<tr>
						<td width="15%" class="txtR">文件下载：</td>
						<td width="85%" colspan="3">
							<s:if test="infoTcontent.fileAtta.attaName != null">
								<a href="FileDownload?filepath=<s:property value='infoTcontent.fileAtta.attaPath'/>&dispname=<s:property value='infoTcontent.fileAtta.attaName'/>"><s:property value="infoTcontent.fileAtta.attaName"/></a>
							</s:if>
							<s:else>无附件</s:else>
						</td>		
					</tr>
				</s:if>
				<tr>
					<td class="txtR" width="15%">新闻简介：</td>
					<td colspan="3" >
						<s:property value='infoTcontent.contentAbstract'  escape="false"/>
					</td>
				</tr>
				<tr>
					<td class="txtR" width="15%">新闻内容：</td>
					<td colspan="3" width="85%">
							<script id="content" name="infoTcontent.content" type="text/plain" style="height:250px;">
								<s:property value='infoTcontent.content' escape='false'/>
    					 	 </script>
						     <!-- 实例化编辑器 -->
						     <script type="text/javascript">
						        var ue = UE.getEditor('content');
						        ue.setHeight(500);
						     </script>
					</td>
				</tr>
			</table>
		<form name="form1" id="form1" method="post">
		<input type='hidden' id="contentId" name="contentId" value="<s:property value='infoTcontent.contentId'/>"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab">
				<tr>
					<th colspan="4"><p class="title" style="text-align: left;padding-left: 26px; font-weight: bold;">内容发布审核</p></th>
				</tr>
				<tr>
					<td width="15%" class="txtR">是否通过：</td>
					<td colspan="3" >
						<select id="checkFlag" name="checkFlag">
							<option value="1" <s:if test="infoTcontent.checkFlag==1">selected</s:if>>通过</option>
							<option value="2" <s:if test="infoTcontent.checkFlag==2">selected</s:if> >不通过</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%" class="txtR">审核意见：</td>
					<td colspan="3" >
						<textarea id="checkOpinion" name="checkOpinion" rows="6" cols="100"><s:property value='infoTcontent.checkOpinion'/></textarea>
					</td>
				</tr>
			</table>
			<div class="btnbar">
				<a href="javascript:void(0)" class="btnG" onclick="check()"><img src="images/ico_btn_save.gif"/>确定</a>
				<a href="javascript:void(0)" onclick="Dialog.close();"><img src="images/ico_btn_cancel.gif" />关闭</a>
       		</div>
       	</form>
		</dd>
	</dl>
</div>
</body>
</html>
