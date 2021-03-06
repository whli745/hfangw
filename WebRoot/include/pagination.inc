<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<s:if test="allRows > 0">
	<p class="pageLeft">
		共
		<s:property value="allRows" />
		条记录，每页
		<s:property value="thisPageRows" />
		条，共
		<s:property value="maxPage" />
		页
	</p>
	<p class="pageRight">
		<s:if test="page<=1">首页<img src='./images/btn_pre.gif'
				border='0' />
		</s:if>
		<s:else>
			<a href="javascript:void(0)"
			onclick="javascript:skipToPage(1, '<s:property value="maxPage" />', '<s:property value="getPages()[0].URL" />')">首页</a>
			<a href="javascript:void(0)"
			onclick="javascript:skipToPage('<s:property value="page-1"/>', '<s:property value="maxPage" />', '<s:property value="getPages()[0].URL" />')"><img
					src='./images/btn_pre.gif' border='0' /></a>
		</s:else>
		<s:property value="page" />
		/
		<s:property value="maxPage" />
		页
		<s:if test="page>=getMaxPage()">
			<img src='./images/btn_next.gif' border='0' />尾页</s:if>
		<s:else>
			<a href="javascript:void(0)"
			onclick="javascript:skipToPage('<s:property value="page + 1"/>', '<s:property value="maxPage" />', '<s:property value="getPages()[0].URL" />')"><img
					src='./images/btn_next.gif' border='0' /></a>
			<a href="javascript:void(0)"
			onclick="javascript:skipToPage('<s:property value="maxPage"/>', '<s:property value="maxPage" />','<s:property value="getPages()[0].URL" />')">尾页</a>
		</s:else>
		转到第
		<input type="text" id="page" name="page" class="iptJump"
			value="<s:property value='page'/>" />
		页
		<a href="javascript:void(0)"
			onclick="skipToPage(document.getElementById('page').value, '<s:property value="maxPage" />', '<s:property value="getPages()[0].URL" />')"><img
				src="./images/btn_goto.gif" border="0" />转</a>
	</p>
</s:if>
