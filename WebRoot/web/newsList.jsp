<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<%@ include file="include/heard.inc"%>
<script type="text/javascript" src="js/layer/laypage-v1.3/laypage.js"></script>
<script>
	 jQuery(function(){
		 setTimeout(function(){
			  var t;
			  if (parent.document.documentElement && parent.document.documentElement.scrollTop) {
				  t = parent.document.documentElement.scrollTop;
			  }else if (parent.document.body) {
				  t = parent.document.body.scrollTop;
			  }
			  if( t > 200){
				  parent.$("html,body").animate({scrollTop:190},500);
			  }
		 },800);
	 });
</script>
</head>
<body>
	<div class="news-cont">
		<div class="news-list">
			<ul>
				<s:iterator value="rp.resultList">
					<li class="clearfix"><img src="<s:property value='fileAtta.attaPath'/>"
						width="180" height="120"/>
						<div class="news-info">
							<a href="newsDetail.action?contentId=<s:property value='contentId'/>" class="news-tit" target="_blank"><s:property value='contentMainTitle'/></a>
							<p class="news-txt"><s:property value='contentId_' escape="false"/></p>
							<div class="news-time">
<!-- 								<a href="">楼市热点</a> | <a href="">安徽商报</a> | <span>16-03-09</span> -->
							</div>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="list-page" id="page" style="text-align: center;min-height: 70px;">
	</div>
	<script>
		laypage({
			cont: $('#page'),
			pages: <s:property value="rp.maxPage"/>, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
		    skip: true, //是否开启跳页
		    skin: '#C0392B',
		    groups: 3, //连续显示分页数
		    curr: function(){ //通过url获取当前页，也可以同上（pages）方式获取
			var page = location.search.match(/page=(\d+)/);
			return page ? page[1] : 1;
		    }(), 
		    jump: function(e, first){ //触发分页后的回调
			if(!first){ //一定要加此判断，否则初始时会无限刷新
			    var m = location.href.indexOf('page=');
			    if(m < 0){
					location.href = location.href + '&page=' + e.curr ;
			    }else{
					location.href = location.href.substring(0, m) + '&page=' + e.curr ;
			    }  
			}
		    }
		});


	</script>
</body>
</html>
