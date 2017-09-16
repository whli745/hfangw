<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="../../../include/heard.inc"%>
	<script type="text/javascript" src="./ueditor/editor_all_min.js"></script>
	<script type="text/javascript" src="js/zDialog/zDialog.js"></script>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery("#listTab1 tr:even").addClass("alt");
		jQuery("#listTab2 tr:even").addClass("alt");
		jQuery(".listTab tr:not(first,second)").hover(function(){jQuery(this).addClass("over")},function(){jQuery(this).removeClass("over")});
	})
	
	function setTab(name,cursel,n,srcValue){
		document.getElementById('ifc').src = srcValue;
		for(i=1;i<=n;i++){
		  var menu=document.getElementById(name+i);
		  menu.className=i==cursel?"hover":"";
		}
	}
	</script>
</head>
<body id="mouseRight">
	<div class="mainDiv">
  	<dl class="mtab">
    	<dt>
         <p class="position">当前位置：<a>系统管理</a><a>基础管理</a><a >日志管理</a></p>
        </dt>
        <dd>
			<div class="Title1">
				<ul class="tabZb">
					<li id="zb2" onmousedown="setTab('zb',2,2, 'queryLogInfoList.action')" class="hover"><a href="#">系统日志</a></li>
					<li id="zb1" onmousedown="setTab('zb',1,2, 'queryLogActionDescList.action')"><a href="#">系统功能描述</a></li>
				</ul>
			</div>
			<div class="Project" id="con_zb_1">
				<iframe src="queryLogInfoList.action" width="100%" style="min-height: 600px;" scrolling="auto" id="ifc" frameborder="0"> </iframe>
			</div>
        </dd>
    </dl>
    </div>
</body>
</html>