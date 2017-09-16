<s:if test="buttonModuleList.size()>0">
<script type="text/javascript">
	var buttons;
	$(document).ready(function(){
		<s:iterator value='buttonModuleList' var="buttonModule">
			<s:set var ="moduleControl" value="'moduleControl'"></s:set>  
			<s:set var ="flag" value="'flag'"></s:set>  
			<s:if test='#buttonModule[#flag].equals("0")'>
				buttons = document.getElementsByTagName('span');
				for(var i = 0; i < buttons.length; i++) {
					if(buttons[i].getAttribute("name")=='<s:property value="#buttonModule[#moduleControl]"/>') {
						buttons[i].parentNode.removeChild(buttons[i]); //.style.display = "none";
					}
				}
			</s:if>
		</s:iterator>
	})
</script>
</s:if>

