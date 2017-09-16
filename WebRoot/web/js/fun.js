//选项卡切换 
    $(function(){
	    function tabS(tabTit,on,tabCon){
			//$(tabCon+">div:not(:first)").hide();
	    	if(!$(tabCon))return false;
			$(tabCon).each(function(){
				$(tabCon+">div").eq(0).show();
			});
			$(tabTit).each(function(){
				$(tabTit +">ul>li").eq(0).addClass(on);
			});
			$(tabTit +">ul>li").click(function(){
				$(this).addClass(on).siblings().removeClass(on);
				var index = $(tabTit +">ul>li").index(this);
				$(tabCon+">div").eq(index).show().siblings().hide();
			});
		}
		tabS(".news-tabs","current",".news-cont");
		tabS(".sort-tab","current",".key-list");
});
