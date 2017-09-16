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
		<script type="text/javascript" src="./js/jquery.uploadify.v2.0.3.js"></script>
		<script type="text/javascript" src="./js/swfobject.js"></script>
		<link rel="stylesheet" href="./css/uploadify.css" type="text/css"></link>
		<script type="text/javascript">
			function checkAll(){
				if(jQuery.trim(jQuery("#cons_name").val())==""){
					Dialog.alertFocus('顾问姓名不能为空！', 'cons_name');
					return false;
				}
				if(jQuery.trim(jQuery("#motto").val())==""){
					Dialog.alertFocus('服务格言不能为空！', 'motto');
					return false;
				}
				Dialog.confirm('是否确定此操作？',function(){
					document.form1.submit();
				});
			}
		
	</script>
	</head>

	<body style="overflow-x:hidden;">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>网站管理</a><a>楼盘管理</a><a>置业顾问</a><a><s:if test="hfwRealtyConsultant==null">添加</s:if><s:else>修改</s:else>置业顾问</a>
					</p>
					<a href="queryRealtyConsultantList.action" class="back">返回</a>
				</dt>
				<dd>
					<form name="form1" action="editHfwRealtyConsultant.action" method="post">
						<input type="hidden" name="hfwRealtyConsultant.oid"	value="<s:property value="hfwRealtyConsultant.oid"/>" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">
									<s:if test="hfwRealtyConsultant==null">添加</s:if><s:else>修改</s:else>置业顾问</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">顾问姓名：</td>
								<td>
									<input id="cons_name" class="ipt" size="40"	name="hfwRealtyConsultant.cons_name" 
										value="<s:property value="hfwRealtyConsultant.cons_name"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">服务格言：</td>
								<td>
									<input id="motto" class="ipt" size="40"	name="hfwRealtyConsultant.motto" 
										value="<s:property value="hfwRealtyConsultant.motto"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">排序：</td>
								<td>
									<input id="sort" class="ipt" size="40"	name="hfwRealtyConsultant.sort" style="width:50px;"
										value="<s:property value="hfwRealtyConsultant.sort"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">图片：</td>
								<td>
									<input type="file" name="uploadify" id="uploadify" />
									<div id="div_progress"></div>
									<input type="hidden" id="imageName" style="max-width: 300px;" name="hfwRealtyConsultant.pic" value="<s:property value="hfwRealtyConsultant.pic"/>" />
									<s:if test="hfwRealtyConsultant!=null&&hfwRealtyConsultant.pic!=null&&!hfwRealtyConsultant.pic.equals(\"\")">
										<img src="<%=basePath%>buidingmanage/images/<s:property value="hfwRealtyConsultant.pic"/>" id="img" />
									</s:if>
									<s:else>
										<img src="" id="img" />
									</s:else>
									<script>
										window.onload=function() {
											   jQuery("#uploadify").uploadify({
											       'uploader': './images/uploadify.swf', //flash文件的相对路径
											       'script': './upload.action?file_path=buidingmanage/images/',  //后台处理程序的相对路径
											       'fileDataName':'file', //设置上传文件名称,默认为Filedata
											       'cancelImg': './images/cancel.png', //每一个文件上的关闭按钮图标
											       'queueID': 'div_progress', //文件队列的ID，该ID与存放文件队列的div的ID一致
											       'queueSizeLimit':3, //当允许多文件生成时，设置选择文件的个数，默认值：999
											       'fileDesc' : 'jpg、gif、jpeg、png文件', //用来设置选择文件对话框中的提示文本        
											       'fileExt' : '*.jpg;*.gif;*.png;*.jpeg', //设置可以选择的文件的类型
											       'auto' : true, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
											       'multi' : false, //设置为true时可以上传多个文件
											       'simUploadLimit' : 1, //允许同时上传的个数 默认值：1 
											       'sizeLimit':10240000, //上传文件的大小限制
											       'buttonText': '选择图片', //浏览按钮的文本，默认值：BROWSE
											       'displayData': 'percentage',//上传队列显示的数据类型，percentage是百分比，speed是上传速度
											        //回调函数
											       'onComplete': function (evt, queueID, fileObj, response, data){
											        	jQuery("#img").attr("src",'<%=basePath%>buidingmanage/images/'+response);
											        	jQuery('#imageName').val(response);
											       },
											       'onError' : function(event, queueID, fileObj,errorObj){
											    	   if(errorObj.type === "File Size"){
											    		  Dialog.alert("文件最大为1M");
											    		  jQuery("#uploadify").uploadifyClearQueue();
											    	   }
												   },
												   'onQueueFull':function(event,queueSizeLimit){
														Dialog.alert("最多同时上传"+queueSizeLimit+"张图片");
													    return false;
												   }
											    }); 
										}
									</script>
								</td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(0)" onclick="checkAll();" class="btnG">确定</a>
						<a href="queryRealtyConsultantList.action" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
	</body>
</html>
