<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="districtName_"/>最新楼盘，<s:property value="districtName_"/>团购楼盘-华房网</title>
<%@ include file="include/heard.inc"%>
<link href="web/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/layer/laypage-v1.3/laypage.js"></script>
<base href="<%=basePath%>" />
<style type="text/css">
.selected_cond {
	border: 1px solid #999;
	color: #999;
	padding: 1px 8px 2px;
	height: 20px;
	float: left;
	display: block;
	margin-top: 12px;
	line-height: 20px;
	margin-right: 10px;
	font-size: 14px;
}

.selected_cond:HOVER {
	border: 1px solid #FF6600;
	color: #FF6600;
	cursor: pointer;
}

.noborder {
	border: 0;
	line-height: 22px;
}

.noborder:HOVER {
	border: 0;
	border: 0;
	line-height: 22px;
}
</style>
<script>
	var url = 'buidingSearch.action?type=<s:property value="type"/>&districtId=<s:property value="districtId"/>';
	function doSearch(a){
		jQuery(a).siblings().removeClass('curr-area');
		jQuery(a).addClass('curr-area');
		jQuery('a[class="curr-area"][id]').each(function(){
			url+='&'+jQuery(this).attr('id');
		});
		location.href = url;
	}
	
	function clear_condition(type){
		if(type==5){
			location.href = url;
		}else{
			jQuery('a[name="con_' + type + '"]').addClass('curr-area');
			jQuery('a[name="con_' + type + '"]').siblings().removeClass('curr-area');
			doSearch(jQuery('a[name="con_' + type + '"]')[0]);
		}
	}
</script>
</head>
<body>
	<div class="wrapper">
		<!--头部开始 -->
		<%@ include file="include/top_b_s.inc"%>
		<%@ include file="include/nav_house.inc"%>
		<!-- header end -->
		<s:set name="querystring" value='#request.querystring == null ? "" : #request.querystring'/>
		<div class="content w1190 auto">
			<div class="filter-nav">
				<div class="filter-nav-tit">
					<span></span>楼盘搜索
				</div>
				<div class="filter-mod" 
				<s:if test='#querystring.contains("q.area_id")||#querystring.contains("q.search_unit_price")||#querystring.contains("q.hous_type")||#querystring.contains("q.building_type")'>
				style="height: 225px;"
				</s:if>>
					<div class="filter-item filter-position">
						<label class="item-title"> 位置： </label>
						<div class="item-area">
							<div class="item-bd">
								<div class="item-list area-bd">
									<div class="filter">
										<a name="con_1" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("q.area_id")'>class="curr-area"</s:if>>不限</a>
										<s:iterator value="areaList">
											<a onclick="doSearch(this);" href="javascript:void(0);"
												id="q.area_id=<s:property value='areaId'/>"
												<s:if test='#querystring.contains(areaId)'>class="curr-area"</s:if>><s:property value="areaName" /></a>
										</s:iterator>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 单价： </label>
						<div class="item-mod">
							<a name="con_2" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("q.search_unit_price")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@lpdjDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="q.search_unit_price=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 户型： </label>
						<div class="item-mod">
							<a name="con_3" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("q.hous_type")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@hxflDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="q.hous_type=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 类型： </label>
						<div class="item-mod">
							<a name="con_4" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("q.building_type")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@jzlxDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="q.building_type=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<s:if test='#querystring.contains("q.area_id")||#querystring.contains("q.search_unit_price")||#querystring.contains("q.hous_type")||#querystring.contains("q.building_type")'>
					<div class="filter-item">
						<label class="item-title" style="margin-top: 2px;"> 已选择分类：
						</label> 
							<s:if test='#querystring.contains("q.area_id")'>
							<s:iterator value="areaList">
								<s:if test='#querystring.contains(areaId)'>
								<span class="selected_cond" onclick="clear_condition(1);">位置：<s:property value="areaName" />&nbsp;<i class="fa fa-close"></i></span> 
								</s:if>
							</s:iterator>
							</s:if>
							<s:if test='#querystring.contains("q.search_unit_price")'>
							<s:iterator value="@util.BaseParameter@lpdjDictList">
								<s:if test='#querystring.contains(dictId)'>
								<span class="selected_cond" onclick="clear_condition(2);">单价：<s:property value="dictName" />&nbsp;<i class="fa fa-close"></i></span> 
								</s:if>
							</s:iterator>
							</s:if>
							<s:if test='#querystring.contains("q.hous_type")'>
							<s:iterator value="@util.BaseParameter@hxflDictList">
								<s:if test='#querystring.contains(dictId)'>
								<span class="selected_cond" onclick="clear_condition(3);">户型：<s:property value="dictName" />&nbsp;<i class="fa fa-close"></i></span> 
								</s:if>
							</s:iterator>
							</s:if>
							<s:if test='#querystring.contains("q.building_type")'>
							<s:iterator value="@util.BaseParameter@jzlxDictList">
								<s:if test='#querystring.contains(dictId)'>
								<span class="selected_cond" onclick="clear_condition(4);">类型：<s:property value="dictName" />&nbsp;<i class="fa fa-close"></i></span> 
								</s:if>
							</s:iterator>
							</s:if> 
							<span class="selected_cond noborder" onclick="clear_condition(5);"><i class="fa fa-trash-o"></i>&nbsp;清除全部条件</span>
					</div>
					</s:if>
				</div>
			</div>
<!-- 			<i class="list-icons"> </i> -->
			<div class="list-contents">
				<div class="list-results">
					<div class="key-sort">
						<div class="page-tab">
							<div class="sort-tab">
								<ul>
									<li class="current">全部楼盘</li>
								</ul>
							</div>
						</div>
						<div class="sort-condi">
							<div class="condition">
								<a class="light"><span> 默认排序 </span>
								</a> 
<!-- 								<a class="" href=""> <span> 价格 </span> </i> -->
<!-- 								</a>  -->
<!-- 								<a class="" href=""> <span> 开盘时间 </span> </i> -->
<!-- 								</a> -->
							</div>
							<span class="result">共有 <em><s:property value='rp.allRows' /></em> 个符合要求的
								<h1>
									<a/> <s:property value="districtName"/> </a>
								</h1> 楼盘
							</span>
						</div>
					</div>

					<div class="key-list">
						<div style="display: block">
							
							<s:iterator value="rp.resultList">
								<div class="item-mod" onclick="window.open('showBuidingDetail.action?oid=<s:property value="oid" />')">
									<a class="pic" target="_blank"> 
										<img width="180" height="135" src="<s:property value='pics!=null&&pics.split(";").length>1&&pics.split(";")[1]' />" alt="" />
									</a>
									<div class="infos">
										<div class="lp-name">
											<h3>
												<a class="items-name"   target="_blank">
													<s:property value='proj_name' />
												</a>
											</h3>
											<s:if test="@util.BaseParameter@DICT_ID_QFZS eq sale_hous_status||@util.BaseParameter@DICT_ID_XFZS eq sale_hous_status">
												<i class="status-icon onsale"><s:property value='shs_dict.dictName' /> </i> 
											</s:if>
											<s:elseif test="@util.BaseParameter@DICT_ID_DS eq sale_hous_status">
												<i class="status-icon reco"> <s:property value='shs_dict.dictName' /> </i> 
											</s:elseif>
											<s:else>
												<i class="status-icon jnh-icon"><s:property value='shs_dict.dictName' /> </i> 
											</s:else>
<!-- 												<a href="" -->
<!-- 												target="_blank" class="list-dp" soj="list_dp"> （94条点评） </a> -->
										</div>
										<p class="address">
											<a  class="list-map" target="_blank">
												[<s:property value='sysArea.areaName' />]&nbsp;<s:property value='address' /> </a>
										</p>
										<p>
											户型： 
											<s:set name="len" value="hous_type == null ? 0 : hous_type.split(\",\").length"/>
											<s:set name="idx" value="1"/>
											<s:iterator value="@util.BaseParameter@hxflDictList">
												<s:if test="hous_type != null && hous_type.contains(dictId)">
													<a  target="_blank"><s:property value="dictName"/> </a>
													<s:if test="#idx<#len">|</s:if>
													<s:set name="idx" value="#idx+1"/>
												</s:if>
											</s:iterator>
										</p>
										<div class="data-brief">
											<em style="color:#666;">最新开盘： </em> 
											<a  target="_blank"><s:property value='latest_opening' /> </a>
										</div>
									</div>
									<div class="favor-pos">
										<p class="price">
											<s:set name="price" value="priceList[0]"/>
											<s:property value='#price.val_2' /> <span><s:property value='#price.val_3' /></span><s:property value='#price.val_4' />
										</p>
										
										<div class="discount-item">
											<p class="favor-tag">
												<s:if test='discount != null && !discount.equals("")'>
													<em class="discount-txt" title="<s:property value='discount' />"><s:property value='discount' /></em>
												</s:if>
											</p>
										</div>
										<p class="tel">
											<i class="list-ico tel-icon"> </i><s:property value='sales_line' />
										</p>
									</div>
									<s:if test="packetList != null && packetList.size() > 0">
									<img src="web/images/hongbao.gif" width="57" height="45" />
										<!-- <a href="#" class="qiang-icon" soj="16jnh_loupanlist_hongbao"
											title="抢红包"> <img
											src="web/images/hongbao.gif" width="57" height="45" />
										</a> -->
									</s:if>
								</div>
							</s:iterator>
						</div>
						<div style="display: none">
						
						</div>
					</div>
					<!--邮箱订阅-->
					<div class="list-page">
						<span class="total"> 共有 <em> <s:property value='rp.allRows' /> </em> 个有关<s:property value="districtName"/>新房楼盘
						</span>
						<div id="pagination" style="width:500px;height: 30px;float: right;">
						</div>
					</div>
					<script>
						laypage({
							cont : jQuery('#pagination'), // 容器。值支持id名、原生dom对象，jquery对象,
							pages : <s:property value='rp.maxPage' />, // 总页数
							skin : '#C0392B',
							groups : 3, // 连续显示分页数
							curr : <s:property value='page' />, // 当前页
							skip : true, // 是否开启跳页
							jump : function(obj, first) {
								if (!first) {// 点击跳页触发函数自身，并传递当前页：obj.curr
									if (location.href.indexOf('?') < 0) {
										location.href = location.href + '?page=' + obj.curr;
									} else {
										if (location.href.indexOf('page=') > 0) {
											location.href = location.href.substring(0,
													location.href.indexOf('page='))
													+ '&page=' + obj.curr;
										} else {
											location.href = location.href + '&page='
													+ obj.curr;
										}
									}
								}
							}
						});
					</script>
				</div>
				<%@ include file="include/right.inc"%>
			</div>
		</div>
		<!--底部开始 -->
		<%@ include file="include/footer.inc"%>
		<!--底部结束 -->
	</div>
</body>
</html>
