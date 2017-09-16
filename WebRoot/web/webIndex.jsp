<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:property value="districtName_"/>房产信息网-华房网</title>
	<%@ include file="include/heard.inc" %>
	<script type="text/javascript" src="web/js/slider.js"></script>
	<script type="text/javascript" src="web/js/right-slide.js"></script>
</head>
<body>
	<div id="top">
		<div id="izl_rmenu" class="izl-rmenu">
			<div class="btn btn-wx">
				<img class="pic" src="web/images/weixin.jpg"/>
			</div>
			<div class="btn btn-hb">
				<img class="pic" src="web/images/hongbao.jpg"
					onclick="document.getElementById('firstBtn').click();" />
			</div>
			<div class="btn btn-top"></div>
		</div>
	</div>
	<div class="wrapper">
		<div class="header">
			<%@ include file="include/top.inc" %>
			<%@ include file="include/nav_home.inc" %>
			<div id="banner_tabs" class="flexslider">
		        <ul class="slides">
		            <li>
		                <a title="" href="javascript:void(0);">
		                    <img style="width:100%; height:100%;" src="web/images/banner1.jpg"/>
		                </a>
		            </li>
		            <li>
		                <a title="" href="javascript:void(0);">
		                    <img style="width:100%; height:100%;" src="web/images/banner2.jpg"/>
		                </a>
		            </li>
		            <li>
		                <a title="" href="javascript:void(0);">
		                    <img style="width:100%; height:100%;" src="web/images/banner3.jpg"/>
		                </a>
		            </li>
		        </ul>
		        <ul class="flex-direction-nav">
		            <li><a class="flex-prev" href="javascript:;">Previous</a></li>
		            <li><a class="flex-next" href="javascript:;">Next</a></li>
		        </ul>
		        <ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
		            <li><a></a></li>
		            <li><a></a></li>
		            <li><a></a></li>
		        </ol>
		    </div>
		</div>
		<!-- header end -->
		<script>
			jQuery(function(){
				jQuery('.area > ul > li > a').mouseover(function(idx){
					jQuery('.area > ul > li > a').removeClass("current");
					jQuery(this).addClass("current");
					jQuery('.zxlp').hide();
					jQuery(".first-left " + "div[id='div_"+jQuery(this).attr('id')+"']").show();
				});
			});
			$(function(){
				//轮播图
				var bannerSlider = new Slider($('#banner_tabs'), {
					time: 5000,
					delay: 400,
					event: 'hover',
					auto: true,
					mode: 'fade',
					controller: $('#bannerCtrl'),
					activeControllerCls: 'active'
				});
				$('#banner_tabs .flex-prev').click(function() {
					bannerSlider.prev()
				});
				$('#banner_tabs .flex-next').click(function() {
					bannerSlider.next()
				});
			});
		</script>
		<div class="content w1190 auto"><div class="third clearfix">
			<div class="third-left">
				<div class="title micro">
					<span></span><a href="buidingSearch.action?type=2&districtId=<s:property value="districtId"/>" target="_blank">热销楼盘</a> 		
				</div>
				<div class="tglp clearfix">
					<s:if test="hpList_2.size>0">
					<div class="tglp-l">
						<s:if test="hpList_2[0].packetList != null && hpList_2[0].packetList.size() > 0">
							<img class="redp" src="<%=basePath %>web/images/hongbao.gif" title="点击领取" onclick="document.getElementById('firstBtn').click();"/>
						</s:if>
						<s:set name="hp" value="hpList_2[0]"/>
						<div class="tglp-img"><img src="<s:property value='#hp.pics!=null&&#hp.pics.split(\";\").length>1&&#hp.pics.split(\";\")[1]'/>" style="width: 344px;height: 223px;"/></div>
						<div class="tglp-l-tit clearfix"><h3 class="left"><s:property value='#hp.proj_name'/></h3><p class="right">团购人数:<span><s:property value='#hp.attention'/></span>人</p></div>
						<div class="tglp-l-price">价格：<s:property value='#hp.unit_price'/></div>
						<div class="tglp-l-btn"><a id="firstBtn" href="showBuidingDetail.action?oid=<s:property value='#hp.oid'/>&districtId=<s:property value="districtId"/>" target="_blank"><img src="web/images/tglp-l-btn.png" /></a></div>
					</div>
					</s:if>
					<div class="tglp-r">
						<ul class="clearfix">
							<s:iterator value="hpList_2" status="idx">
								<s:if test="#idx.index>0&&#idx.index<5">
								<li onclick="window.open('showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>')" style="cursor: pointer;">
									<s:if test="packetList != null && packetList.size() > 0">
										<img class="li_redp" src="<%=basePath %>web/images/hongbao.gif" title="点击领取" />
									</s:if>
									<div class="tglp-r-pic">
									<img src="<s:property value="pics!=null&&pics.split(\";\").length>1&&pics.split(\";\")[1]"/>" style="width: 200px;height:107px;"/></div>
									<div class="tglp-r-info">
										<p style="width: 162px;overflow: hidden;vertical-align: bottom;white-space: nowrap;text-overflow: ellipsis;"><s:property value="proj_name"/></p>
										<p><s:property value='priceList[0].val_2 eq ""?"价格":priceList[0].val_2'/> : <span><s:property value="priceList[0].val_3"/></span>&nbsp;<s:property value="priceList[0].val_4"/></p>
									</div>
									<div class="tglp-r-btn">
										<img src="web/images/tglp-r-btn.png"/>
									</div>
								</li>
								</s:if>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div class="third-right">
				<div class="lphd">
					<div class="slide-title">
							楼盘活动<a href="newsFrames.action?districtId=<s:property value="districtId"/>" class="right" target="_blank">更多</a>
					</div>
					<ul>
						<s:iterator value="ctList_1">
							<li><a href="newsDetail.action?contentId=<s:property value="contentId"/>&districtId=<s:property value="districtId"/>" target="_blank"><s:property value="contentMainTitle"/></a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="scdt">
					<div class="slide-title">
							市场动态<a href="newsFrames.action" class="right" target="_blank">更多</a>
					</div>
					<ul>
						<s:iterator value="ctList_2">
							<li><a href="newsDetail.action?contentId=<s:property value="contentId"/>&districtId=<s:property value="districtId"/>" target="_blank"><s:property value="contentMainTitle"/></a></li>
						</s:iterator>
					</ul>
				</div>

			</div>
		</div>
		<!-- third end -->
		<div class="second">
			<div class="title micro">
				<span></span> 最新活动专区		
			</div>
			<div class="zxhd">
				<ul class="clearfix">
					<s:iterator value="ctList_1" >
						<li style="cursor:pointer;" onclick="window.open('newsDetail.action?contentId=<s:property value="contentId"/>&districtId=<s:property value="districtId"/>')">
<%-- 						<s:if test="packetList != null && packetList.size() > 0"> --%>
<%-- 							<img class="li_redp" src="<%=basePath %>web/images/hongbao.gif" title="点击领取"/> --%>
<%-- 						</s:if> --%>
						<img src="<s:property value='fileAtta.attaPath'/>" style="width: 225px;height: 200px;"/>
						<s:if test="packetList != null && packetList.size() > 0">
							<div class="zxhd-info" title="点击领取"><p>现金红包</p></div>
						</s:if>
						<div class="zxhd-title"><s:property value='contentMainTitle'/></div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<!-- second end -->
		<div class="first clearfix">
			<div class="first-left">
				<div class="title micro">
					<span></span><a href="buidingSearch.action?type=1&districtId=<s:property value="districtId"/>" target="_blank">最新楼盘</a>
					<div class="area right">
						<ul class="clearfix">
							<s:iterator value="areaList" status="idx">
								<s:if test="#idx.index < 8">
									<li><a id="<s:property value="areaId"/>"
									<s:if test="#idx.index == 0">class="current" </s:if>
									href="buidingSearch.action?type=1&districtId=<s:property value="districtId"/>&q.area_id=<s:property value="areaId"/>" target="_blank"><s:property value="areaName"/>
									</a></li>
								</s:if>
							</s:iterator>
						</ul>
					</div>
				</div>
				<s:iterator value="areaList" status="idx">
					<s:if test="#idx.index < 8">
					<div class="zxlp" <s:if test="#idx.index != 0">
					            		style="display:none;"
					            	</s:if> id="div_<s:property value="areaId"/>">
						<ul class="clearfix">
							<s:iterator value="hp_map_1.get(areaId)" status="idx2">
				            	<li onclick="window.open('showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>')" >
				            		<div class="zxlp-pic">
				            		<img src="<s:property value="pics!=null&&pics.split(\";\").length>1&&pics.split(\";\")[1]"/>" style="width: 226px;height:161px;"/>
				            		</div>
				            		<div class="zxlp-info clearfix"><span class="left tit"><s:property value="proj_name"/></span><span class="right qy"><s:property value="sysArea.areaName"/></span></div>
				            		<div class="zxlp-yh"  title="<s:property value="discount"/>"><s:property value="discount"/>&nbsp;</div>
				            		<div class="zxlp-gz clearfix"><p class="left">已有<span><s:property value="attention"/></span>人关注</p><a class="right" href="javascript:void(0);">分享</a></div>
				            	</li>
							</s:iterator>
						 </ul>	
					</div>
					</s:if>
				</s:iterator>
			</div>
			<script type="text/javascript">
				$(function(){
					$(".hflp > ul > li").next("div").hide();
					$(".hflp > ul > li").next("div").eq("0").show();
					$(".hflp > ul > li").hover(function(){	
						$(".hflp > ul > li").next("div").hide();
						$(this).next("div").show();
					})
				})
			</script>
			<div class="first-right">
				<div class="hflp" style="min-height: 400px;">
					<div class="slide-title">
						<s:property value="districtName_"/>最新楼盘
					</div>
					<div class="hflp-title">
						<ul class="clearfix">
							<li style="margin-left:40px;">楼盘名称</li>
							<li style="margin-left:65px;">区域</li>
							<li style="margin-left:55px;">价格</li>
						</ul>
					</div>	
					<ul class="hflp-list">
						<s:iterator value="hpList_1" status="idx">
						<li>
							<i 
							<s:if test="#idx.index < 3">
							class="hot"
							</s:if>
							><s:property value="#idx.count"/></i>
							<a class="hflp-name" href="showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>" target="_blank" title="<s:property value="proj_name"/>"><s:property value="proj_name"/></a>
							<span class="hflp-area"><s:property value="sysArea.areaName"/></span>
							<a class="hflp-price" href="showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>" target="_blank" title="<s:property value="discount"/>"><s:property value="discount"/></a>
						</li>
						<div class="hflp-info clearfix">
							<div class="hflp-infoimg left">
				            	<img src="<s:property value="pics!=null&&pics.split(\";\").length>1&&pics.split(\";\")[1]"/>" style="width: 100px;height:70px;"/>
							</div>
							<div class="hflp-infotxt left">
								<p title="<s:property value="unit_price"/>">价格：<s:property value="unit_price"/></p>
								<p title="<s:property value="discount"/>">优惠：<s:property value="discount"/></p>
							</div>
						</div>
						</s:iterator>
					</ul>
				</div>
				<!-- hflp end -->
				<div class="hfph">
					<div class="slide-title">
						<s:property value="districtName_"/>购房团购排行
					</div>
					<div class="hfph-title">
						<ul class="clearfix">
							<li style="margin-left:40px;">楼盘名称</li>
							<li style="margin-left:65px;">区域</li>
							<li style="margin-left:55px;">团购人数</li>
						</ul>
					</div>	
					<ul class="hfph-list">
						<s:iterator value="hpList_2" status="idx">
							<li>
								<i 
								<s:if test="#idx.index < 3">
								class="hot"
								</s:if>
								><s:property value="#idx.count"/></i>
								<a class="hfph-name"  href="showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>" target="_blank"><s:property value="proj_name"/> </a>
								<span class="hfph-area"><s:property value="sysArea.areaName"/></span>
								<a class="hfph-price" style="margin-left: 10px;"  href="showBuidingDetail.action?oid=<s:property value="oid"/>&districtId=<s:property value="districtId"/>" target="_blank"><s:property value="attention"/>套</a>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
		</div>
		<!-- first end -->
		<div class="partner">
			<h3>合作伙伴:</h3>
			<ul class="clearfix">
				<li><a><img src="web/images/bd.png"/></a></li>
				<li><a><img src="web/images/ftx.png"/></a></li>
				<li><a><img src="web/images/fdd.png"/></a></li>
				<li><a><img src="web/images/ld.png"/></a></li>
				<li><a><img src="web/images/wk.png"/></a></li>
				<li><a><img src="web/images/hr.png"/></a></li>
				<li><a><img src="web/images/jf.png"/></a></li>
				<li><a><img src="web/images/ajk.png"/></a></li>
			</ul>
		</div>
<!-- content end	-->		
  </div>
  <%@ include file="include/footer.inc" %>
  </div>
<%--   <script type="text/javascript"> var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fe5a643599657538afc75f272b12d99db' type='text/javascript'%3E%3C/script%3E")) </script> --%>
</body>
</html>
