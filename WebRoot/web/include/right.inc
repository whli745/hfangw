<%@ page contentType="text/html;charset=UTF-8"%>
<div class="list-advs">
  <div class="build-mod latest-opening s-mod">
    <h3>
    	  最新开盘
    </h3>
    <dl class="list-box" style="min-height: 328px;">
      <dt class="list-date" style="padding:2px;"></dt>
      <s:iterator value="hpList_1">
      <dd class="list-item">
        <span class="item-name">
          <a href="showBuidingDetail.action?oid=<s:property value="oid"/>" target="_blank">
          	<s:property value="proj_name"/>
          </a>
        </span>
        <span class="item-price">
          <em>
            	<s:property value="unit_price"/>
          </em>
        </span>
      </dd>
      </s:iterator>
    </dl>
  </div>
  <div class="ifxside-recomm" style="min-height: 637px;">
     <h3 class="hd">推荐楼盘</h3>
     <ul class="etlist">
      <s:iterator value="hpList_2" status="idx">
     	<li onclick="window.open('showBuidingDetail.action?oid=<s:property value="oid"/>')">
        	<img src="<s:property value="pics!=null&&pics.split(\";\").length>1&&pics.split(\";\")[1]"/>" style="width: 210px;height: 140px;"/>
            <h4 class="tit" style="overflow: hidden;vertical-align: bottom;white-space: nowrap;text-overflow: ellipsis;width: 220px;"><s:property value="proj_name"/></h4>
            <p class="price"><s:property value="priceList[0].val_2"/><em class="ifx-num"><s:property value="priceList[0].val_3"/></em><s:property value="priceList[0].val_4"/></p>		
        </li>
        <s:if test="#idx.index<hpList_1.size-1"><li class="line"></li></s:if>
       </s:iterator>
     </ul>
     </div>
</div>
