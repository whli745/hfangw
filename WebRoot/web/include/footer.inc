<%@ page contentType="text/html;charset=UTF-8"%>
<div class="foot">
  	<div class="copyright">
		<s:iterator value="infoTcategoryList_2" status="idx">
  		<a href="newsAboutUsFrames.action?columnId=<s:property value='columnId'/>" target="_blank"><s:property value="columnName" /></a> 
		<s:if test="#idx.index<infoTcategoryList_2.size-1">|</s:if>
		</s:iterator>
  		<p>Copyrihgt © 2015-2016 All Rights Reserved 华房网 版权所有</p>
  	</div>
</div>
