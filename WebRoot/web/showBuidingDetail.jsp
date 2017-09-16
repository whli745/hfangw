<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title><s:property value="housingProj.sysArea.areaName"/>-<s:property value="housingProj.proj_name"/>-华房网</title>
<%@ include file="include/heard.inc"%>
<link rel="stylesheet" href="web/js/imageviewer/viewer.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>web/js/nifty/css/component.css" />
<script>
jQuery(function () {  
	jQuery(window).scroll(function () {  
        var top = jQuery(window).scrollTop() + 200;  
        var left = jQuery(window).scrollLeft() + 320;  
        jQuery("#editInfo").animate({ "top": top }, 0);  //方式一  效果比较理想  
    });
	jQuery('#yzm').click(function(){
		jQuery("#yzm").attr("src", "imageServlet?r=" + Math.random());
	});
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	jQuery('#saveBtn').click();
	    	return false;
	     }
	}
	jQuery('.mainDiv input').click(function(){
		layer.closeAll();
	});
	jQuery('.md-overlay').click(function(){
		layer.closeAll();
	});
});

function toSave(){
	var linkman = jQuery.trim(document.getElementById("linkMan").value);
	var linkPhone = jQuery.trim(document.getElementById("linkPhone").value);
	var yzm = jQuery.trim(document.getElementById("yzmText").value);
	if (!jQuery("#tipchk").is(':checked')) {
		common.alert("请先阅读并同意《华房网红包服务协议》");
		return false;
	}
	if (linkman == "" || linkman == '请填写您的姓名') {
		common.tips('请填写您的姓名！', "#linkMan",3);
		return false;
	}
	if (linkPhone == "" || linkPhone == '请输入您的手机号') {
		common.tips('请输入您的手机号！', '#linkPhone',3);
		return false;
	}
	var telReg = !!jQuery('#linkPhone').val().match(/^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/);
	if (!telReg) {
		common.tips('您的手机号码填写错误！', '#linkPhone',3);
		return false;
	}
	if (yzm == "" || yzm == '请输入验证码') {
		common.tips('请输入验证码！', '#yzmText',1);
		return false;
	}
	jQuery.ajax({
		type: "POST",
		url: "hfwContactsEdit.action?r=" + Math.random(),
		data: jQuery("#packetForm").serialize(),
		success: function (msg) {
			if (msg != '') {
				if (msg == '1') {
					common.alertFocus('验证码不正确！', "userCode");
				}else if (msg == '2') {
					common.alert("每个手机号只能领取一次！");
				} else if (msg == '3') {
					common.alert("领取成功！",function(){
						jQuery("#linkMan").val("请填写您的姓名");
						jQuery("#linkPhone").val("请输入您的手机号");
						jQuery("#yzmText").val("请输入验证码");
						jQuery("#close_img").click();
					},1);
				}
			}
		}
	});
}
</script>
</head>
<body >
	<s:if test="housingProj.packetList.size() >0 && !''.equals(housingProj.packetList[0].packetName) && !'敬请期待'.equals(housingProj.packetList[0].packetName)">
		<div id="editInfo" class="md-trigger" data-modal="modal-11" title="点击领取" style="right:0;position:absolute;top:200px;width: 190px;z-index: 9999;text-align:center;cursor: pointer;">
			<img src="<%=basePath%>web/images/f774ec0f316648d433da15749fd641723.png" style="width: 55%;"/>
			<div style="color: #BC2414;font-size: 14px;font-weight: bold;text-align: center;"><s:property value="housingProj.packetList[0].packetName"/></div>
		</div>
		<div class="md-modal md-effect-11" id="modal-11" style="width: 400px;">
			<img id="close_img" src="<%=basePath%>web/images/delete.png" class="md-close" />
			<div class="md-content">
				<h3>申请华房网专属优惠</h3>
				<div class="mainDiv">
					<form name="packetForm" id="packetForm">
						<input type="hidden" value="<s:property value="housingProj.packetList[0].oid"/>" name="q.packet.oid" id="packetId"/>
						<input type="text" class="rp_ipt" name="q.linkMan" id="linkMan"
							   value="请填写您的姓名" onfocus="if(this.value=='请填写您的姓名')this.value=''" onblur="if(this.value==''){this.value='请填写您的姓名'}"/>
						<input type="text" class="rp_ipt" name="q.linkPhone" id="linkPhone"
							   value="请输入您的手机号" onfocus="if(this.value=='请输入您的手机号')this.value=''" onblur="if(this.value==''){this.value='请输入您的手机号'}"/>
						<input type="text" class="rp_ipt" style="width: 200px;" name="yzm" id="yzmText"
							   value="请输入验证码" onfocus="if(this.value=='请输入验证码')this.value=''" onblur="if(this.value==''){this.value='请输入验证码'}"/>
						<img id="yzm" src="imageServlet"/>
					</form>
					<button id="saveBtn" onclick="toSave();">立即领取优惠卷</button>
					<ul>
						<li style="text-align: center;"><label><input id="tipchk" type="checkbox" checked="checked"/>&nbsp;我已阅读并同意《华房网红包服务协议》</label></li>
						<li>华房网郑重承诺：</li>
						<li>1、华房网提供的服务均免费，部分楼盘开盘活动，会在认购时用服务费抵扣房款。</li>
						<li>2、充分尊重用户意愿，提供一对一温馨服务，免受传统中介骚扰之苦。</li>
						<li>3、保证用户信息安全，多重防护措施严格保护用户信息。</li>
					</ul>
				</div>
			</div>
		</div>
	</s:if>
	<div class="wrapper" >
		<!--头部开始 -->
		<div class="header">
			<%@ include file="include/top_no_dis_b.inc"%>
			<%@ include file="include/nav_detail.inc"%>
		</div>
		<!-- header end -->

		<div id="container">
			<div style="margin: 20px auto; width: 100%;">
				<div class="lp-name-id">
					<h1><s:property value="housingProj.proj_name"/></h1>
					<dl>
						<dt class="sell"><span style="margin-left: -11px;line-height: 22px; color: #fff;"><s:property value="housingProj.sysArea.areaName"/></span></dt>
						<s:iterator value="@util.BaseParameter@rxbqDictList" >
							<s:if test="housingProj.hot_sell_label!=null&&housingProj.hot_sell_label.contains(dictId)">
								<dd class="b"><s:property value="dictName"/></dd>
							</s:if>
						</s:iterator>
					</dl>
				</div>
				<div class="clear"></div>
			</div>


			<div class="main-detail mod">
				<script language="javascript" src="web/js/jquery.js"></script>
				<div class="switch">
					<!-- 焦点图 -->
					<div class="syFocusThumb">
						<div id="syFocusThumb" class="sliderwrapper">
							<s:set name="pics_desc" value="housingProj.pics_desc!=null&&housingProj.pics_desc.split(\";\")"/>
							<s:iterator value="housingProj.pics!=null&&housingProj.pics.split(\";\")" var="pic" status="idx">
								<s:if test="#idx.index>0">
									<div class="contentdiv"
										style="display: block; overflow: hidden; z-index: 7; visibility: visible;">
										<div class="dPic">
											<a rel="colorbox<s:property value='#idx.index'/>"
												title="<s:property value='#pics_desc[#idx.index]'/>" class="cboxElement"> <img
												src="<s:property value='#pic'/>" alt="<s:property value='#pics_desc[#idx.index]'/>"
												class="intro-info-img"/></a>
										</div>
									</div>
								</s:if>
							</s:iterator>
						</div>
						<div id="paginate-syFocusThumb" class="pagination">
							<i id="prev" class="prev" title="上一幅"></i> <i id="next"
								class="next" title="下一幅"></i>
							<div class="thumbWrap">
								<div class="holder">
									<s:iterator value="housingProj.pics!=null&&housingProj.pics.split(\";\")" var="pic" status="idx">
										<s:if test="#idx.index>0">
											<i 
											<s:if test="#idx.index==1">
												class="toc selected" 
											</s:if>
											<s:else>
												class="toc" 
											</s:else>
											rel="<s:property value='#idx.index'/>" style="float: left;"><a target="_blank"><img src="<s:property value='#pic'/>"/></a><span></span><p><s:property value='#pics_desc[#idx.index]'/></p></i>
										</s:if>
									</s:iterator>
								</div>
							</div>
						</div>
					</div>
					<!-- 焦点图 end-->
					<script type="text/javascript" src="web/js/imageviewer/viewer.js"></script> 
					<script>
						jQuery(function(){
							var $images = jQuery('.sliderwrapper');
							var handler = function (e) {
						        console.log(e.type);
						      };
						    var options = {
						        // inline: true,
						        url: 'data-original',
						        build: handler,
						        built: handler,
						        show: handler,
						        shown: handler,
						        hide: handler,
						        hidden: handler
						      };
						     $images.on({
						        'build.viewer': handler,
						        'built.viewer': handler,
						        'show.viewer': handler,
						        'shown.viewer': handler,
						        'hide.viewer': handler,
						        'hidden.viewer': handler
						      }).viewer(options);
						});
					</script>
				</div>

				<script type="text/javascript" src="web/js/min_contentslider.js"></script>
				<div class="basic-details">
					<dl class="basic-parms">
						<dt>参考价格</dt>
						<dd class="price">
							<s:iterator value="housingProj.priceList" status="idx">
								<s:if test="#idx.index==0">
									<p>
										<s:property value="val_1"/><em class="sp-price"><s:property value="val_3"/></em><s:property value="val_4"/>

									</p>
								</s:if>
								<s:else>
									<p>
										<s:property value="val_1"/>&nbsp;<s:property value="val_3"/>&nbsp;<s:property value="val_4"/>
									</p>
								</s:else>
							</s:iterator>
						</dd>
						<dt>优惠折扣</dt>
						<dd><s:property value="housingProj.discount"/></dd>
						<dt>楼盘户型</dt>
						<dd class="ajust">
							<div class="house-item">
								<s:set name="len" value="housingProj.hous_type!=null&&housingProj.hous_type.split(\",\").length"/>
								<s:set name="idx" value="1"/>
								<s:iterator value="@util.BaseParameter@hxflDictList">
									<s:if test="housingProj.hous_type!=null&&housingProj.hous_type.contains(dictId)">
										<s:property value="dictName"/>
										<s:if test="#idx<#len">|</s:if>
										<s:set name="idx" value="#idx+1"/>
									</s:if>
								</s:iterator>
							</div>
						</dd>
						<dt>楼盘地址</dt>
						<dd>[ <s:property value="housingProj.sysArea.areaName"/> ] <s:property value="housingProj.address"/></dd>
					</dl>

					<div class="basic-tel" id="j-triggerlayer-b">
						<i class="lp-icons lp-icons-tel"></i>
						<div class="tel-box glance-mod">
							<p class="tel clearfix">
								<strong class="last-strong"><s:property value="housingProj.sales_line"/></strong>
							</p>
						</div>
					</div>
					<div class="public-notice">
						<em>最新政策、价格详情，敬请电话咨询售楼处</em>
					</div>

					<div class="brief-info basic-parms">
						<ul class="info-left">
							<li><label>最新开盘</label><span><s:property value="housingProj.latest_opening"/></span></li>
							<li><label>装修标准</label><span><s:property value="housingProj.deco_standard"/></span></li>
							<li><label>产权年限</label><span><s:property value="housingProj.period_right"/></span></li>
							<li><label>交房时间</label> <span><s:property value="housingProj.launch_time"/></span></li>
							<li>
								<label>建筑类型</label>
								<span>
									<s:set name="len" value="housingProj.building_type!=null&&housingProj.building_type.split(\",\").length"/>
									<s:set name="idx" value="1"/>
									<s:iterator value="@util.BaseParameter@jzlxDictList">
										<s:if test="housingProj.building_type!=null&&housingProj.building_type.contains(dictId)">
											<s:property value="dictName"/>
											<s:if test="#idx<#len">/</s:if>
											<s:set name="idx" value="#idx+1"/>
										</s:if>
									</s:iterator>
								</span>
							</li>
						</ul>
					</div>

				</div>
			</div>

			<div class="discount-service-mod ">
				<div class="main-title">
					<div class="fr">
						<span class="apply-num">参与人数：<em><s:property value="bmrs"/></em>人
						</span>
						<div class="discount-date" id="j-discount-count">
							报名倒计时： <span class="time"><span id="t_d"
								style="color: #f60;">00</span>天<span id="t_h"
								style="color: #f60;">00</span>时<span id="t_m"
								style="color: #f60;">00</span>分<span id="t_s"
								style="color: #f60;">00</span>秒</span>
							<script>
							   function GetRTime(){
							       var EndTime= new Date('<s:date name="housingProj.hfwKft.bm_end_time" format="yyyy/MM/dd HH:mm:ss"/>');
							       var NowTime = new Date();
							       var t =EndTime.getTime() - NowTime.getTime();
							       var d=Math.floor(t/1000/60/60/24);
							       var h=Math.floor(t/1000/60/60%24);
							       var m=Math.floor(t/1000/60%60);
							       var s=Math.floor(t/1000%60);
							
							       document.getElementById("t_d").innerHTML = d;
							       document.getElementById("t_h").innerHTML = h;
							       document.getElementById("t_m").innerHTML = m;
							       document.getElementById("t_s").innerHTML = s;
								   }
								   setInterval(GetRTime,0);
							</script>
						</div>
					</div>
				</div>
			</div>
			<!-- 安居优惠服务结束 -->
			<div class="activity-mod">
				<div class="act-mod mod">

					<!-- 专车看房 -->
					<!-- 线路看房团 -->
					<div class="act-item">
						<i class="lp-icons lp-icons-kan fl">看房</i>
						<div class="act-item-info fl">
							<h4 class="act-title">
								<a title="<s:property value="housingProj.proj_name"/>免费看房团" onclick="signUp();"
									><s:property value="housingProj.proj_name"/>免费看房团</a>
							</h4>
							<div class="act-date">
								<div class="act-time fl">看房时间：<s:property value="housingProj.hfwKft.kf_time"/></div>
								<div class="fl">集合地点：<s:property value="housingProj.hfwKft.jhdd"/></div>
							</div>
						</div>
						<div class="act-join fl gray">
							<i class="lp-icons lp-icons-people"></i><s:property value="bmrs"/>人已参加
						</div>
						<a href="javascript:void(0)"
							onclick="signUp();"
							class="btn btn-b fr acti-line">报名看房</a>
					</div>
					<!-- 首付贷 -->


				</div>
			</div>

			<script>
				var l_idx;
				function signUp(){
					var html = '<div style="padding:20px;">';
					html+='<table>';
					html+='<tr>';
					html+='<td style="width:40%;text-align:right;font-size:16px;line-height:50px;"><span style="color:red;">*</span>联系姓名：</td><td><input type="text" id="lxxm" name="" style="width:222px;height:42px;border:1px solid #ccc;line-height:42px;font-size:18px;"/></td>';
					html+='</tr>';
					html+='<tr>';
					html+='<td style="text-align:right;font-size:16px;line-height:50px;"><span style="color:red;">*</span>手机号码：</td><td><input type="text" id="sjhm" name="" style="width:222px;height:42px;border:1px solid #ccc;line-height:42px;font-size:18px;"/></td>';
					html+='</tr>';
					html+='<tr>';
					html+='<td></td><td style="padding-top:10px;"><input type="button" value="报名看房" style="width:130px;height:40px;background-color:#61AA00;text-align:center;border:0;font-size:18px;color:#fff;cursor:pointer;" onclick="doSignUp();"/></td>';
					html+='</tr>';
					html+='</table>';
					html+='</div>';
					l_idx = layer.open({
						  type: 1,
						  title:['报名看房', 'font-size:22px;background-color:#fff;height:66px;line-height:66px;color:#62AB00;'],
						  skin: 'layer-ext-moon' , //样式类名
						  closeBtn: 1, 
						  shift: 2,
						  area: ['490px', '300px'],
						  shadeClose: false, //开启遮罩关闭
						  content: html,
						  success: function(layero, index){
							  jQuery('#lxxm').focus();
						  }
						});
				}
				function doSignUp(){
					var $lxxm = jQuery('#lxxm');
					var $sjhm = jQuery('#sjhm');
					if(jQuery.trim($lxxm.val())==''){
						common.alertFocus('请填写联系姓名！','lxxm');
						return false;
					}
					if(jQuery.trim($sjhm.val())==''){
						common.alertFocus('请填写手机号码！','sjhm');
						return false;
					}
					common.confirm('确定信息填写正确吗？',function(){
						jQuery.ajax({
							type: "POST",
				    		url: "bookingRoomAjax.action?random=" + Math.random(),
					    	data: 'lxxm=' + $lxxm.val() + '&sjhm=' + $sjhm.val() + '&kft_id=<s:property value="housingProj.hfwKft.oid"/>&area_id=<s:property value="housingProj.area_id"/>',
						    success: function(msg){
						    	if(msg!=''&&msg==0){
						    		common.alert('您已经预约过该楼盘，无需重复预约！',function(){layer.closeAll();});
						    	}else if(msg==1){
						    		common.alert('预约成功！',function(){layer.closeAll();},1);
						    	}
						    	
							}
						});
					});
				}
			</script>

			<div class="list-mod">
				<div class="main-title clearfix">
					<h3 class="hd">猜你喜欢</h3>
				</div>
				<div class="hot-mod mod">
					<ul class="clearfix">
						<s:iterator value="hpList_1">
						<li><a href="showBuidingDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="proj_name"/>">
							<img src="<s:property value='pics!=null&&pics.split(";").length>1&&pics.split(";")[1]'/>" width="200" height="150"/></a>
							<a href="showBuidingDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="proj_name"/>" class="lpname"><s:property value="sysArea.areaName"/>-<s:property value="proj_name"/></a>
							<p class="lpprice">
								<em><s:property value="priceList[0].val_3"/><s:property value="priceList[0].val_4"/></em>
							</p></li>
						</s:iterator>
					</ul>
				</div>
			</div>



			<div class="consultant-mod">
				<div class="main-title">
					<h3 class="hd">金牌置业顾问</h3>
					<ul class="fl eval-label">
						<li><i class="lp-icons eval-icon"></i>华房网认证</li>
						<li><i class="lp-icons eval-icon"></i>值得信赖</li>
					</ul>
				</div>
				<div class="mod consult-mod">
					<div class="consult-wrap">
						<ul class="consult-list clearfix">
							<s:iterator value="clist">
								<li>
									<div class="portrait">
										<img width="60" height="60" src="./buidingmanage/images/<s:property value="pic"/>"/>
									</div>
									<div class="consult-info" consultant_id="4001" type="1">
										<div class="consult-tit">
											<h3 class="name"><s:property value="cons_name"/></h3>
											<!--住宅单页的预约置业顾问按钮-->
											<a href="javascript:void(0)"
												onclick="signUp()"
												class="consult-btn">预约</a>
										</div>
										<p class="maxim"><s:property value="motto"/></p>
									</div>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>


			<div data-marker="peripheral_support" class="life-mod">
				<div class="main-title">
					<h3 class="hd">
						周边配套<span class="sub-hd"></span>
					</h3>
				</div>
				<div class="mod life-content">
					<div class="life-bmap" id="map-box"></div>
					<div class="life-detail">
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

			<div class="list-mod">
				<div class="main-title clearfix">
					<h3 class="hd">看了该楼盘的人还看过</h3>
				</div>
				<div class="hot-mod mod">
					<ul class="clearfix">
						<s:iterator value="hpList_2">
						<li><a href="showBuidingDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="proj_name"/>">
							<img src="<s:property value='pics!=null&&pics.split(";").length>1&&pics.split(";")[1]'/>" width="200" height="150"/></a>
							<a href="showBuidingDetail.action?oid=<s:property value="oid"/>" target="_blank" title="<s:property value="proj_name"/>" class="lpname"><s:property value="sysArea.areaName"/>-<s:property value="proj_name"/></a>
							<p class="lpprice">
								<em><s:property value="priceList[0].val_3"/><s:property value="priceList[0].val_4"/></em>
							</p></li>
						</s:iterator>
					</ul>
				</div>
			</div>

			<div class="calculator-mod">
				<div class="main-title">
					<h3 class="hd">
						参考月供<span class="sub-hd"></span>
					</h3>
				</div>
				<div class="calculate">
					<script language="javascript" type="text/javascript"
						src="web/js/homt.js"></script>
					<div id="calculate01">

						<form name="calc1">

							<div class="mainl">
								<div class="h1">请您填写：</div>
								<div class="mainltr01">
									<div class="mainltr01tr">
										贷款类别：
										<label><input type="radio" value="1" name="typeRadio"
											checked="checked" onclick="exc_zuhe(this.form,this.value);"/> 商业贷款</label>
										<label><input type="radio" value="2" name="typeRadio"
											onclick="exc_zuhe(this.form,this.value);"/> 公积金贷款</label> 
										<label><input type="radio" value="3" name="typeRadio"
												onclick="exc_zuhe(this.form,this.value);"/>组合型贷款</label> 
										<input type="hidden" name="type" value="1" id="type"/>
									</div>
									<ul id="calc1_zuhe" style="display: none;">
										<li>  商业性：<input name="total_sy" maxlength="8" size="8"
											class="guestbook01" type="text"/>元</li>
										<li>  公积金：<input name="total_gjj" maxlength="8" size="8"
											class="guestbook01" type="text"/>元</li>
									</ul>
								</div>
								<div class="mainltr01" id="calc1_ctype" style="display: block;">
									<div class="h2">计算方式：</div>
									<ul>
										<li class="ew">
										<label><input id="calc1_radio1"
											onclick="exc_js(this.form,1);" checked="checked" value="1"
											name="jisuan_radio" type="radio"/> 根据面积、单价计算</label></li>
											<span id="calc1_js_div1">
											<li>    单价：<input name="price" class="guestbook01"
												type="text"/>元/平米</li>
											<li>    面积：<input name="sqm" class="guestbook01"
												type="text"/>平方米</li>
											<li>    按揭成数： <select size="1" name="anjie"
												class="teasda">
													<option value="9">9成</option>
													<option value="8">8成</option>
													<option value="7" selected="selected">7成</option>
													<option value="6">6成</option>
													<option value="5">5成</option>
													<option value="4">4成</option>
													<option value="3">3成</option>
													<option value="2">2成</option>
												</select>
											</li>
										</span>
										<li class="ew"><label><input id="calc1_radio2"
											onclick="exc_js(this.form,2);" value="2" name="jisuan_radio"
											type="radio"/> 根据贷款总额计算</label></li>
										<li id="calc1_js_div2" style="display: block;">    贷款总额：<input
											maxlength="8" size="10" name="daikuan_total000"
											class="guestbook01"/>万元</li>
									</ul>
								</div>
								<div class="mainltr01">
									按揭年数： <select id="years" size="1" name="years" class="teasd"
										onchange="ShowLilv(this.value,document.calc1.lilv.value)">
										<option value="1">1年（12期）</option>
										<option value="2">2年（24期）</option>
										<option value="3">3年（36期）</option>
										<option value="4">4年（48期）</option>
										<option value="5">5年（60期）</option>
										<option value="6">6年（72期）</option>
										<option value="7">7年（84期）</option>
										<option value="8">8年（96期）</option>
										<option value="9">9年（108期）</option>
										<option value="10">10年（120期）</option>
										<option value="11">11年（132期）</option>
										<option value="12">12年（144期）</option>
										<option value="13">13年（156期）</option>
										<option value="14">14年（168期）</option>
										<option value="15">15年（180期）</option>
										<option value="16">16年（192期）</option>
										<option value="17">17年（204期）</option>
										<option value="18">18年（216期）</option>
										<option value="19">19年（228期）</option>
										<option value="20" selected="true">20年（240期）</option>
										<option value="25">25年（300期）</option>
										<option value="30">30年（360期）</option>
									</select>
								</div>
								<div class="mainltr01">
									<div class="mainltr01tr">
										贷款利率： <select id="lilv" name="lilv" class="teasd"
											onchange="ShowLilvNew(document.calc1.years.value,this.value)">
											<option value="8">2015年10月24日利率上限（1.1倍）</option>
											<option value="7">2015年10月24日利率下限（85折）</option>
											<option value="6">2015年10月24日利率下限（7折）</option>
											<option value="5" selected="true">2015年10月24日基准利率</option>
										</select>
									</div>
									<ul style="margin-top: 10px;margin-left: 27px;">
										<li id="singlelv_li" style="display: block;">        <input
											name="text" type="text" class="red" id="singlelv"
											style="width: 50px;" value="4.90"/> %</li>
										<li id="sdlv_li" style="display: none;">        商    业： <input
											id="sdlv" type="text" class="red" style="width: 50px;"
											value="4.90"/>% </li>
										<li id="gjlv_li" style="display: none;">        公 积 金： <input
											id="gjlv" type="text" class="red" style="width: 50px;"
											value="3.25"/> %</li>
									</ul>
								</div>


								<div class="tools-btn">
									<i class="btn-bg"></i> <a
										href="javascript:ext_total(document.calc1);" class="btn btn-b">开始计算</a>
								</div>
								<div class="tools-btna">
									<i class="btn-bg"></i> <a
										href="javascript:formReset(document.calc1);"
										class="btna btn-b">重新填写</a>
								</div>
								<div class="clear"></div>
							</div>
							<div class="mainr">
								<div class="h1">查看结果：</div>
								<div class="mainrt">
									<div class="mainrtr01">
										<ul>
											<li>  房款总额：<input name="fangkuan_total1" type="text"
												class="guestbook02" readonly/>元</li>
											<li>  贷款总额：<input name="daikuan_total1" type="text"
												class="guestbook02" readonly/>元</li>
											<li>  还款总额：<input name="all_total1" type="text"
												class="guestbook02" readonly/>元</li>
											<li>支付利息款：<input name="accrual1" type="text"
												class="guestbook02" readonly/>元</li>
											<li>  首期付款：<input name="money_first1" type="text"
												class="guestbook02" readonly/>元</li>
											<li>  贷款月数：<input name="month1" type="text"
												class="guestbook02" readonly/></li>
											<li id="type1_mm1">  月均还款：<input name="month_money1"
												type="text" class="guestbook02" readonly/></li>
										</ul>
										<div id="calc1_benjin" style="display: none;">
											<input type="hidden" name="fangkuan_total2"/> <input
												type="hidden" name="daikuan_total2"/> <input
													type="hidden" name="all_total2"/> <input
														type="hidden" name="accrual2"/> <input
															type="hidden" name="money_first2"/> <input
																type="hidden" name="month2"/> <input
																	type="hidden" name="month_money2"/>
										</div>
									</div>
								</div>
								<div class="mainrtr01tr"
									style="text-align: right; padding-right: 30px;">备注：以等额本息计算结果，数据仅供参考</div>
							</div>
							<div class="clear"></div>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!--底部开始 -->
	<%@ include file="include/footer.inc" %>
	<!--底部结束 -->
	<div class="md-overlay"></div>
	<script type="text/javascript"
		src="web/js/hfangw_web_loupan_index.js"></script>
	<script type="text/javascript">
	    (function ($) {
	//     	Loupan.Map({panName: '昌吉东路于塘路交叉口南', lat: 31.299189, lng: 121.199244});
	    	Loupan.Map({panName: '<s:property value="housingProj.address"/>', lat: <s:property value='housingProj.lnglat.split(";")[1]'/>, lng: <s:property value='housingProj.lnglat.split(";")[0]'/>});
	        //send soj
	        $('#life-map-con .more a, #life-map-con .img a').on('click.sendsoj',function(){
	            XF.Soj.send("{from:loupan_index}",XF.Soj.param);
	        });
	        XF.Vars.loupan_id = '389715';
	    })(jQuery);
	</script>
		<script src="<%=basePath%>web/js/nifty/js/classie.js"></script>
		<script src="<%=basePath%>web/js/nifty/js/modalEffects.js"></script>
</body>
</html>
