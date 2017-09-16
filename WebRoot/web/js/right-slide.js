$(function(){
	
	var tophtml="<div id=\"izl_rmenu\" class=\"izl-rmenu\" ><div class=\"btn btn-wx\"><img class=\"pic\" src=\"web/images/weixin.jpg\"/></div><div class=\"btn btn-hb\"><img class=\"pic\" src=\"web/images/hongbao.jpg\" onclick=\"window.location.href=\'\'\"/></div><div class=\"btn btn-top\"></div></div>";
//	$("#top").html(tophtml);
	$("#izl_rmenu").each(function(){
		$(this).find(".btn-wx,.btn-hb").mouseenter(function(){
			$(this).find(".pic").fadeIn("fast");
		});
		$(this).find(".btn-wx,.btn-hb").mouseleave(function(){
			$(this).find(".pic").fadeOut("fast");
		});
		
		$(this).find(".btn-top").click(function(){
			$("html, body").animate({
				"scroll-top":0
			},"fast");
		});
	});
	var lastRmenuStatus=false;
	$(window).scroll(function(){//bug
		var _top=$(window).scrollTop();
		if(_top>200){
			$("#izl_rmenu").data("expanded",true);
		}else{
			$("#izl_rmenu").data("expanded",false);
		}
		if($("#izl_rmenu").data("expanded")!=lastRmenuStatus){
			lastRmenuStatus=$("#izl_rmenu").data("expanded");
			if(lastRmenuStatus){
				$("#izl_rmenu .btn-top").slideDown();
			}else{
				$("#izl_rmenu .btn-top").slideUp();
			}
		}
	});
});