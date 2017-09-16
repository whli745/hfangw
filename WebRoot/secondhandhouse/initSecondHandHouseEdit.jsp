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
		<link href="./swfupload/css/swfupload-default.css" rel="stylesheet"type="text/css" />
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
		<script type="text/javascript" src="./js/imgpreview.full.0.22.jquery.js"></script>
		<!-- ueditor start -->
		<script type="text/javascript" src="./js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="./js/ueditor/ueditor.all.js"></script>
		<link rel="stylesheet" type="text/css" href="./js/ueditor/ueditor.css" />
		<style type="text/css">
			.pic_a{background-image:url(images/image.png); background-repeat:no-repeat;padding-left: 20px;color:#222;}
			.pic_div{background-color: #E5F3F9;margin:0 2px;}
			.pic_del_a{}
			em {text-decoration: underline;}
		</style>
		<script type="text/javascript">
		function checkAll(){
			if(jQuery.trim(jQuery("#house_name").val())==""){
				Dialog.alertFocus('楼盘名称不能为空！', 'roleName');
				return false;
			}
			if(jQuery.trim(jQuery("#areaId").val())==""){
				Dialog.alertFocus('请选择所属区划！', 'areaName');
				return false;
			}
			if(isNaN(jQuery.trim(jQuery("#selling_price").val()))){
				common.alertFocus('售价（万）只能为数字，请检查！','selling_price');
				return ;
			}
			if(isNaN(jQuery.trim(jQuery("#refe_down_payment").val()))){
				common.alertFocus('参考首付（万）只能为数字，请检查！','refe_down_payment');
				return ;
			}
			if(isNaN(jQuery.trim(jQuery("#building_age").val()))){
				common.alertFocus('建造年代只能为数字，请检查！','building_age');
				return ;
			}
			var pics = "";
			jQuery('#pics_div_1').find('a[class="pic_a"]').each(function(){
				pics += ";" + jQuery(this).attr('href');
			});
			jQuery('#pics_1').val(pics);
			var pics = "";
			jQuery('#pics_div_2').find('a[class="pic_a"]').each(function(){
				pics += ";" + jQuery(this).attr('href');
			});
			jQuery('#pics_2').val(pics);
			Dialog.confirm('是否确定此操作？',function(){
				document.form1.submit();
			});
		}
		
		function chooseLatlng(){
// 			var bodyWidth=parent.document.body.clientWidth;//网页可见区域宽
		    var bodyHeight=parent.document.documentElement.clientHeight;//网页可见区域高
// 			var width = bodyWidth - 15;
		  	var height = bodyHeight - 175;
			layer.open({
			  title:'请定位楼盘',
			  type: 2,
			  area: ['650px',height + 'px'],
			  fix: true, //不固定
			  maxmin: true,
			  content: 'buidingmanage/map.jsp'
			});
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
				onClick: treeOnClick,//点击树的节点
				onDblClick: treeOnDblClick
			},
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
			return;
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
	</script>
	</head>

	<body style="overflow-x:hidden;">
		<div class="mainDiv">
			<dl class="mtab">
				<dt>
					<p class="position">当前位置：<a>网站管理</a><a>楼盘管理</a><a>二手房源</a><a><s:if test="secondHandHouse==null">添加</s:if><s:else>修改</s:else>二手房源</a>
					</p>
					<a href="querySecondHandHouseList.action" class="back">返回</a>
				</dt>
				<dd>
					<form name="form1" action="secondHandHouseEdit.action" method="post">
						<input type="hidden" name="secondHandHouse.oid"	value="<s:property value="secondHandHouse.oid"/>" />
						<input type="hidden" name="secondHandHouse.del_flag"	value="<s:property value="secondHandHouse.del_flag" />" />
						<input type="hidden" name="secondHandHouse.refe_month_for"	value="<s:property value="secondHandHouse.refe_month_for" />" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">
									<s:if test="secondHandHouse==null">添加</s:if><s:else>修改</s:else>二手房源</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">房源名称：</td>
								<td>
									<input id="house_name" class="ipt" size="40"	name="secondHandHouse.house_name" 
										value="<s:property value="secondHandHouse.house_name"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属区划：</td>
								<td>
									<input type="text" class="input_text" id="areaName" value="<s:property value='secondHandHouse.sysArea.areaName'/>" readonly="readonly" style="width: 160px;"/>
						        	<input type="hidden" class="input_text" id="areaId" name="secondHandHouse.area_id" value="<s:property value='secondHandHouse.area_id'/>"/><font color="red">&nbsp;*</font>
						        	<input id="m-areaTree" type="button" class="btn_select" value="选择..." />
									<div id="tree-area" style="visibility: hidden; position: absolute; top: 0px; width:0px;">
										<div class="ydlg-hd">区划选择器[双击选中]</div>
										<div class="ydlg-bd">
											<ul id="tree_area" class="ztree" ></ul>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">所在小区：</td>
								<td>
									<input id="hous_acreage" class="ipt" size="40"	name="secondHandHouse.village" 
										value="<s:property value="secondHandHouse.village"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">地址：</td>
								<td>
									<input id="address" class="ipt" size="40"	name="secondHandHouse.address" 
										value="<s:property value="secondHandHouse.address"/>" data-maxlength="250" style="width: 400px;float: left;"/>
									<img onclick="chooseLatlng();" src="./buidingmanage/images/Map_130.76507650765px_1195280_easyicon.net.png" alt="" style="width: 20px;cursor: pointer;float: left;margin: 0 2px;"/>
									<input id="lnglat" name="secondHandHouse.lng_lat" type="hidden" value="<s:property value="secondHandHouse.lng_lat"/>"/>
									<div id="lnglat_div" style="float:left;color: #FF6600;">
										<s:if test="secondHandHouse!=null&&secondHandHouse.lng_lat!=null">
										<span style="font-weight:bolder;">经度：</span><s:property value='secondHandHouse.lng_lat.split(";")[0]'/>&nbsp;<span style="font-weight:bolder;">纬度：</span><s:property value='secondHandHouse.lng_lat.split(";")[1]'/>
										</s:if>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">售价：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esfsjDictList">
										<label><input type="radio" name="secondHandHouse.selling_price_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.selling_price_dict_id!=null&&secondHandHouse.selling_price_dict_id.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">面积：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esfmjDictList">
										<label><input type="radio" name="secondHandHouse.acreage_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.acreage_dict_id!=null&&secondHandHouse.acreage_dict_id.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">房型：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esffxDictList">
										<label><input type="radio" name="secondHandHouse.hous_type_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.hous_type_dict_id!=null&&secondHandHouse.hous_type_dict_id.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">建造年代：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esfjzndDictList">
										<label><input type="radio" name="secondHandHouse.building_age_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.building_age_dict_id!=null&&secondHandHouse.building_age_dict_id.equals(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">楼层：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esflcDictList">
										<label><input type="radio" name="secondHandHouse.floor_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.floor_dict_id!=null&&secondHandHouse.floor_dict_id.equals(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">房屋类型：</td>
								<td>
									<s:iterator value="@util.BaseParameter@esffwlxDictList">
										<label><input type="radio" name="secondHandHouse.building_type_dict_id"
										value='<s:property value="dictId"/>'
										<s:if test="secondHandHouse!=null&&secondHandHouse.building_type_dict_id!=null&&secondHandHouse.building_type_dict_id.equals(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">售价（万）：</td>
								<td>
									<input id="selling_price" class="ipt" size="40"	name="secondHandHouse.selling_price" 
										value="<s:property value="secondHandHouse.selling_price"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">参考首付（万）：</td>
								<td>
									<input id="refe_down_payment" class="ipt" size="40"	name="secondHandHouse.refe_down_payment" 
										value="<s:property value="secondHandHouse.refe_down_payment"/>" data-maxlength="25"/>
								</td>
							</tr>
<!-- 							<tr> -->
<!-- 								<td width="15%" class="txtR">参考月供：</td> -->
<!-- 								<td> -->
<!-- 									<input id="refe_month_for" class="ipt" size="40"	name="secondHandHouse.refe_month_for"  -->
<%-- 										value="<s:property value="secondHandHouse.refe_month_for"/>" data-maxlength="25"/> --%>
<!-- 								</td> -->
<!-- 							</tr> -->
							<tr>
								<td width="15%" class="txtR">单价：</td>
								<td>
									<input id="unit_price" class="ipt" size="40"	name="secondHandHouse.unit_price" 
										value="<s:property value="secondHandHouse.unit_price"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">房型：</td>
								<td>
									<input id="hous_type" class="ipt" size="40"	name="secondHandHouse.hous_type" 
										value="<s:property value="secondHandHouse.hous_type"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">面积：</td>
								<td>
									<input id="acreage" class="ipt" size="40"	name="secondHandHouse.acreage" 
										value="<s:property value="secondHandHouse.acreage"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">朝向：</td>
								<td>
									<input id="orientations" class="ipt" size="40"	name="secondHandHouse.orientations" 
										value="<s:property value="secondHandHouse.orientations"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">楼层：</td>
								<td>
									<input id="floor" class="ipt" size="40"	name="secondHandHouse.floor" 
										value="<s:property value="secondHandHouse.floor"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">装修：</td>
								<td>
									<input id="deco_standard" class="ipt" size="40"	name="secondHandHouse.deco_standard" 
										value="<s:property value="secondHandHouse.deco_standard"/>" data-maxlength="25"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">建造年代：</td>
								<td>
									<input id="building_age" class="ipt" size="40"	name="secondHandHouse.building_age" 
										value="<s:property value="secondHandHouse.building_age"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td class="txtR">室内图上传：</td>
								<td>
									<div id="pics_div_1">
									</div>
									<input type="hidden" id="pics_1" name="secondHandHouse.pics_1"/>
									<input type="button" value="上传室内图" onclick="startLoad(1)" style="width: 80px;height:35px ;background-color: #FF6600;border: 0;font-size: 14px;color: #fff;margin: 2px;"/>
								</td>
							</tr>
							<tr>
								<td class="txtR">室外图上传：</td>
								<td>
									<div id="pics_div_2">
									</div>
									<input type="hidden" id="pics_2" name="secondHandHouse.pics_2"/>
									<input type="button" value="上传室外图" onclick="startLoad(2)" style="width: 80px;height:35px ;background-color: #FF6600;border: 0;font-size: 14px;color: #fff;margin: 2px;"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">房源描述：</td>
								<td>
									<!-- 加载编辑器的容器 -->
						     		<script id="detail_info" name="secondHandHouse.detail_info" type="text/plain" style="height:250px;width:894px;margin:0;"><s:property value='secondHandHouse.detail_info' escape='false'/></script>
									<script>
										var ueditor = null;
										jQuery(document).ready(function() {//加载web编辑器
											ueditor= UE.ui.Editor({
												initialFrameHeight: 300,
												scaleEnabled:true
											});
											ueditor.render('detail_info');
										});
									</script>
								</td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(0)" onclick="checkAll();" class="btnG">确定</a>
						<a href="querySecondHandHouseList.action" class="btnO">返回</a>
					</div>
				</dd>
			</dl>
		</div>
		<script>
			var btnIds = ['m-areaTree']; //按钮名称
			var tanchuDivs = ['tree-area']; //展示div名称
			for ( var i = 0; i < btnIds.length; i++) {
				var td = new HelloWorld(btnIds[i], tanchuDivs[i]);
				YAHOO.ext.EventManager.onDocumentReady(td.init, td, true);
			}
		</script>
		<script type="text/javascript" src="./swfupload/js/swfupload.js"></script>
		<script type="text/javascript" src="./swfupload/js/handlers.js"></script>
		<script>
			var contextPath=".";
			var upload_type = 1;
			function startLoad(type){
				upload_type = type;
				var url=contextPath+"/servlet/FileUploadServlet.htm"; //处理上传的servlet
				var sizeLimit="1 MB";// 文件的大小  注意: 中间要有空格
				var types="*.jpg;*.jpeg;*.gif"; //注意是 " ; " 分割 
				var typesdesc="web iamge file"; //这里可以自定义
				var uploadLimit=20;  //上传文件的 个数
				initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit);
			}
			function del_pic(o_this){
				jQuery(o_this).parent().remove();
			}
			function callback_fun(pic_paths){
				var pic_p_arr = pic_paths.split(';');
				for(var i=1;i<pic_p_arr.length;i++){
					jQuery('#pics_div_' + upload_type).append('<div class="pic_div"><a class="pic_a" href="' + pic_p_arr[i] + '">' + pic_p_arr[i] + '</a>&nbsp;<a class="pic_del_a" href="javascript:void(0)" onclick="del_pic(this);">删除</a></div>');
				}
				$('#pics_div_' + upload_type).find('a').unbind(); //移除所有
				$('#pics_div_' + upload_type).find('a').imgPreview({
					imgCSS: { width: 800 }
				});
			}
			jQuery(function(){
				upload_type = 1;
				callback_fun('<s:property value="secondHandHouse.pics_1"/>');
				upload_type = 2;
				callback_fun('<s:property value="secondHandHouse.pics_2"/>');
			});
		</script>
		<script>
			(function($){  
				$('#pics_div_1,#pics_div_2').find('a').imgPreview({
					imgCSS: { width: 500 }
				});
			})(jQuery);
		</script>
		
	</body>
</html>
