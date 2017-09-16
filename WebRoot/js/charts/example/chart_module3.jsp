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
		/*投诉折线图*/
$(function () {
    var chart2;
    $(document).ready(function() {
        chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'line'
            },
            title: {
                text: '每月投诉分布'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            tooltip: {
                enabled: false,
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'';
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                name: '建设工程',
                data: [16, 16, 17, 17, 15, 14, 13, 12, 11, 10, 9, 8]
            }, {
                name: '政府采购',
                data: [13, 12, 14, 14, 13, 12, 11, 10, 19, 28, 27, 26]
            }, {
                name: '医药采购',
                data: [12, 12, 17, 25, 11, 15, 14, 16, 14, 10, 16, 28]
            }, {
                name: '产权交易',
                data: [29, 32, 15, 25, 19, 16, 28, 10, 28, 16, 13, 16]
            }, {
                name: '土地交易',
                data: [16, 22, 12, 28, 18, 12, 12, 9, 7, 15, 13, 14]
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
