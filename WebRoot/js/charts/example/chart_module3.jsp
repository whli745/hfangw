<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="../../../include/heard.inc"%>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
<title>ͳһ��������ƽ̨</title>
<script type="text/javascript" src="./js/charts/jquery-1.8.1.min.js"></script>
<script src="./js/charts/highcharts.js"></script>
<script src="./js/charts/modules/exporting.js"></script>
<script type="text/javascript">
		/*Ͷ������ͼ*/
$(function () {
    var chart2;
    $(document).ready(function() {
        chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'line'
            },
            title: {
                text: 'ÿ��Ͷ�߷ֲ�'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: ['1��', '2��', '3��', '4��', '5��', '6��', '7��', '8��', '9��', '10��', '11��', '12��']
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
                name: '���蹤��',
                data: [16, 16, 17, 17, 15, 14, 13, 12, 11, 10, 9, 8]
            }, {
                name: '�����ɹ�',
                data: [13, 12, 14, 14, 13, 12, 11, 10, 19, 28, 27, 26]
            }, {
                name: 'ҽҩ�ɹ�',
                data: [12, 12, 17, 25, 11, 15, 14, 16, 14, 10, 16, 28]
            }, {
                name: '��Ȩ����',
                data: [29, 32, 15, 25, 19, 16, 28, 10, 28, 16, 13, 16]
            }, {
                name: '���ؽ���',
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
