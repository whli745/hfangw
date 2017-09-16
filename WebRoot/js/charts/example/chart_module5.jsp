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
		/*收费饼状图*/
$(function () {
    var chart5;
    
    $(document).ready(function () {
    	
    	// Build the chart
        chart5 = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '收费：单位（万元）'
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.point.name +'</b>: '+ this.y +'';
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: [
                    ['建设工程',   443],
                    ['政府采购',       226],
                    ['医药采购',    128],
                    ['产权交易',     216],
                    ['土地交易',   627]
                ]
            }]
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
				  <div id="container" style="min-width: 300px; height: 300px; margin: 0 auto"></div>
			</dd>
		</dl>
	</div>
</body>
<script type="text/javascript" src="./js/chart/modules/exporting.js"></script>
</html>
