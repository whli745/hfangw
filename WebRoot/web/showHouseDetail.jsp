<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="keywords" content="华房网，合肥华房网络科技有限公司，合肥房产网，房产信息网，合肥最新楼盘，合肥团购楼盘"/>
<meta name="description" content=""/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title><s:property value="secondHandHouse.house_name"/>，<s:property value="secondHandHouse.village"/>二手房，<s:property value="secondHandHouse.hous_type"/>，<s:property value="secondHandHouse.selling_price"/>万元-华房网</title>
<%@ include file="include/heard.inc"%>
<script type="text/javascript" src="web/js/b34af0157d629e6e4743e831e5547cfe.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" media="all" href="web/css/esf.css"/>
<link rel="stylesheet" rev="stylesheet" href="web/css/global.css" type="text/css" />
</head>
<body>
	<div class="wrapper">
		<!--头部开始 -->
		<div class="header">
			<%@ include file="include/top_no_dis.inc"%>
			<%@ include file="include/nav_detail.inc"%>
		</div>
		<!-- header end -->
		<div class="content w1190 auto">
			<div class="tit cf" style="margin-top:10px;margin-bottom: 10px;">
			 <h3 class="fl"><s:property value="secondHandHouse.house_name"/></h3>
			</div>
			<div class="mainbox cf">
				<div class="lbox">
					<div id="photoSlide" class="box photobox">
						<ul class="tabs cf">
							<li><a href="javascript:void(0);" class="now"><em
									class="p_icon"></em><span>室内图（<s:property value="secondHandHouse.pics_1.split(\";\").length-1"/>）</span></a></li>
							<li class="lbl"><a href="javascript:void(0);" class='next'><em
									class="p_icon"></em><span>室外图（<s:property value="secondHandHouse.pics_2.split(\";\").length-1"/>）</span></a></li>
						</ul>
						<div class="con">
							<div class="tabscon tnow">
								<div class="bigps photoslide cf">
									<a href="javascript:void(0);" class="btn_ps btn_pre"><em
										class="p_icon"></em></a>
									<div class="picCon">
										<ul class="picMove cf"
											style="margin-left: 0; overflow: hidden;">
											<s:iterator value="secondHandHouse.pics_1!=null&&secondHandHouse.pics_1.split(\";\")" var="pic" status="idx">
												<s:if test="#idx.index>0">
													<li><a href="javascript:void(0);"> 
															<img src="<s:property value='#pic'/>" alt="" data-src="" />
														</a>
													</li>
												</s:if>
											</s:iterator>
										</ul>
									</div>
									<a href="javascript:void(0);" class="btn_ps btn_next"><em
										class="p_icon"></em></a>
								</div>
								<div class="thumb">
									<div class="photoslide cf">
										<a href="javascript:void(0);" class="p_icon btn_ps btn_pre"></a>
										<div class="picCon">
											<ul class="picMove cf">
												<li>
													<s:iterator value="secondHandHouse.pics_1!=null&&secondHandHouse.pics_1.split(\";\")" var="pic" status="idx">
														<s:if test="#idx.index>0">
															<a href="javascript:void(0);" 
															<s:if test="#idx.index==1">
															class="now"
															</s:if>						
															><img src="<s:property value='#pic'/>" alt="" /></a>
														</s:if>
													</s:iterator>
												</li>
											</ul>
										</div>
										<a href="javascript:void(0);" class="p_icon btn_ps btn_next"></a>
									</div>
								</div>
							</div>
							<div class="tabscon next" style="display: none;">
								<div class="bigps photoslide cf">
									<a href="javascript:void(0);" class="btn_ps btn_pre"><em
										class="p_icon"></em></a>
									<div class="picCon">
										<ul class="picMove cf" style="overflow: hidden;">
											<s:iterator value="secondHandHouse.pics_2!=null&&secondHandHouse.pics_2.split(\";\")" var="pic" status="idx">
												<s:if test="#idx.index>0">
													<li><a href="javascript:void(0);"> 
															<img src="<s:property value='#pic'/>" alt="" data-src="" />
														</a>
													</li>
												</s:if>
											</s:iterator>
										</ul>
									</div>
									<a href="javascript:void(0);" class="btn_ps btn_next"><em
										class="p_icon"></em></a>
								</div>
								<div class="thumb">
									<div class="photoslide cf">
										<a href="javascript:void(0);" class="p_icon btn_ps btn_pre"></a>
										<div class="picCon">
											<ul class="picMove cf">
												<li>
													<s:iterator value="secondHandHouse.pics_2!=null&&secondHandHouse.pics_2.split(\";\")" var="pic" status="idx">
														<s:if test="#idx.index>0">
															<a href="javascript:void(0);" 
															<s:if test="#idx.index==1">
															class="now"
															</s:if>						
															><img src="<s:property value='#pic'/>" alt="" /></a>
														</s:if>
													</s:iterator>
												</li>
											</ul>
										</div>
										<a href="javascript:void(0);" class="p_icon btn_ps btn_next">11</a>
									</div>
								</div>
							</div>
						</div>
						<script>
						    var room_in_count = '<s:property value="secondHandHouse.pics_1.split(\";\").length-1"/>';
						    var room_out_count = '<s:property value="secondHandHouse.pics_2.split(\";\").length-1"/>';
						</script>
						 <script>
						    var AJK = AJK || {};
						    AJK.SeoRecommend = AJK.SeoRecommend || {};
						    AJK.SeoRecommend.type = 0;
						</script>
						<script>
					        var favParams = {
					            uniqueId: 'unique_dialog',
					            popupId: 'popup_fav_apf_id_9',
					            contentId: 'content_fav_apf_id_9',
					            propID: '470527290',
					            type: '11'
					        };
					    </script> 
					    <script type="text/javascript">
						     //收藏参数
						    var add_favorite_param = {
						        'htype' : 1,
						        'ptype' : 11,
						        'fid' : '470527290',
						        'view_favorate_url' : 'http://user.anjuke.com/favorite/zufang/W0QQCurNavZ1?from=Site_Rent_Register_FP'
						    };
						    var prop_info = {
						            'prop_id' : '470527290',
						            'source_type' : '1',
						            'pro_price' : ' 4270000'
						    };
						    var city_id = 47;
						    //房产知识
						    var comm_info = {
						            'comm_id' : '353159',
						            'comm_name' : '万泰麓溪公馆',
						            'comm_lat' : '37.515730527821',
						            'comm_lng' : '121.44354970308'
						    };
						    var broker_info = {
						            'broker_id' : '2121864',
						            'chat_id' : '2005692385',
						            'is_actived' : '1'
						        };
						
						    var room_in_count = '<s:property value="secondHandHouse.pics_1.split(\";\").length-1"/>';
						    var room_out_count = '<s:property value="secondHandHouse.pics_2.split(\";\").length-1"/>';
						
						    var user_anjuke = 'http://user.anjuke.com/';
						    var user_anjuke_favorite = user_anjuke+'favorite/fangyuan';
						    var user_type = '';
						    var user_favorite = '0';
						    var user_anjuke_login_111_url = 'http://user.anjuke.com/my/login?history=aHR0cDovL3l0LmFuanVrZS5jb20vcHJvcC92aWV3L0E0NzA1MjcyOTA/ZmF2b3JpdGU9MTEx';
						    var user_anjuke_register_111_url = 'http://user.anjuke.com/register/?history=aHR0cDovL3l0LmFuanVrZS5jb20vcHJvcC92aWV3L0E0NzA1MjcyOTA/ZmF2b3JpdGU9MTEx';
						    var user_anjuke_login_112_url = 'http://user.anjuke.com/my/login?history=aHR0cDovL3l0LmFuanVrZS5jb20vcHJvcC92aWV3L0E0NzA1MjcyOTA/ZmF2b3JpdGU9MTEy';
						    var user_anjuke_register_112_url = 'http://user.anjuke.com/register/?history=aHR0cDovL3l0LmFuanVrZS5jb20vcHJvcC92aWV3L0E0NzA1MjcyOTA/ZmF2b3JpdGU9MTEy';
						    var register_source_code = 'Site_ESF_Register_FP';
						    var register_source_code_cp = 'Site_ESF_Price_FP';
						    var register_source_code_nh = 'Site_ESF_HouseInfo_FP';
						    var register_source_code_sm = 'Site_ESF_MP_FP';
						    var link_info = {
						        'broker_id' : "2121864" || "",
						        'prop_id' : "470527290" || "",
						        'comm_id' : '353159' || "",
						        'chat_id' : "2005692385" || "",
						        'nearby_type' : "" || "",
						        'prop_search_url_pre' : 'http://yt.anjuke.com/sale/rd1/?kw='
						    };
						    var anjuke_city_url = '';
						    var pro_type = 1;// ershoufang 1 or zufang 2
						    var userId = "5EA2C4AA-23F8-9F0F-34D3-245F830C2EFA";
						    var history_url =  "/vppvQueue/?id=1&type=1201";
						    var open_new_recommend = 1;
						    // 晶赞动态传参
						    var __zp_tag_params = {
						        "outerid_s": "A470527290",
						        "cid_s": "353159",
						        p_zp_prodstype: "05d0e8b539620e734e5770ce892e2e20",
						        p_zp_prods: {
						            "outerid": "A470527290",
						            "cid": "353159",
						            "name": "万泰麓溪公馆",
						            "category": "烟台",
						            "subcategory": "莱山",
						            "thirdcategory": "海滨迟家",
						            "p_totalprice": "4270000",
						            "housetype":"6室3厅3卫",
						            "p_img_url": "http://d.pic1.ajkimg.com/display/hj/7534a012674c0e8a8af56e327556981e/420x315.jpg",
						            "p_clickurl": "http://yt.anjuke.com/sale/rd1/?kw=万泰麓溪公馆"
						        }
						    };
						// 聚效商品回传代码
						    var _mvq = window._mvq || []; 
						    window._mvq = _mvq;
						    _mvq.push(['$setAccount', 'm-184379-0']);
						    _mvq.push(['$logConversion']);
						    _mvq.push(['$setGeneral', 'goodsdetail', '','','']);
						    _mvq.push(['setPageUrl',"http://yt.anjuke.com/sale/rd1/?kw=万泰麓溪公馆"]);
						    _mvq.push(['$addGoods', '', '', '万泰麓溪公馆', 'A470527290', '4270000', 'http://d.pic1.ajkimg.com/display/hj/7534a012674c0e8a8af56e327556981e/420x315.jpg', '', '', '', '', '', '', '二手房', '烟台', '海滨迟家']);
						    _mvq.push(['$addPricing','']);
						    _mvq.push(['$logData']);
						
						    //板块id
						    var block_id = '6337';
						</script> 
						<script type="text/javascript" src="web/js/ershou_web_property_view_view.js"></script>
						<script type="text/javascript">
						    J.ready(function(){
						        try{
						            window.global = window.global|| {};
						        }catch (e){
						            window.global = {};
						        }
						        var exposure =  new J.ui.exposure()
						        exposure.setSite("anjuke-exposure-npv");
						        exposure.setPage("Ershou_Web_Property_View_ViewPage");
						        exposure.setPageName("Anjuke_View_Property");
						        exposure.setReferrer(document.referrer);
						        exposure.setNGuid("");
						        exposure.setNUid("");
						        exposure.start();
						        window.global.exposure = exposure;
						    });
						</script>
						<script type="text/javascript">
							(function(param){
							       var c = {query:[], args:param||{}};
							       c.query.push(["_setAccount","8"]);//固定参数
							       (window.__zpSMConfig = window.__zpSMConfig||[]).push(c);
							       var zp = document.createElement("script"); zp.type = "text/javascript"; zp.async = true;
							       zp.src = ("https:" == document.location.protocol ? "https:" : "http:") + "//cdn.zampda.net/s.js";
							       var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(zp, s);
							}) (window.__zp_tag_params);
						</script> 
						<a style="display: block; height: 1px; font-size: 1px; line-height: 1px" data-trace="{Anjuke_View_Property_viewed_screenBtm:1}"></a>
						<div class="pinfo" id="prop_infor">
							<a href="javascript:void(0);" class="startLink"></a>
							<div class="box prop-info-box" id="prop_infor">
								<div class="phraseobox cf">
									<div class="litem fl">
										<dl class="p_phrase cf">
											<dt>售价</dt>
											<dd class="og">
												<strong><span class="f26" style="color:#FF6600;"><s:property value="secondHandHouse.selling_price"/></span>&nbsp;<em>万</em></strong>
											</dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>参考首付</dt>
											<dd><s:property value="secondHandHouse.refe_down_payment"/>万</dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>参考月供</dt>
											<dd id="reference_monthpay"></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>单价</dt>
											<dd><s:property value="secondHandHouse.unit_price"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>所在小区</dt>
											<dd>
												<a href="#SH"><s:property value="secondHandHouse.village"/></a> 
											</dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>位置</dt>
											<dd>
												<a href="#SH"><s:property value="secondHandHouse.sysArea.areaName" /></a> 
												<a href="#SH"><s:property value="secondHandHouse.address"/></a>
											</dd>
										</dl>
									</div>
									<div class="ritem fr">
										<dl class="p_phrase cf">
											<dt>房型</dt>
											<dd><s:property value="secondHandHouse.hous_type"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>面积</dt>
											<dd><s:property value="secondHandHouse.acreage"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>朝向</dt>
											<dd><s:property value="secondHandHouse.orientations"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>楼层</dt>
											<dd><s:property value="secondHandHouse.floor"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>装修</dt>
											<dd><s:property value="secondHandHouse.deco_standard"/></dd>
										</dl>
										<dl class="p_phrase cf">
											<dt>类型</dt>
											<dd>
												<s:iterator value="@util.BaseParameter@esffwlxDictList" status="idx">
													<s:if test="dictId eq secondHandHouse.building_type_dict_id"><s:property value="dictName"/></s:if>
												</s:iterator>
											</dd>
										</dl>
									</div>
								</div>
								<div class="pro_detail">
									<div class="pro_links" id="proLinks" style="display: none"></div>
									<div class="pro_main cf" id="propContent" style="">
										<div class="pro_con wb">
											<s:property value="secondHandHouse.detail_info" escape="false"/>
										</div>
									</div>
									<div class="t_c" style="display: none">
										<!--a href="javascript:void(0);" class="btn_show btn_all" title="显示全部"><i class="p_icon"></i></a-->
										<a href="javascript:void(0);" class="btn_show btn_up"
											title="收起全部"><i class="p_icon"></i></a>
									</div>
									<div class="text-mute extra-info">房源编号：<s:property value="secondHandHouse.refe_month_for" escape="false"/>，发布时间：<s:date name='secondHandHouse.latest_edit_date' format="yyyy年MM月dd日"/></div>
								</div>
							</div>
						</div>
					</div>
					<a style="display: block; height: 1px; font-size: 1px; line-height: 1px" data-trace="{Anjuke_View_Property_viewed_homeinfo:1}"></a>

					<div data-marker="peripheral_support" class="life-mod" >
						<div class="main-title">
							<h3 class="hd">
								生活配套<span class="sub-hd"></span><a name="SH"></a>
							</h3>
						</div>
						<div class="mod life-content" style="width: 840px;">
							<div class="life-bmap" id="map-box" style="width: 365px;"></div>
							<div class="life-detail" style="width: 470px;">
								<ul class="life-tab clearfix" id="life-tab">
									<li id="life-tab-tra" class="active"><a href="javascript:">交通</a></li>
									<li id="life-tab-biz"><a href="javascript:">商业</a></li>
									<li id="life-tab-edu"><a href="javascript:">教育</a></li>
									<li id="life-tab-hos"><a href="javascript:">医院</a></li>
								</ul>
								<div class="life-list">
									<p id="life-list-type" class="info gray"></p>
									<p id="life-list-count" class="info"></p>
									<ul id="life-list" class="list-ul clearfix yahei">
									</ul>
								</div>
							</div>
						</div>
					</div>

					<div>
						<div class="list-mod">
							<div class="hd clearfix">
								<h4>猜你喜欢</h4>
							</div>
							<div class="hot-mod mod">
								<ul class="clearfix">
									<s:iterator value="shhList" status="idx">
										<li>
											<a href="showHouseDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="proj_name"/>">
											<img src="<s:property value='pics_1!=null&&pics_1.split(";").length>1&&pics_1.split(";")[1]'/>" width="200" height="150"/></a>
											<a href="showHouseDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="house_name"/>" class="lpname" style="font-size: 13px;font-weight: bolder;line-height: 18px;height: 18px;"><s:property value="house_name"/></a>
											<p class="lpprice" style="font-size: 14px;font-weight: bolder;">
												<em><s:property value="selling_price"/>&nbsp;万</em>
											</p>
											<a style="font-size: 12px;margin-top: 4px;"><s:property value="hous_type"/></a><br/>
											<a style="font-size: 12px;margin-top: 4px;"><s:property value="village"/></a>
										</li>
									</s:iterator>
								</ul>
							</div>
						</div>
						<div style="display: none"></div>
					</div>
					<!--monthbox s-->
					<div class="monthbox" id="refer_month">
						<div class="hd">
							<h4>参考月供</h4>
							<h5 class="gray"><s:property value="secondHandHouse.village"/></h5>
						</div>
						<div class="box">
							<!-- pie chart -->
							<div class="monthpay phraseobox cf">
								<div class="litem fl">
									<p class="desc">默认为参考首付和参考贷款，您可以更换下面选项获得自己的专属贷款详情</p>
									<dl class="p_phrase cf">
										<dt>贷款类别:</dt>
										<dd>
											<select id="paies">
												<option value="1">商业贷款</option>
												<option value="2">公积金贷款</option>
											</select> <i class="icon-help icon-paies"></i>
											<div class="p_arowCon commerce">
												<p>商业贷款是以银行信贷资金为来源向购房者个人发放的贷款。</p>
												<!--箭头在下面p_arw_b  箭头在上面p_arw_t-->
												<em class="p_arw p_arw_b" style="left: 15px; bottom: -12px;"></em>
												<!--em class="p_arw p_arw_t" style="left:15px;top:-12px;"></em-->
											</div>
											<div class="p_arowCon fund">
												<p>公积金贷款是指缴存住房公积金的职工享受的贷款，国家规定，凡是缴存公积金的职工均可按公积金贷款的相关规定申请公积金贷款。</p>
												<!--箭头在下面p_arw_b  箭头在上面p_arw_t-->
												<em class="p_arw p_arw_b" style="left: 15px; bottom: -12px;"></em>
												<!--em class="p_arw p_arw_t" style="left:15px;top:-12px;"></em-->
											</div>
										</dd>
									</dl>
									<dl class="p_phrase cf">
										<dt>按揭成数:</dt>
										<dd>
											<select id="pointes">
												<option value="9">9成</option>
												<option value="8">8成</option>
												<option value="7">7成</option>
												<option value="6">6成</option>
												<option value="5">5成</option>
												<option value="4">4成</option>
												<option value="3">3成</option>
												<option value="2">2成</option>
												<option value="1">1成</option>
											</select> <i class="icon-help icon-ratio"></i>
											<div class="p_arowCon ratio">
												<p>是指所贷款的额度总额占房款总额的比例。按揭成数=贷款的额度/房款总额</p>
												<!--箭头在下面p_arw_b  箭头在上面p_arw_t-->
												<em class="p_arw p_arw_b" style="left: 15px; bottom: -12px;"></em>
												<!--em class="p_arw p_arw_t" style="left:15px;top:-12px;"></em-->
											</div>
										</dd>
									</dl>
									<dl class="p_phrase cf">
										<dt>贷款时间:</dt>
										<dd>
											<select id="yeares">
												<option value="0.5">6个月(6期)</option>
												<option value="1">1年(12期)</option>
												<option value="2">2年(24期)</option>
												<option value="3">3年(36期)</option>
												<option value="4">4年(48期)</option>
												<option value="5">5年(60期)</option>
												<option value="6">6年(72期)</option>
												<option value="7">7年(84期)</option>
												<option value="8">8年(96期)</option>
												<option value="9">9年(108期)</option>
												<option value="10">10年(120期)</option>
												<option value="11">11年(132期)</option>
												<option value="12">12年(144期)</option>
												<option value="13">13年(156期)</option>
												<option value="14">14年(168期)</option>
												<option value="15">15年(180期)</option>
												<option value="16">16年(192期)</option>
												<option value="17">17年(204期)</option>
												<option value="18">18年(216期)</option>
												<option value="19">19年(228期)</option>
												<option value="20" selected="selected">20年(240期)</option>
												<option value="21">21年(252期)</option>
												<option value="22">22年(264期)</option>
												<option value="23">23年(276期)</option>
												<option value="24">24年(288期)</option>
												<option value="25">25年(300期)</option>
												<option value="26">26年(312期)</option>
												<option value="27">27年(324期)</option>
												<option value="28">28年(336期)</option>
												<option value="29">29年(348期)</option>
												<option value="30">30年(360期)</option>
											</select>
										</dd>
									</dl>
									<div>
										<p class="desc-bottom text-mute">
											税费由营业税、个人所得税、契税等构成。具体税费因房 源不同有差异，详情请咨询经纪人。</p>
										<a target="_blank" class="btncounter"
											href="http://www.daixiaomi.com/labs/house_calculator/?"
											onclick="J.site.trackEvent('piechartford')">计算您的贷款详情</a>
									</div>
								</div>
								<div class="ritem fr chartitem">
									<div class="">
										<div class="piechart" id="piechart">
											<!-- SVG Pie Chart -->
										</div>
										<div class="legend-container">
											<h3 style="display: none;">
												<ins>月均还款：</ins>
												<span class="orange" id="yearpays">0000</span>元/月<strong>
													(<span id="yearcounts" style="font-size: 15px; color: #999">20</span>年)
												</strong>
											</h3>
											<ul>
												<li id="firstpay"><i class="legend firstpay"></i>参考首付：<span><s:property value='secondHandHouse.refe_down_payment' escape='false'/></span>万(<span>3成</span>)</li>
												<li id="repayed"><i class="legend repayed"></i>贷款金额：<span>298.90</span>万(<span>成</span>)</li>
												<li id="payedpoint"><i class="legend payedpoint"></i>支付利息：<span></span>万(利率<span></span>%)</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<script>
							    var commInform = {
							        Allprice: "<s:property value='secondHandHouse.selling_price' escape='false'/>" || 0,
							        firsetPrice: "<s:property value='secondHandHouse.refe_down_payment' escape='false'/>" || 0
							    };
							    var cal_grate = [0.0275,0.0325];
							    var cal_brate = [0.0435,0.0435,0.0475,0.0475,0.049];
							</script>
						</div>
					</div>
					<a style="display: block; height: 1px; font-size: 1px; line-height: 1px" data-trace="{Anjuke_View_Property_viewed_monthinfo:1}"></a>
					<!-- 同月供优质房 -->
					<div id="areaPriceRecommendProp" class="likebox" style=""></div>
					<div class="trendbox" id="price_trend">
						<div class="">
							<!-- line chart -->
							<script>
							    var ad_param={};
							    ad_param.lat = 0;
							    ad_param.lng = 0;
							    ad_param.city_id = 0;
							    ad_param.aifang_ad_url = "";
							    
							    var priceTrend = {
							        icon: ("http://pages.ajkcdn.com/site/img/global/2" || "") + "/char_circle.png",
							        data: {
							            id: "353159",
							            commName: "万泰麓溪公馆" || "",
							            blockName: "海滨迟家" || "",
							            avgPrice: "13685" || 0
							        },
							        url: "/v3/ajax/prop/pricetrend/"//api url, http://www.anjuke.com/v3/ajax/prop/pricetrend/?commid=1
							    };
							</script>
						</div>
					</div>

					<!-- 投资潜力房 -->
					<div id="potentialRecommendProp" class="likebox" style=""></div>

					<a style="display: block; height: 1px; font-size: 1px; line-height: 1px" data-trace="{Anjuke_View_Property_viewed_qaBtm:1}"></a>
				</div>
				<div class="rbox">
					<div class="ifxside-recomm">
						<h3 class="hd">推荐楼盘</h3>
						<ul class="etlist">
							<s:iterator value="hpList_1" status="idx">
						     	<li onclick="window.open('showBuidingDetail.action?oid=<s:property value="oid"/>')">
						        	<img src="<s:property value="pics!=null&&pics.split(\";\").length>1&&pics.split(\";\")[1]"/>" style="width: 210px;height: 140px;"/>
						            <h4 class="tit" style="overflow: hidden;vertical-align: bottom;white-space: nowrap;text-overflow: ellipsis;width: 220px;"><s:property value="proj_name"/></h4>
						            <p class="price"><s:property value="priceList[0].val_2"/><em class="ifx-num"><s:property value="priceList[0].val_3"/></em><s:property value="priceList[0].val_4"/></p>		
						        </li>
						        <s:if test="#idx.index<hpList_1.size-1"><li class="line"></li></s:if>
						     </s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>

		<!--底部开始 -->
		<%@ include file="include/footer.inc"%>
		<!--底部结束 -->
	</div>
	<script type="text/javascript" src="web/js/hfangw_web_loupan_index.js"></script>
	<script type="text/javascript">
	    (function ($) {
	//     	Loupan.Map({panName: '昌吉东路于塘路交叉口南', lat: 31.299189, lng: 121.199244});
	    	Loupan.Map({panName: '<s:property value="secondHandHouse.village"/>', lat: <s:property value='secondHandHouse.lng_lat.split(";")[1]'/>, lng: <s:property value='secondHandHouse.lng_lat.split(";")[0]'/>});
	        //send soj
	        $('#life-map-con .more a, #life-map-con .img a').on('click.sendsoj',function(){
	            XF.Soj.send("{from:loupan_index}",XF.Soj.param);
	        });
	        XF.Vars.loupan_id = '389715';
	    })(jQuery);
	</script>
<script type="text/javascript">
    (function(){
        function sendSoj(){

            var fixReferer = '',
                curReferer = document.referrer,
                site = 'anjuke',
                CustomParams = '{"v":"2.0","channel":1,"userId":0,"userType":0,"tradeType":1,"proId":470527290,"COMMID":353159,"brokerId":2121864,"brokerType":2,"hpType":1,"entry":99,"uniqid":"5720a0fbc02f02.80738874","romar_item":"00003440700353159","userProId":"A470527290","ab":"expclick-AJKERSHOUFANG_101_65949717"}',
                st =  new J.logger.Tracker();
            st.setSite(site);
            st.setPage("Ershou_Web_Property_ViewPage");
            st.setPageName("Anjuke_View_Property");
            st.setReferrer(curReferer ? curReferer : fixReferer);
            st.setNGuid("aQQ_ajkguid");
            st.setNUid("ajk_member_id");
            st.setCustomParam(CustomParams);
            try{
                st.track();
            }catch(err){
                J.logger.log('sendSojError',err);
            }
        }
        sendSoj();
    }());
    // 58 统计 不要cp、sc参数
    (function(){

        try{
            var fixReferer = '',
                curReferer = document.referrer,
                site = 'anjuke',
                st = new SiteTracker();
                st.setSite(site);
                st.setPage("Ershou_Web_Property_ViewPage");
                st.setPageName("Anjuke_View_Property");
                st.setReferer(curReferer ? curReferer : fixReferer);
                st.buildParams();
            var _trackUrl = st.getParams();
                delete _trackUrl.sc;
                delete _trackUrl.cp;
            window._trackURL = JSON.stringify(_trackUrl);

            function loadTrackjs(){
                 var s = document.createElement('script');
                 s.type = 'text/javascript';
                 s.async = true;
                 s.src = 'http://tracklog.58.com/referrer_anjuke_m.js?_=' + Math.random();
                 var b = document.body;
                 s.onload = function () {
                    st.setSite(site+'-npv');
                    st.setPage("Ershou_Web_Property_ViewPage"+"_tracklog");
                    st.setPageName("Ershou_Web_Property_ViewPage"+"_tracklog");
                    st.track();
                 }
                 s.onerror = function () {
                     st.setSite(site+'-npv');
                     st.setPage("Ershou_Web_Property_ViewPage"+"_tracklog_error");
                     st.setPageName("Ershou_Web_Property_ViewPage"+"_tracklog_error");
                     st.track();
                 }
                 b.appendChild(s);
            }
            loadTrackjs()
        }catch(e){
        }
    })();
</script> 
<!-- START: 性能测试监控 --> 
<script>
    J.ready(function(){
        var v = 1456202590,
            src = "http://pages.aifcdn.com/prism/performance.js?v="+v;
        J.load(src, function(){
            window.perfConfig = {
                pageName : 'Ershou_Web_Property_ViewPage',
                siteName : 'ershoufang'
            };
        })
    });
</script> 
<!-- END: 性能测试监控 --> 

<script type="text/javascript">
    J.ready(function(){
        try{
            window.global = window.global|| {};
        }catch (e){
            window.global = {};
        }
        var exposure =  new J.ui.exposure()
        exposure.setSite("anjuke-exposure-npv");
        exposure.setPage("Ershou_Web_Property_View_ViewPage");
        exposure.setPageName("Anjuke_View_Property");
        exposure.setReferrer(document.referrer);
        exposure.setNGuid("");
        exposure.setNUid("");
        exposure.start();
        window.global.exposure = exposure;
    });
</script><script type="text/javascript">
(function(param){
       var c = {query:[], args:param||{}};
       c.query.push(["_setAccount","8"]);//固定参数
       (window.__zpSMConfig = window.__zpSMConfig||[]).push(c);
       var zp = document.createElement("script"); zp.type = "text/javascript"; zp.async = true;
       zp.src = ("https:" == document.location.protocol ? "https:" : "http:") + "//cdn.zampda.net/s.js";
       var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(zp, s);
}) (window.__zp_tag_params);
</script>  
</body>
</html>
