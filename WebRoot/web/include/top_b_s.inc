<%@ page contentType="text/html;charset=UTF-8"%>
<script>
	$(function(){
		//城市展开收起
		$(".citys span").click(function(){
			var ul = $(".citys1");
			if(ul.css("display")=="none"){
				ul.slideDown("fast");
			}else{
				ul.slideUp("fast");
			}
		});
	})
</script>
<div class="hrader-top">
	<div class="w1190 auto">您好，欢迎浏览华房网！</div>
</div>	
<div class="header-cont w1190 auto">
	<div class="logo left">
		<img src="web/images/logo.png" width="194" height="75" alt="华房网"/>
	</div>
	<div class="citys left">
		<span><s:property value="districtName"/></span>
		<div class="citys1">
			<ul>
				<s:iterator value="@util.Common@getAreaSiteList()">
				<li><a id="<s:property value="areaId"/>" href="javascript:void(0);"><s:property value="areaName"/></a></li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<script>
		jQuery(function (){
			jQuery('.citys1 > ul > li > a').click(function(){
				var new_href = 'buidingSearch.action?type=<s:property value="type"/>&districtId=' + jQuery(this).attr('id');
				location.href = new_href;
			});
		});
	</script>
	<form id="sch_form" action="buidingSearch.action" method="get">
	<input type="hidden" name="type" value="<s:property value="type"/>"/>
	<input type="hidden" name="districtId" value="<s:property value="districtId"/>"/>
	<div class="searchbox right">
		<div class="search clearfix">
        	<select class="left sel">
              <option>楼盘</option>
              <option>商圈</option>
              <option>门面</option>
            </select>
			<input id="i_sch" type="text" class="left" name="q.proj_name" value="<s:property value='q.proj_name==null?"请输入关键字...":q.proj_name'/>" onfocus="if(this.value=='请输入关键字...'){this.value='';}"  onblur="if(this.value==''){this.value='请输入关键字...';}"/>
			<input type="button" value="开始搜索" class="left" onclick="doSch();"/>
		</div>
		<div class="hot-search">
			<p>热门楼盘:  <s:property value='@util.Common@getSearchKeywordsHtml()' escape="false"/></p>
		</div>
	</div>
	</form>
	<script>
		function doSch(){
			if(jQuery('#i_sch').val()=='请输入关键字...'){
				jQuery('#i_sch').val('');
			}
			jQuery('#sch_form').submit();
		}
		function doSch_(o){
			jQuery('#i_sch').val(jQuery(o).text());
			doSch();
		}
	</script>
</div>

