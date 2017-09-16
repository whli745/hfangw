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
		/*业务量柱形图*/
$(function () {
    var chart;
    $(document).ready(function() {
    
        var colors = Highcharts.getOptions().colors,
            categories = ['建设工程', '政府采购', '医药采购', '产权交易', '土地交易'],
            name = '业务量（单位：件）',
            data = [{
                    y: 8134,
                    color: colors[0],
                    title: categories[0]
                }, {
                    y: 13216,
                    color: colors[1],
                    title: categories[1]
                }, {
                    y: 11100,
                    color: colors[2],
                    title: categories[2]
                }, {
                    y: 17715,
                    color: colors[3],
                    title: categories[3]
                }, {
                    y: 2614,
                    color: colors[4],
                    title: categories[4]
                }];
    
        function setChart(name, categories, data, color) {
			chart.xAxis[0].setCategories(categories, false);
			chart.series[0].remove(false);
			chart.addSeries({
				name: name,
				data: data,
				color: color || 'white'
			}, false);
			chart.redraw();
        }
    
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container3',
                type: 'column'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            plotOptions: {
                column: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function(event) {
                                alert(event.point.title);
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                } else { // restore
                                    setChart(name, categories, data);
                                }
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true,
                        color: colors[0],
                        style: {
                            fontWeight: 'bold'
                        },
                        formatter: function() {
                            return this.y +'';
                        }
                    }
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                        s = this.x +':<b>'+ this.y +'</b><br/>';
                    return s;
                }
            },
            series: [{
                name: name,
                data: data,
                color: 'white'
            }],
            exporting: {
                enabled: false
            }
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
				  <div id="container3" style="min-width: 300px; height: 300px; margin: 0 auto"></div>
			</dd>
		</dl>
	</div>
</body>
<script type="text/javascript" src="./js/chart/modules/exporting.js"></script>
</html>
