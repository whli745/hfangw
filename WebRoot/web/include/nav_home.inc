<%@ page contentType="text/html;charset=UTF-8"%>
<div class="nav">
	<ul class="w1190 auto clearfix">
		<li><a class="current" href="?districtId=<s:property value="districtId"/>">网站首页</a></li>
		<li><a href="buidingSearch.action?type=1&districtId=<s:property value="districtId"/>">最新楼盘</a></li>
		<li><a href="buidingSearch.action?type=2&districtId=<s:property value="districtId"/>">团购楼盘</a></li>
		<li><a href="houseSearch.action?districtId=<s:property value="districtId"/>">二手房</a></li>
		<li><a href="newsFrames.action?districtId=<s:property value="districtId"/>">最新资讯</a></li>
	</ul>
</div>