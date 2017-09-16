<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="../../../include/heard.inc"%>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>统一电子政务平台</title>
<script type="text/javascript" src="./js/charts/jquery-1.8.1.min.js"></script>
<script src="./js/charts/highcharts.js"></script>
<script src="./js/charts/modules/exporting.js"></script>
<script type="text/javascript">
	/*预警纠错横向柱状图*/
	$(function() {
		var chart;
		$(document).ready(
				function() {
					chart = new Highcharts.Chart({
						chart : {
							renderTo : 'container',
							type : 'bar'
						},
						title : {
							text : ' '
						},
						xAxis : {
							categories : [ '建设工程', '政府采购', '医药采购', '产权交易',
									'土地交易' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : ''
							}
						},
						legend : {
							backgroundColor : '#FFFFFF',
							reversed : true
						},
						tooltip : {
							formatter : function() {
								return '' + this.series.name + ': ' + this.y
										+ '';
							}
						},
						plotOptions : {
							series : {
								stacking : 'normal',
								events : {
									click : function(event) {
										alert(event.point.series.name + event.point.series.data);
										var drilldown = this.drilldown;
										if (drilldown) { // drill down
											setChart(drilldown.name,
													drilldown.categories,
													drilldown.data,
													drilldown.color);
										} else { // restore
											setChart(name, categories, data);
										}
									}
								}
							}
						},
						series : [ {
							name : '红牌',
							data : [ 63, 42, 30, 34, 43 ]
						}, {
							name : '黄牌',
							data : [ 92, 62, 53, 72, 81 ]
						}, {
							name : '预警',
							data : [ 113, 104, 94, 102, 125 ]
						} ]
					});
				});

	});
</script>


<style>
body {
	background: none
}
</style>
</head>
<body>
	<div class="mainDiv">
		<dl class="mtab" style="width: 100%; margin-top: 12px;">
			<dd id="t1">
				<div id="container"
					style="min-width: 300px; height: 300px; margin: 0 auto"></div>
			</dd>
		</dl>
	</div>
</body>
<script type="text/javascript" src="./js/chart/modules/exporting.js"></script>
</html>
