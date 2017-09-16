<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
<title><s:property value="@util.BaseParemeter@SYSTEM_NAME"/></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css"/>

<%
      response.setHeader("Pragma",   "No-cache");   
      response.setHeader("Cache-Control",   "no-cache");   
      response.setDateHeader("Expires",   0);
	  response.setHeader("Cache-Control", "Public"); 
%>
<link rel="stylesheet" type="text/css" href="./js/xyTree/resources/css/yui-ext.css" />
<link rel="stylesheet" href="./js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="./js/xyTree/util/utilities_2.1.0.js"></script>
<script type="text/javascript" src="./js/xyTree/util/yui-ext.js"></script>
<script language="javascript" src="./js/xyTree/js/dialog.js"></script>
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="./js/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="./js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="./js/zDialog/zDialog.js"></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/AreaManageDwr.js'></script>
<script type='text/javascript' src='dwr/interface/PtSynthetizeAppDwr.js'></script>

<!-- ueditor start -->
<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="./js/ueditor/ueditor.css" />
<!-- ueditor end -->
<link rel="stylesheet" type="text/css" href="./js/wChar/wChar.css" />
<script type="text/javascript" src="./js/wChar/wChar.js" ></script>

<style>
.button_90a {min-width:60px;height:20px;line-height:20px; border-radius:3px; font-size:12px; border:0px;background: #7bbeec;color:#fff;text-align:center; margin:auto;cursor: pointer;}
</style>
<script language="javascript">
$(function(){
	if($('input[id="isPicVideo"]:checked').val() =='<s:property value="@util.BaseParameter@NO"/>'){
	}else{
		 $('#shouXiazai').show();
	}
	<s:if test="infoTcontent.contentSubTitle != null && infoTcontent.contentSubTitle != ''">
		$("#moreInfo").show();
		$("#btn").attr("value","隐藏更多")
	</s:if>
});
function shouXiazai(){
	if($('input[id="isPicVideo"]:checked').val() =='<s:property value="@util.BaseParameter@NO"/>'){
		 $('#shouXiazai').hide();
	 }else{
		 $('#shouXiazai').show();
	 }
}
//区划树
var zTree1;
var setting;
   var isGetNodes = false;
var zNodes =<s:property value="sysAreaJson" escape="false"/>;
// 控制按钮显示
setting = {
	expandSpeed: "",
	callback: {
		beforeExpand: zTreeBeforeExpand,//展开树
		beforeCollapse: zTreeBeforeCollapse,//合并数
		//onClick: treeOnClick,//点击树的节点
		onDblClick: treeOnDblClick
	}
};

jQuery(document).ready(function(){
	zTree1 = jQuery.fn.zTree.init(jQuery('#tree_area'), setting, zNodes);
	var nodes = zTree1.getNodes();
	zTree1.selectNode(nodes[0]);
	zTree1.expandNode(nodes[0], true, null, null, true);
});
var isGetNodes = false;
var curParentNode = null;
function zTreeBeforeExpand(treeId, treeNode) {
	if (!isGetNodes && treeNode.isParent && (!treeNode.nodes || treeNode.nodes.length==0)) {
		curParentNode = treeNode;
		isGetNodes = true;
		var id = curParentNode.id;
	    AreaManageDwr.areaTreeList(id, function(msg){//调用区划DWR返回区划Json
		if (!msg || msg.length == 0) {
				isGetNodes = false;
				return;
			}
			var newNodes = "";
			try {
				newNodes = eval("(" + msg + ")");
			} catch(err) {}
			if (newNodes && newNodes != "") {
				zTree1.addNodes(curParentNode, newNodes, true);
				zTree1.updateNode(curParentNode);
				zTree1.selectNode(zTree1.getNodeByParam("id", curParentNode.id, null));
				zTree1.expandNode(curParentNode);
				isGetNodes = false;
			}
	});
		return false;
	}
	return true;
}

function zTreeBeforeCollapse(treeId, treeNode) { // 合并树
	zTree1.removeChildNodes(treeNode);
	treeNode.isParent=true;
	zTree1.expandNode(treeNode);
}

function treeOnClick(event, treeId, treeNode) {//点击树的节点
	if(!treeNode.open) {//展开
		zTreeBeforeExpand(treeId, treeNode);
	} else {//合并 
		zTreeBeforeCollapse(treeId, treeNode);
		zTree1.expandNode(treeNode);
	}
}

function treeOnDblClick(event, treeId, treeNode) {//双击节点完成选择
	jQuery("#areaId").val(treeNode.id)
	jQuery("#areaName").val(treeNode.name)
	jQuery("#m-areaTree").click();
}
var ueditor = null;
jQuery(document).ready(function() {//加载web编辑器
	ueditor= UE.ui.Editor({
		initialFrameHeight: 300,
	});
	ueditor.render('content');
});
function check(obj) { // 验证表单数据提交
// 	if(jQuery.trim(jQuery('#areaId').val()) == '') {
// 		Dialog.alertFocus("请选择所属区划！");
// 		return false;
// 	}
	var  contentMainTitle = jQuery.trim(jQuery("#contentMainTitle").val());

	if(contentMainTitle == '') {
		jQuery('#contentMainTitle').val('');
		Dialog.alertFocus("内容主标题不能为空！",'contentMainTitle');
		return false;
	}
	if(contentMainTitle.length>50){
		Dialog.alertFocus("内容主标题长度不能超过50字！",'contentMainTitle');
		return false;
	}
	
	var startTime=jQuery("#issurDate").val();  
    var endTime=jQuery("#loseDate").val();  
    　	if (startTime != null &&  endTime != null) {
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
	    if(end<start){
	    	Dialog.alertFocus("失效时间应该不小于发布时间！");
	        return false;  
	    }  
    　	}
	
	if($('#jumpUrlCheckbox').is(':checked')&&$.trim($('#jumpUrl').val()) == ''){
		Dialog.alertFocus("绑定连接地址不能为空！");
		return false;
	}else if($('#jumpUrlCheckbox').is(':checked')){
		$('#jumpUrlFlag').val('<s:property value="@util.BaseParameter@YES"/>');
	}else{
		$('#jumpUrlFlag').val('<s:property value="@util.BaseParameter@NO"/>');
	}
	<s:if test="infoTcontent.contentId == null">
	if(jQuery('#upload_file').val()==''&&jQuery('input[name="infoTcontent.isPicVideo"]:checked').val()==1){
		Dialog.alertFocus("请上传图片！");
		return false;
	}
	</s:if>
	Dialog.confirm('是否确定该操作？',function(){
		//修改页面 有附件文件时   修改为普通新闻时要清空文件主键
		if($('input[id="isPicVideo"]:checked').val() =='<s:property value="@util.BaseParameter@NO"/>'){
			 $('#attaId').val("");
		 }
		/* $.ajax({
	    	type:"post",
	    	cache:false,
	    	url:"saveOrUpdateInfoTcontent.action",
	    	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    	data:$('#myForm').serialize(),  //表单的id
	    	success:function(data){
	    		window.parent.document.location.reload();
	    		Dialog.close();
	    	},
	    	error: function(request) {
	    		Dialog.alertFocus("提交失败！");
	    	}
	   }); */
		document.myForm.submit();
		
	});
}
<s:if test="@util.BaseParameter@YES.equals(infoTcontent.jumpUrlFlag)">
$(function(){
	$('#jumpUrlCheckbox').click();
});
</s:if>
//显示更多		
function show(){
	if($("#btn").attr("value")== "显示更多"){
		$("#moreInfo").show();
		$("#btn").attr("value","隐藏更多")
	}else{
		$("#moreInfo").hide();
		$("#btn").attr("value","显示更多")
	}
	//$("#moreInfo").toggle();
}
</script>
</head>
<body>
<div class="mainDiv">
	<dl class="mtab" style="width:100%">
		<dd>
			<form name="myForm" id="myForm" action="saveOrUpdateInfoTcontent.action" method="post" enctype="multipart/form-data">
				<input type="hidden" name="infoTcontent.contentId" value="<s:property value='infoTcontent.contentId'/>" />
				<input type='hidden' id="attaId" name="infoTcontent.attaId" value="<s:property value='infoTcontent.attaId'/>"/>
				<input type='hidden' name="infoTcontent.columnId" value="<s:property value='infoTcontent.contentId==null?columnId:infoTcontent.columnId'/>"/>
				<input type='hidden' id="jumpUrlFlag" name="infoTcontent.jumpUrlFlag" value="<s:property value='infoTcontent.jumpUrlFlag'/>"/>
				<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="formTab" style='table-layout:fixed;' ><!-- style='table-layout:fixed;' -->
					<tr>
						<td style="width:15%" class="txtR">所属栏目：</td>
						<td style="width:35%" >
							<s:property value="infoTcontent.infoTcategory.columnName"/>
						</td>
				        <td style="width:15%"  class="txtR">所属区划：</td>
				        <td style="width:35%" >
				        	<input type="text" class="input_text" id="areaName" value="<s:property value='infoTcontent.contentId == null? "合肥市":infoTcontent.sysArea.areaName'/>" readonly="readonly"/>
				        	<input type="hidden" class="input_text" id="areaId" name="infoTcontent.areaId" value="<s:property value='infoTcontent.contentId == null? @util.BaseParameter@HEFEI_AREA_ID:infoTcontent.areaId'/>"/>
				        	<input id="m-areaTree" type="button"  value="选择.." class="button_90a"/>
				        	<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
								<div class="ydlg-hd">区划选择器[双击选中]</div>
								<div class="ydlg-bd">
									<ul id="tree_area" class="ztree" ></ul>
								</div>
							</div>
				        </td>
				    </tr>
					<tr>
						<td class="txtR">新闻主标题：</td>
						<td colspan="3">
							<input id="contentMainTitle" name="infoTcontent.contentMainTitle" class="ipt" 
								value="<s:property value='infoTcontent.contentMainTitle'/>" data-maxlength="255"/><font color="red">&nbsp;*</font>
							| <input type='button' id='btn' value='显示更多' onclick='show();' class="button_90a"/>
						</td>
					</tr>
					<tbody id="moreInfo" style="display:none;">
					<tr>
						<td class="txtR">发布单位：</td>
						<td >
							<input id="issueOrgan" name="infoTcontent.issueOrgan" class="ipt" 
								value="<s:property value='infoTcontent.issueOrgan'/>" data-maxlength="255"/>
						</td>
						<td class="txtR">发布人：</td>
						<td>
							<input id="issueUsername" name="infoTcontent.issueUsername" class="ipt" 
								value="<s:property value='infoTcontent.issueUsername'/>" data-maxlength="255"/>
						</td>
					</tr>
					<tr>
						<td class="txtR">绑定连接：</td>
						<td colspan="3">
							<input id="jumpUrl" value="<s:property value='infoTcontent.jumpUrl'/>" name="infoTcontent.jumpUrl" 
								class="ipt" style="display:none;" data-maxlength="200"/><span style="color:red;display:none;" id="jumpUrlTip">*</span>
							<label style="cursor: pointer;" >
								<input type="checkbox" value="" onclick="$('#jumpUrl').toggle('10');$('#jumpUrlTip').toggle('10');" id="jumpUrlCheckbox" style="vertical-align:text-bottom; margin-bottom:2px; *margin-bottom:-2px;" />是否绑定固有连接
							</label>
						</td>
					</tr>
					<tr>
						<td class="txtR">新闻副标题：</td>
						<td colspan="3">
							<input id="contentSubTitle" name="infoTcontent.contentSubTitle" class="ipt" 
								value="<s:property value='infoTcontent.contentSubTitle'/>" data-maxlength="255"/>
						</td>
					</tr>
					<tr>
						<td class="txtR">发布时间：</td>
						<td>
							<input type="text" id="issurDate" name="infoTcontent.issurDate" class="ipt_s Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'loseDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:150px;" value="<s:date name='infoTcontent.issurDate' format='yyyy-MM-dd HH:mm:ss'/>"  readonly="readonly" />
						</td>
						<td class="txtR">失效时间：</td>
						<td>
							<input type="text" id="loseDate" name="infoTcontent.loseDate" class="ipt_s Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'issurDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:150px;" value="<s:date name='infoTcontent.loseDate' format='yyyy-MM-dd HH:mm:ss'/>"  readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="txtR">访问量：</td>
						<td>
							<input id="visitorVolume" name="infoTcontent.visitorVolume" class="ipt" 
								value="<s:property value='infoTcontent.visitorVolume'/>" data-maxlength="10"/>
						</td>
						<td class="txtR">是否置顶：</td>
						<td>
							<s:if test="infoTcontent.isTop == 1">
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="1" checked="checked"/>是</label>
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="0" />否</label>
							</s:if>
							<s:elseif test="infoTcontent.isTop == null || infoTcontent.sTop == ''">
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="1" checked="checked"/>是</label>
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="0" />否</label>
							</s:elseif>
							<s:else>
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="1" />是</label>
								<label><input type="radio" name="infoTcontent.isTop" id="isTop" value="0" checked="checked"/>否</label>
							</s:else>
						</td>
					</tr>
					<tr>
						<td class="txtR">内容简介：</td>
						<td colspan="3" >
							<input type="text" class="ipt" id="contentAbstract" name="infoTcontent.contentAbstract" 
								value="<s:property value='infoTcontent.contentAbstract'/>" data-maxlength="255"/>
						</td>
					</tr>
					</tbody>
					<tr>
						<td class="txtR"><span style="color: #ff0000">红包：</span></td>
						<td id="hongb_td" colspan="3">
				<s:if test="infoTcontent == null || infoTcontent.packetList == null || infoTcontent.packetList.size()==0">
					<input type="text" value="" name="packetList[0].packetName" class="ipt" style="width: 60px;"/>
					<input type="text" value="" name="packetList[0].packetMoney" class="ipt" style="width: 60px;"/>
					<input type="text" value="" name="packetList[0].startTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
					<input type="text" value="" name="packetList[0].endTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
					<a href="javascript:Happend_ipts();">添加</a>&nbsp;<span style="color:#FF6600;">[示例：依次填写 <em>红包名称</em> <em>红包金额</em> <em>开始日期</em> <em>结束日期</em>&nbsp;或不填写]</span>
				</s:if>
				<s:else>
					<s:iterator value="infoTcontent.packetList" status="idx">
						<div style="margin-top:2px;" id="Hdiv_<s:property value="#idx.index"/>">
						<input type="text" value="<s:property value="packetName"/>" name="packetList[<s:property value="#idx.index"/>].packetName" class="ipt" style="width: 60px;"/>
						<input type="text" value="<s:property value="packetMoney"/>" name="packetList[<s:property value="#idx.index"/>].packetMoney" class="ipt" style="width: 60px;"/>
						<input type="text" value="<s:date name='startTime' format='yyyy-MM-dd'/>" name="packetList[<s:property value="#idx.index"/>].startTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
						<input type="text" value="<s:date name='endTime' format='yyyy-MM-dd'/>" name="packetList[<s:property value="#idx.index"/>].endTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
						<s:if test="#idx.index == 0">
							<a href="javascript:Happend_ipts();">添加</a>&nbsp;&nbsp;<span style="color:#FF6600;">[示例：依次填写 <em>红包名称</em> <em>红包金额</em> <em>开始日期</em> <em>结束日期</em>&nbsp;或不填写]</span>
						</s:if>
						<s:else>
							<a href="javascript:Hdel_ipts(<s:property value="#idx.index"/>)">删除</a></div>
						</s:else>
						</div>
					</s:iterator>
				</s:else>
				<script>
					var idx = '<s:property value="housingProj.packetList.size"/>';
					function Happend_ipts(){
						if(idx == ''){
							idx = 0;
						}
						++idx;
						var html = '<div style="margin-top:2px;display:block;" id="Hdiv_'+idx+'"><input type="text" value="" name="packetList['+idx+'].packetName" class="ipt" style="width: 60px;"/>'
								+'&nbsp;<input type="text" value="" name="packetList['+idx+'].packetMoney" class="ipt" style="width: 60px;"/>'
								+'&nbsp;<input type="text" value="" name="packetList['+idx+'].startTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>'
								+'&nbsp;<input type="text" value="" name="packetList['+idx+'].endTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>'
								+'&nbsp;<a href="javascript:Hdel_ipts('+idx+')">删除</a></div>';
						jQuery('#hongb_td').append(html);
					}
					function Hdel_ipts(d_idx){
						jQuery('#Hdiv_' + d_idx).remove();
					}
				</script>
			</td>
					</tr>
					<tr>
						<td class="txtR">图片或视频新闻：</td>
						<td>
							<s:if test="infoTcontent==null || infoTcontent.isPicVideo == null ||infoTcontent.isPicVideo == 0">
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()"  value="0" checked="checked"/>普通新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="1" />图片新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="2" disabled="disabled"/>视频新闻</label>
							</s:if>
							<s:elseif test='infoTcontent.isPicVideo==1'>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="0" />普通新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="1" checked="checked"/>图片新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="2" disabled="disabled"/>视频新闻</label>
							</s:elseif>
							<s:else >
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="0" />普通新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="1" />图片新闻</label>
								<label><input type="radio" name="infoTcontent.isPicVideo" id="isPicVideo" onclick="shouXiazai()" value="2" checked="checked" disabled="disabled"/>视频新闻</label>
							</s:else >
						 </td>
						 <td class="txtR">是否启用：</td>
						<td>
							<input type="hidden" name="infoTcontent.needCheck" id="needCheck" value="1" />
							<s:if test="infoTcontent==null || infoTcontent.usingFlag == '' ||infoTcontent.usingFlag == 0">
								<label><input type="radio" name="infoTcontent.usingFlag" id="usingFlag" value="1" />启用</label>
								<label><input type="radio" name="infoTcontent.usingFlag" id="usingFlag" value="0" checked="checked"/>禁用</label>
							</s:if>
							<s:else>
								<label><input type="radio" name="infoTcontent.usingFlag" id="usingFlag" value="1" checked="checked"/>启用</label>
								<label><input type="radio" name="infoTcontent.usingFlag" id="usingFlag" value="0" />禁用</label>
							</s:else>
						</td>
					</tr>
					<tbody id="shouXiazai" style="display:none;">
					<tr>
						<td class="txtR">文件上传：</td>
						<td colspan="3">
							<input id="upload_file"  size="30" name="atta" type="file" style="height:25px;line-height:24px;padding: 0 10px;cursor: pointer;width:500px;"/><font color="red">&nbsp;*</font>
						</td>
					</tr>
					<s:if test="infoTcontent !=null">
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
					</tbody>
					<tr>
						<td colspan="4" >
							 <!-- 加载编辑器的容器 -->
						     <script id="content" name="infoTcontent.content" type="text/plain" style="height:250px;width:894px;margin:0 auto;"><s:property value='infoTcontent.content' escape='false'/></script>
						</td>
					</tr>
					
				</table>
				<div class="btnbar">
					<s:if test="infoTcontent.contentMainTitle==null"><a href="javascript:void(0)" onclick="check();"><img src="./images/ico_btn_add.gif" />添加</a></s:if>
					<s:else><a href="javascript:void(0)" onclick="check();"><img src="images/ico_btn_modi.gif" />修改</a></s:else>
					<a href="javascript:void(0)" onclick="Dialog.close();"><img src="images/ico_btn_cancel.gif" />关闭</a>
        		</div>
			</form>
		</dd>
	</dl>
</div>
<script>
var btnIds = ['m-areaTree']; //按钮名称
var tanchuDivs = ['tree-area']; //展示div名称
for ( var i = 0; i < btnIds.length; i++) {
	var td = new HelloWorld(btnIds[i], tanchuDivs[i] ,true);
	YAHOO.ext.EventManager.onDocumentReady(td.init, td, true);
}
</script>

</body>
</html>
