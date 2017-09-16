<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="districtName_"/>二手房房产网，<s:property value="districtName_"/>二手房交易信息，<s:property value="districtName_"/>二手房出售-华房网</title>
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
	var url = 'houseSearch.action?districtId=<s:property value="districtId"/>';
	function doSearch(a,flag){
		jQuery(a).siblings().removeClass('curr-area');
		jQuery(a).addClass('curr-area');
		jQuery('a[class="curr-area"][id]').each(function(){
			url+='&'+jQuery(this).attr('id');
		});
		if(flag){
			jQuery(a).parent().siblings().removeClass('selected');
			jQuery(a).parent().addClass('selected');
		}
		jQuery('li.selected a[id]').each(function(){
			url+='&'+jQuery(this).attr('id');
		});
		location.href = url;
	}
	
</script>
<style>
.content {
	font-size: 13px;
}
.search_bottom {
	padding-left: 0;
	position: relative;
	height: 45px;
	padding-top: 0;
	margin-right: 0;
	clear: both;
	display: -moz-inline-stack;
	display: inline-block;
}

.search_bottom a {
	display: -moz-inline-stack;
	display: inline-block;
	zoom: 1
}

.search_bottom a,span {
	color: #333;
	font-size: 13px;
}

#condmenu .condul {
	padding: 0;
	list-style-type: none
}

#condmenu .condul {
	line-height: 20px;
	padding-top: 14px;
	height: 31px
}

#condmenu .condlist_tip {
	float: left;
	color: #999;
	margin-right: 0
}

#condmenu li {
	float: left;
	display: inline
}

#condmenu a {
	height: 100%;
	width: 80px;
	text-decoration: none;
	background: #fff;
	border: 1px solid #b3b3b3;
	margin-left: 10px;
	margin-right: 8px;
	padding: 2px 0;
	color: #333
}

#condmenu a.on {
	border: #f2b169 1px solid
}

#condmenu ul ul a {
	display: block;
	background: #fff;
	color: #333;
	height: 100%;
	line-height: 12px;
	padding: 6px 12px;
	border: 0;
	width: 56px
}

#condmenu table {
	border-collapse: collapse;
	padding: 0;
	margin: -1px;
	width: 0;
	height: 0;
	font-size: 1em;
	z-index: 1000;
	background: #fff
}

#condmenu li:hover {
	position: relative
}

#condmenu :hover>a {
	color: #333;
	background: #fff
}

#condmenu ul ul li:hover {
	position: relative
}

#condmenu ul ul :hover>a {
	color: #333
}

#condmenu ul ul {
	visibility: hidden;
	position: absolute;
	height: 0;
	top: 22px;
	_top: 21px;
	left: 0;
	width: 96px;
	z-index: 1000;
	border: 1px solid #ccc;
	border-top: 0
}

#condmenu ul ul li.selected a {
	color: #333
}

#condmenu ul :hover ul {
	visibility: visible;
	height: auto;
	left: 0;
	_left: -1px
}

#condmenu ul ul li {
	display: block;
	height: auto;
	line-height: 12px;
	width: 70px;
	_overflow: hidden;
	_zoom: 1;
	margin-left: 0
}

#condmenu .select_item span.txt {
	display: inline-block;
	padding-left: 7px;
	white-space: nowrap;
	width: 60px;
	cursor: pointer
}

* html #condmenu a:hover {
	color: #333;
	background-color: #fff;
	position: relative;
	z-index: 1000
}

* html #condmenu ul ul a:hover {
	color: #333;
	position: relative;
	z-index: 1000
}

#condmenu #condhouseage_id .select_item span.txt {
	width: 70px
}

.condul li {
	margin-left: 10px
}

#condmenu #condhouseage_id a {
	display: inline-block;
	margin-left: 0
}

#condmenu .condul a {
	margin-left: 0
}

#condmenu #condhouseage_id ul a {
	width: 76px
}

#condmenu #condhouseage_id ul {
	width: 91px;
	_width: 100px
}

#condmenu #condhouseage_id ul li {
	width: 100px;
	margin-left: 0
}

#condmenu #condhouseage_id ul a:link,#condmenu #condhouseage_id ul a:visited,#condmenu #condhouseage_id ul a:hover,#condmenu #condhouseage_id ul a:active
	{
	width: 84px
}

#condmenu .selected a {
	color: #f60 !important
}

#condmenu #selectfitment_id,#condmenu #selectschool_id,#condmenu #condbrand_id,#condmenu #condhouseage_id,#condmenu #condfloor_id,#condmenu #condusetype_id,#condmenu #condeconomics_id,#condmenu #condpubtime_id
	{
	position: relative
}

#condmenu #selectfitment_id a iframe,#condmenu #selectschool_id a iframe,#condmenu #condbrand_id a iframe,#condmenu #condhouseage_id a iframe,#condmenu #condfloor_id a iframe,#condmenu #condusetype_id a iframe,#condmenu #condeconomics_id a iframe,#condmenu #condpubtime_id a iframe
	{
	display: none
}

#condmenu #selectfitment_id a:hover iframe,#condmenu #selectschool_id a:hover iframe,#condmenu #condbrand_id a:hover iframe,#condmenu #condhouseage_id a:hover iframe,#condmenu #condfloor_id a:hover iframe,#condmenu #condusetype_id a:hover iframe,#condmenu #condeconomics_id a:hover iframe,#condmenu #condpubtime_id a:hover iframe
	{
	display: block
}

#condmenu .condul a {
	width: 91px;
	_width: 100px;
	height: 20px;
	line-height: 20px;
	padding: 0;
	border: 1px solid #d1d1d1;
	margin-right: 0
}

#condmenu .condul #condeconomics_id a {
	width: 112px;
	_width: 112px
}

#condmenu .condul a .icon {
	position: absolute;
	right: 7px;
	top: 9px;
	display: block;
	overflow: hidden;
	width: 0;
	height: 0;
	line-height: 0;
	border: 4px solid;
	border-color: #7b7b7b #fff #fff #fff
}

#condmenu .condul a :hover .icon {
	_top: 8px;
	_right: 6px
}

#condmenu .condul #condusetype_id a,#condmenu .condul #condeconomics_id a,#condmenu .condul #condusetype_id ul
	{
	display: block
}

#condmenu .condul ul {
	overflow: hidden;
	zoom: 1;
	background: #fff;
	padding: 4px 0
}

#condmenu .condul #condfloor_id ul {
	width: 91px;
	_width: 100px
}

#condmenu .condul #condusetype_id ul {
	width: 91px;
	_width: 100px
}

#condmenu .condul #condeconomics_id ul {
	width: 112px;
	_width: 112px
}

#condmenu .condul ul a,#condmenu .condul ul a:link,#condmenu .condul ul a:visited
	{
	display: block;
	width: 72px;
	height: 100%;
	padding: 6px 8px;
	margin: 0;
	line-height: 12px;
	color: #333;
	background: #fff;
	border: 0 none
}

#condmenu .condul ul a:hover {
	color: #f60;
	text-decoration: underline
}
</style>
</head>
<body>
	<div class="wrapper">
		<!--头部开始 -->
		<%@ include file="include/top_shh.inc"%>
		<%@ include file="include/nav_shh.inc"%>
		<!-- header end -->
		<s:set name="querystring" value="#request.querystring"/>
		<div class="content w1190 auto">
			<div class="filter-nav">
				<div class="filter-nav-tit">
					<span></span>二手房搜索
				</div>
				<div class="filter-mod" style="height: 230px;">
					<div class="filter-item filter-position">
						<label class="item-title"> 区域： </label>
						<div class="item-area">
							<div class="item-bd">
								<div class="item-list area-bd">
									<div class="filter">
										<a name="con_1" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("shh_q.area_id")'>class="curr-area"</s:if>>不限</a>
										<s:iterator value="areaList">
											<a onclick="doSearch(this);" href="javascript:void(0);"
												id="shh_q.area_id=<s:property value='areaId'/>"
												<s:if test='#querystring.contains(areaId)'>class="curr-area"</s:if>><s:property value="areaName" /></a>
										</s:iterator>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 售价： </label>
						<div class="item-mod">
							<a name="con_2" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("shh_q.selling_price_dict_id")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@esfsjDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="shh_q.selling_price_dict_id=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 面积： </label>
						<div class="item-mod">
							<a name="con_3" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("shh_q.acreage_dict_id")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@esfmjDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="shh_q.acreage_dict_id=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title"> 房型： </label>
						<div class="item-mod">
							<a name="con_4" onclick="doSearch(this);" href="javascript:void(0);" <s:if test='!#querystring.contains("shh_q.hous_type_dict_id")'>class="curr-area"</s:if>> 不限 </a>
							<s:iterator value="@util.BaseParameter@esffxDictList">
								<a onclick="doSearch(this);" href="javascript:void(0);"
									id="shh_q.hous_type_dict_id=<s:property value='dictId'/>"
									<s:if test='#querystring.contains(dictId)'>class="curr-area"</s:if>><s:property value="dictName" /></a>
							</s:iterator>
						</div>
					</div>
					<div class="filter-item">
						<label class="item-title" style="margin-top: 2px;"> 更多：</label> 
						<div class="search_bottom">
						    <div id="condmenu">
						        <ul class="condul clearfix">
						            <!-- house age start -->
						            <li id="condhouseage_id" style="margin-left: 0;">
					            		<s:set name="flag" value="true"/>
					                	<s:iterator value="@util.BaseParameter@esfjzndDictList">
					                    	<s:if test='#querystring.contains(dictId)'>
					                    		<s:set name="flag" value="false"/>
					                    		<a href="javascript:void(0);">
					                    			<span class="select_item"><span class="txt" id="condhouseage_txt_id" style="color:#f60;"><s:property value="dictName" /></span>    <span class="icon">&nbsp;</span></span></a>
					                    	</s:if>
					                    </s:iterator>
					                    <s:if test="#flag">
						                     <a href="javascript:void(0);">
						                    	<span class="select_item"><span class="txt" id="condhouseage_txt_id">建造年代</span><span class="icon">&nbsp;</span></span></a>
					                    </s:if>
						                <ul>
						                    <li <s:if test='!#querystring.contains("shh_q.building_age_dict_id")'>class="selected"</s:if>> <a onclick="doSearch(this,1);" href="javascript:void(0);" rel="nofollow">全部</a> </li>
						                    <s:iterator value="@util.BaseParameter@esfjzndDictList">
						                    	<li <s:if test='#querystring.contains(dictId)'>class="selected"</s:if>>
							                    	<a onclick="doSearch(this);" href="javascript:void(0);"
														id="shh_q.building_age_dict_id=<s:property value='dictId'/>"
														><s:property value="dictName" /></a>
												</li>
						                    </s:iterator>
						                </ul>
						            </li>
						            <!-- house age end -->
						
						            <!-- house type start -->
						            <li id="condusetype_id">
						            	<s:set name="flag" value="true"/>
						            	<s:iterator value="@util.BaseParameter@esffwlxDictList">
					                    	<s:if test='#querystring.contains(dictId)'>
					                    		<s:set name="flag" value="false"/>
					                    		<a href="javascript:void(0);">
					                    			<span class="select_item"><span class="txt" id="condusetype_txt_id" style="color:#f60;"><s:property value="dictName" /></span>    <span class="icon">&nbsp;</span></span></a>
					                    	</s:if>
					                    </s:iterator>
					                    <s:if test="#flag">
						                     <a href="javascript:void(0);">
						                    <span class="select_item"><span class="txt" id="condusetype_txt_id">房屋类型</span>    <span class="icon">&nbsp;</span></span></a>
					                    </s:if>
						                <ul>
						                    <li <s:if test='!#querystring.contains("shh_q.building_type_dict_id")'>class="selected"</s:if>> <a onclick="doSearch(this,1);" href="javascript:void(0);" rel="nofollow">全部</a> </li>
						                    <s:iterator value="@util.BaseParameter@esffwlxDictList">
						                    	<li <s:if test='#querystring.contains(dictId)'>class="selected"</s:if>>
							                    	<a onclick="doSearch(this);" href="javascript:void(0);"
														id="shh_q.building_type_dict_id=<s:property value='dictId'/>"
														><s:property value="dictName" /></a>
												</li>
						                    </s:iterator>
						                </ul>
						            </li>
						            <!-- house type end -->
						
						            <!-- house floor start -->
						            <li id="condfloor_id">
						            	<s:set name="flag" value="true"/>
						            	<s:iterator value="@util.BaseParameter@esflcDictList">
					                    	<s:if test='#querystring.contains(dictId)'>
					                    		<s:set name="flag" value="false"/>
					                    		<a href="javascript:void(0);">
					                    			<span class="select_item"><span class="txt" id="condfloor_txt_id" style="color:#f60;"><s:property value="dictName" /></span>    <span class="icon">&nbsp;</span></span></a>
					                    	</s:if>
					                    </s:iterator>
					                    <s:if test="#flag">
						                     <a href="javascript:void(0);">
						                     	<span class="select_item"><span class="txt" id="condfloor_txt_id">楼层</span><span class="icon">&nbsp;</span></span></a>
					                    </s:if>
						                <ul>
						                    <li <s:if test='!#querystring.contains("shh_q.floor_dict_id")'>class="selected"</s:if>> <a onclick="doSearch(this,1);" href="javascript:void(0);" rel="nofollow">全部</a> </li>
						                    <s:iterator value="@util.BaseParameter@esflcDictList">
						                    	<li <s:if test='#querystring.contains(dictId)'>class="selected"</s:if>>
							                    	<a onclick="doSearch(this);" href="javascript:void(0);"
														id="shh_q.floor_dict_id=<s:property value='dictId'/>"
														><s:property value="dictName" /></a>
												</li>
						                    </s:iterator>
						                </ul>
						            </li>
						            <!-- house floor end -->
						        </ul>
						    </div>
						</div>
					</div>
					</div>
				</div>
			</div>
<!-- 			<i class="list-icons"> </i> -->
			<div class="list-contents" style="margin: 20px auto;width: 1190px;">
				<div class="list-results">
					<div class="key-sort">
						<div class="page-tab">
							<div class="sort-tab">
								<ul>
									<li class="current">全部二手房</li>
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
								</h1> 二手房
							</span>
						</div>
					</div>

					<div class="key-list">
						<div style="display: block">
							<s:iterator value="rp.resultList">
								<div class="item-mod" onclick="window.open('showHouseDetail.action?oid=<s:property value="oid" />')">
									<a class="pic" target="_blank"> 
										<img width="180" height="135" src="<s:property value='pics_1!=null&&pics_1.split(";").length>1&&pics_1.split(";")[1]' />" alt="" />
									</a>
									<div class="infos">
										<div class="lp-name" style="width:507px;">
											<h3 title="<s:property value='house_name' />">
												<a class="items-name"   target="_blank" style="max-width: 457px;overflow: hidden;display: inline-block;height:24px;">
													<s:property value='house_name' />
												</a>
											</h3>
											<s:if test='pics_1!=null&&pics_1.split(";").length>1'>
												<i class="status-icon reco">多图</i> 
											</s:if>
										</div>
										<p class="address">
											<a  target="_blank"><s:property value="acreage"/> </a>
											|
											<a  target="_blank"><s:property value="hous_type"/> </a>
											|
											<a  target="_blank"><s:property value="unit_price"/> </a>
											|
											<a  target="_blank"><s:property value="floor"/> </a>
											|
											<a  target="_blank"><s:property value="building_age"/>年建造 </a>
										</p>
										<p>
											<s:property value="village"/>
											<a  class="list-map" target="_blank">
												[<s:property value='sysArea.areaName' />-<s:property value='address' />] </a>
										</p>
										<div class="data-brief" style="color:#666;">
<!-- 											<em style="color:#666;">最新开盘： </em>  -->
											最后更新&nbsp;<s:date name='latest_edit_date' format="yyyy-MM-dd HH:mm"/>
										</div>
									</div>
									<div class="favor-pos">
										<p class="price" style="color:#FF6600;">
											<span><s:property value='selling_price' /></span>万
										</p>
										<div class="discount-item">
											<p class="favor-tag">
<%-- 												<em class="discount-txt" title="<s:property value='selling_price' />"><s:property value='selling_price' /></em> --%>
											</p>
										</div>
										<p class="tel">
<%-- 											<i class="list-ico tel-icon"> </i><s:property value='sales_line' /> --%>
										</p>
									</div>
<!-- 									<a href="" class="qiang-icon" soj="16jnh_loupanlist_hongbao" -->
<!-- 										target="_blank" title="抢基金"> <img -->
<!-- 										src="web/images/hongbao.gif" width="57" height="45" /> -->
<!-- 									</a> -->
								</div>
							</s:iterator>
						</div>
						<div style="display: none">
						
						</div>
					</div>
					<!--邮箱订阅-->
					<div class="list-page">
						<span class="total"> 共有 <em> <s:property value='rp.allRows' /> </em> 个有关&nbsp;<s:property value="districtName"/>&nbsp;二手房
						</span>
						<div id="pagination" style="width:500px;height: 30px;float: right;">
						</div>
					</div>
					<script>
						laypage({
							cont : jQuery('#pagination'), // 容器。值支持id名、原生dom对象，jquery对象,
							pages : <s:property value='rp.maxPage' />, // 总页数
							skin : '#6AB928',
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
				<%@ include file="include/house_right.inc"%>
			</div>
		</div>
		<!--底部开始 -->
		<%@ include file="include/footer.inc"%>
		<!--底部结束 -->
</body>
</html>
