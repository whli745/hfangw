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
		<style type="text/css">
			.pic_a{background-image:url(images/image.png); background-repeat:no-repeat;padding-left: 20px;color:#222;}
			.pic_div{background-color: #E5F3F9;margin:0 2px;}
			.pic_del_a{}
			em {text-decoration: underline;}
		</style>
		<script type="text/javascript">
		function checkAll(){
			if(jQuery.trim(jQuery("#proj_name").val())==""){
				Dialog.alertFocus('楼盘名称不能为空！', 'roleName');
				return false;
			}
			if(jQuery.trim(jQuery("#areaId").val())==""){
				Dialog.alertFocus('请选择所属区划！', 'areaName');
				return false;
			}
			var pics = "";
			jQuery('#pics_div').find('a[class="pic_a"]').each(function(){
				pics += ";" + jQuery(this).attr('href');
			});
			jQuery('#pics').val(pics);
			var pics_desc = "";
			jQuery('input[name="pics_desc"]').each(function(){
				pics_desc += ";" + jQuery(this).val();
			});
			jQuery('#pics_desc').val(pics_desc);
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
					<p class="position">当前位置：<a>网站管理</a><a>楼盘管理</a><a>最新楼盘</a><a><s:if test="housingProj==null">添加</s:if><s:else>修改</s:else><s:if test='proj_type.equals("1")'>最新</s:if><s:else>团购</s:else>楼盘</a>
					</p>
					<a href="queryHousingProjectList.action?proj_type=<s:property value="proj_type" />" class="back">返回</a>
				</dt>
				<dd>
					<form name="form1" action="housingProjectEdit.action" method="post">
						<input type="hidden" name="housingProj.oid"	value="<s:property value="housingProj.oid"/>" />
						<input type="hidden" name="proj_type"	value="<s:property value="proj_type" />" />
						<input type="hidden" name="housingProj.proj_type"	value="<s:property value="proj_type" />" />
						<input type="hidden" name="housingProj.del_flag"	value="<s:property value="housingProj.del_flag" />" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formTab">
							<tr>
								<th colspan="4">
									<p class="title">
									<s:if test="housingProj==null">添加</s:if><s:else>修改</s:else><s:if test='proj_type.equals("1")'>最新</s:if><s:else>团购</s:else>楼盘</p>
								</th>
							</tr>
							<tr>
								<td width="15%" class="txtR">楼盘名称：</td>
								<td>
									<input id="proj_name" class="ipt" size="40"	name="housingProj.proj_name" 
										value="<s:property value="housingProj.proj_name"/>" data-maxlength="100"/><font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="txtR">所属区划：</td>
								<td>
									<input type="text" class="input_text" id="areaName" value="<s:property value='housingProj.sysArea.areaName'/>" readonly="readonly" style="width: 160px;"/>
						        	<input type="hidden" class="input_text" id="areaId" name="housingProj.area_id" value="<s:property value='housingProj.area_id'/>"/><font color="red">&nbsp;*</font>
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
								<td width="15%" class="txtR">楼盘地址：</td>
								<td>
									<input id="address" class="ipt" size="40"	name="housingProj.address" 
										value="<s:property value="housingProj.address"/>" data-maxlength="100" style="width: 400px;float: left;"/>
									<img onclick="chooseLatlng();" src="./buidingmanage/images/Map_130.76507650765px_1195280_easyicon.net.png" alt="" style="width: 20px;cursor: pointer;float: left;margin: 0 2px;"/>
									<input id="lnglat" name="housingProj.lnglat" type="hidden" value="<s:property value="housingProj.lnglat"/>"/>
									<div id="lnglat_div" style="float:left;color: #FF6600;">
										<s:if test="housingProj!=null&&housingProj.lnglat!=null&&housingProj.lnglat.indexOf(';')>0">
										<span style="font-weight:bolder;">经度：</span><s:property value='housingProj.lnglat.split(";")[0]'/>&nbsp;<span style="font-weight:bolder;">纬度：</span><s:property value='housingProj.lnglat.split(";")[1]'/>
										</s:if>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">售楼状态：</td>
								<td>
									<s:iterator value="@util.BaseParameter@slztDictList">
										<label><input type="radio" name="housingProj.sale_hous_status"
										value='<s:property value="dictId"/>'
										<s:if test="housingProj!=null&&housingProj.sale_hous_status!=null&&housingProj.sale_hous_status.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">楼盘户型：</td>
								<td>
									<s:iterator value="@util.BaseParameter@hxflDictList">
										<label><input type="checkbox" name="housingProj.hous_type"
										value='<s:property value="dictId"/>'
										<s:if test="housingProj!=null&&housingProj.hous_type!=null&&housingProj.hous_type.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">建筑类型：</td>
								<td>
									<s:iterator value="@util.BaseParameter@jzlxDictList">
										<label><input type="checkbox" name="housingProj.building_type"
										value='<s:property value="dictId"/>'
										<s:if test="housingProj!=null&&housingProj.building_type!=null&&housingProj.building_type.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">价格区间：</td>
								<td>
									<s:iterator value="@util.BaseParameter@lpdjDictList">
										<label><input type="radio" name="housingProj.search_unit_price"
										value='<s:property value="dictId"/>'
										<s:if test="housingProj!=null&&housingProj.search_unit_price!=null&&housingProj.search_unit_price.equals(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">面积：</td>
								<td>
									<input id="hous_acreage" class="ipt" size="40"	name="housingProj.hous_acreage" 
										value="<s:property value="housingProj.hous_acreage"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">交房时间：</td>
								<td>
									<input id="launch_time" class="ipt" size="40"	name="housingProj.launch_time" 
										value="<s:property value="housingProj.launch_time"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">售楼专线：</td>
								<td>
									<input id="sales_line" class="ipt" size="40"	name="housingProj.sales_line" 
										value="<s:property value="housingProj.sales_line"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">参考价格：</td>
								<td id="ckjg_td">
									<s:if test="housingProj == null || housingProj.priceList.size()==0">
										<input type="text" value="住宅" name="priceList[0].val_1" class="ipt" style="width: 60px;"/>
										<input type="text" value="均价" name="priceList[0].val_2" class="ipt" style="width: 60px;"/>
										<input type="text" value="7000" name="priceList[0].val_3" class="ipt" style="width: 60px;"/>
										<input type="text" value="元/m2" name="priceList[0].val_4" class="ipt" style="width: 60px;"/>
										<input type="hidden" value="1" name="priceList[0].val_5" class="ipt" style="width: 60px;"/><a href="javascript:append_ipts();">添加</a>&nbsp;<span style="color:#FF6600;">[示例：依次填写 <em>住宅</em> <em>均价</em> <em>7000</em> <em>元/㎡</em>&nbsp;或依次填写 <em>商住</em> <em>&nbsp;&nbsp;&nbsp;&nbsp;</em> <em>待定</em> <em>&nbsp;&nbsp;&nbsp;&nbsp;</em>&nbsp;]</span>
									</s:if>
									<s:else>
										<s:iterator value="housingProj.priceList" status="idx">
										<div style="margin-top:2px;" id="div_<s:property value="#idx.index"/>">
										<input type="hidden" value="<s:property value="oid"/>" name="priceList[<s:property value="#idx.index"/>].oid" class="ipt" style="width: 60px;"/>
										<input type="text" value="<s:property value="val_1"/>" name="priceList[<s:property value="#idx.index"/>].val_1" class="ipt" style="width: 60px;"/>
										<input type="text" value="<s:property value="val_2"/>" name="priceList[<s:property value="#idx.index"/>].val_2" class="ipt" style="width: 60px;"/>
										<input type="text" value="<s:property value="val_3"/>" name="priceList[<s:property value="#idx.index"/>].val_3" class="ipt" style="width: 60px;"/>
										<input type="text" value="<s:property value="val_4"/>" name="priceList[<s:property value="#idx.index"/>].val_4" class="ipt" style="width: 60px;"/>
										<input type="hidden" value="<s:property value="val_5"/>" name="priceList[<s:property value="#idx.index"/>].val_5" class="ipt" style="width: 60px;"/>
										<s:if test="#idx.index == 0">
											<a href="javascript:append_ipts();">添加</a>&nbsp;&nbsp;<span style="color:#FF6600;">[示例：依次填写 <em>住宅</em> <em>均价</em> <em>7000</em> <em>元/㎡</em>&nbsp;或依次填写 <em>商住</em> <em>&nbsp;&nbsp;&nbsp;&nbsp;</em> <em>待定</em> <em>&nbsp;&nbsp;&nbsp;&nbsp;</em>&nbsp;]</span>
										</s:if>
										<s:else>
											<a href="javascript:del_ipts(<s:property value="#idx.index"/>)">删除</a></div>
										</s:else>
										</div>
										</s:iterator>
									</s:else>
									<script>
										var idx = '<s:property value="housingProj.priceList.size"/>';
										function append_ipts(){
											if(idx == ''){
												idx = 0;
											}
											++idx;
											var html = '<div style="margin-top:2px;display:block;" id="div_'+idx+'"><input type="text" value="" name="priceList['+idx+'].val_1" class="ipt" style="width: 60px;"/>'
											+'&nbsp;<input type="text" value="" name="priceList['+idx+'].val_2" class="ipt" style="width: 60px;"/>'
											+'&nbsp;<input type="text" value="" name="priceList['+idx+'].val_3" class="ipt" style="width: 60px;"/>'
											+'&nbsp;<input type="text" value="" name="priceList['+idx+'].val_4" class="ipt" style="width: 60px;"/>'
											+'<input type="hidden" value="'+idx+'" name="priceList['+idx+'].val_5" class="ipt" style="width: 60px;"/>'
											+'&nbsp;<a href="javascript:del_ipts('+idx+')">删除</a></div>';
											jQuery('#ckjg_td').append(html);
										}
										function del_ipts(d_idx){
											jQuery('#div_' + d_idx).remove();
										}
									</script>
								</td>
							</tr>
			<tr>
			<td width="15%" class="txtR"><font color="red">红包</font>：</td>
			<td id="hongb_td">
				<s:if test="housingProj == null || housingProj.packetList == null || housingProj.packetList.size()==0">
					<input type="text" value="" name="packetList[0].packetName" class="ipt" style="width: 60px;"/>
					<input type="text" value="" name="packetList[0].packetMoney" class="ipt" style="width: 60px;"/>
					<input type="text" value="" name="packetList[0].startTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
					<input type="text" value="" name="packetList[0].endTime" class="ipt Wdate" onclick="WdatePicker()" style="width: 100px;" readonly="readonly"/>
					<a href="javascript:Happend_ipts();">添加</a>&nbsp;<span style="color:#FF6600;">[示例：依次填写 <em>红包名称</em> <em>红包金额</em> <em>开始日期</em> <em>结束日期</em>&nbsp;或不填写]</span>
				</s:if>
				<s:else>
					<s:iterator value="housingProj.packetList" status="idx">
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
								<td width="15%" class="txtR">优惠折扣：</td>
								<td>
									<input id="discount" class="ipt" size="40"	name="housingProj.discount" 
										value="<s:property value="housingProj.discount"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">最新开盘：</td>
								<td>
									<input id="latest_opening" class="ipt" size="40"	name="housingProj.latest_opening" 
										value="<s:property value="housingProj.latest_opening"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">装修标准：</td>
								<td>
									<input id="deco_standard" class="ipt" size="40"	name="housingProj.deco_standard" 
										value="<s:property value="housingProj.deco_standard"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">产权年限：</td>
								<td>
									<input id="period_right" class="ipt" size="40"	name="housingProj.period_right" 
										value="<s:property value="housingProj.period_right"/>" data-maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">热卖标签：</td>
								<td>
									<s:iterator value="@util.BaseParameter@rxbqDictList">
										<label><input type="checkbox" name="housingProj.hot_sell_label"
										value='<s:property value="dictId"/>'
										<s:if test="housingProj!=null&&housingProj.hot_sell_label!=null&&housingProj.hot_sell_label.contains(dictId)">checked</s:if>
										/><s:property value="dictName"/></label>
									</s:iterator>
								</td>
							</tr>
							<tr>
								<td width="15%" class="txtR">看房团：</td>
								<td>
									<input type="hidden" value="<s:property value="housingProj.hfwKft.oid"/>" name="hfwKft.oid"/>
									报名截止时间：<input type="text" name="hfwKft.bm_end_time" class="ipt_s Wdate" value="<s:date name='housingProj == null ? defaultDate : housingProj.hfwKft.bm_end_time' format='yyyy-MM-dd'/>" onfocus="WdatePicker()" readonly="readonly" />
									&nbsp;看房时间：<input class="ipt" size="40" style="width:200px;"name="hfwKft.kf_time" 
										value='<s:property value="housingProj == null ? \"随时\" : housingProj.hfwKft.kf_time"/>' data-maxlength="100"/>
									&nbsp;集合地点：<input class="ipt" size="40" style="width:200px;" name="hfwKft.jhdd" 
										value="<s:property value="housingProj.hfwKft.jhdd"/>" data-maxlength="100"/>
								</td>
							</tr>
							<s:if test='proj_type eq "1"'>
							<tr>
								<td class="txtR">关注度：</td>
								<td>
									<input id="period_right" class="ipt" size="40"	name="housingProj.attention" 
										value="<s:property value="housingProj.attention"/>" data-maxlength="6" style="width:80px;"/>&nbsp;<span style="color:#FF6600;">[注：一个IP每日浏览一次关注度加1,同时支持此处手动设置]</span>
								</td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<td class="txtR">团购人数：</td>
								<td>
									<input id="period_right" class="ipt" size="40"	name="housingProj.attention" 
										value="<s:property value="housingProj.attention"/>" data-maxlength="6" style="width:80px;"/>&nbsp;<span style="color:#FF6600;">[注：一个IP每日浏览一次团购人数加1,同时支持此处手动设置]</span>
								</td>
							</tr>
							</s:else>
							<tr>
								<td class="txtR">效果图上传：</td>
								<td>
									<div id="pics_div">
									</div>
									<input type="hidden" id="pics" name="housingProj.pics"/>
									<input type="hidden" id="pics_desc" name="housingProj.pics_desc"/>
									<input type="button" value="上传图片" onclick="startLoad()" style="width: 80px;height:35px ;background-color: #FF6600;border: 0;font-size: 14px;color: #fff;margin: 2px;"/>
								</td>
							</tr>
						</table>
					</form>
					<div class="btnbarBig">
						<a href="javascript:void(0)" onclick="checkAll();" class="btnG">确定</a>
						<a href="queryHousingProjectList.action?proj_type=<s:property value="proj_type" />" class="btnO">返回</a>
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
			function startLoad(){
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
					jQuery('#pics_div').append('<div class="pic_div"><a class="pic_a" href="' + pic_p_arr[i] + '">' + pic_p_arr[i] + '</a>&nbsp;<input name="pics_desc" type="text" value="效果图" style="width:60px;border:1px solid #ABADB3;height:20px;line-height:20px;"/>&nbsp;<a class="pic_del_a" href="javascript:void(0)" onclick="del_pic(this);">删除</a></div>');
				}
				$('#pics_div').find('a').unbind(); //移除所有
				$('#pics_div').find('a').imgPreview({
					imgCSS: { width: 800 }
				});
			}
			jQuery(function(){
				callback_fun('<s:property value="housingProj.pics"/>');
				var pic_desc_arr = '<s:property value="housingProj.pics_desc" escape="false"/>'.split(';');
				jQuery('input[name="pics_desc"]').each(function(idx){
					jQuery(this).val(pic_desc_arr[idx + 1]);
				});
			});
		</script>
		<script>
			(function($){  
				$('#pics_div').find('a').imgPreview({
					imgCSS: { width: 500 }
				});
			})(jQuery);
		</script>
		
	</body>
</html>
