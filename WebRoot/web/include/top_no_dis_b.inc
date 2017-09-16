<%@ page contentType="text/html;charset=UTF-8"%>
<div class="hrader-top">
	<div class="w1190 auto">您好，欢迎浏览华房网！</div>
</div>	
<div class="header-cont w1190 auto" style="width:1080px;">
	<div class="logo left">
		<img src="web/images/logo.png" width="194" height="75" alt="华房网"/>
	</div>
	<div class="citys left">
	</div>
	<form id="sch_form" action="buidingSearch.action" method="get" target="_blank">
	<div class="searchbox right">
		<div class="search clearfix">
        	<select class="left sel">
              <option>楼盘</option>
              <option>商圈</option>
              <option>门面</option>
            </select>
			<input id="i_sch" type="text" class="left" name="q.proj_name" value="请输入关键字..." onfocus="if(this.value=='请输入关键字...'){this.value='';}"  onblur="if(this.value==''){this.value='请输入关键字...';}"/>
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

