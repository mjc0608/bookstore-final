<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" id="graph-category"></div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" id="graph-day"></div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" id="graph-month"></div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" id="graph-year"></div>
    </div>
</div>


<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script>
    $(function () {
        $('#graph-day').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: 'Last Month'
            },
            xAxis: {
                categories:[ <s:iterator value="day" id="d">'<s:property value="key.substring(5,10)"/>',</s:iterator> ]
            },
            yAxis: [
                {
                    title: {
                        text: 'Income($)'
                    }
                },
                {
                    title: {
                        text: "Sales"
                    },
                    opposite: true
                }],
            tooltip: {
                enabled: true,
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
                name: 'Income',
                data: [ <s:iterator value="day" id="d"><s:property value="value.money"/>,</s:iterator> ],
                yAxis:0
            }, {
                name: 'Sales',
                data: [ <s:iterator value="day" id="d"><s:property value="value.quantity"/>,</s:iterator> ],
                yAxis:1
            }],
            credits: {
                enabled:false
            },
            exporting: {
                enabled:false
            }
        });
    });
</script>
<script>
    $(function () {
        $('#graph-month').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: 'Last Year'
            },
            xAxis: {
                categories:[ <s:iterator value="month" id="d">'<s:property value="key"/>',</s:iterator> ]
            },
            yAxis: [
                {
                    title: {
                        text: 'Income($)'
                    }
                },
                {
                    title: {
                        text: "Sales"
                    },
                    opposite: true
                }],
            tooltip: {
                enabled: true,
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
                name: 'Income',
                data: [ <s:iterator value="month" id="d"><s:property value="value.money"/>,</s:iterator> ],
                yAxis:0
            }, {
                name: 'Sales',
                data: [ <s:iterator value="month" id="d"><s:property value="value.quantity"/>,</s:iterator> ],
                yAxis:1
            }],
            credits: {
                enabled:false
            },
            exporting: {
                enabled:false
            }
        });
    });
</script>




<script>
    $(function () {
        $('#graph-category').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Book Category'
            },
            xAxis: {
                categories:[ <s:iterator value="category" id="d">'<s:property value="key"/>',</s:iterator> ]
            },
            yAxis: [
                {
                    title: {
                        text: 'Income($)'
                    }
                },
                {
                    title: {
                        text: "Sales"
                    },
                    opposite: true
                }],
            tooltip: {
                enabled: true,
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
                name: 'Income',
                data: [ <s:iterator value="category" id="d"><s:property value="value.money"/>,</s:iterator> ],
                yAxis:0
            }, {
                name: 'Sales',
                data: [ <s:iterator value="category" id="d"><s:property value="value.quantity"/>,</s:iterator> ],
                yAxis:1
            }],
            credits: {
                enabled:false
            },
            exporting: {
                enabled:false
            }
        });
    });
</script>

<script>
    $(function () {
        $('#graph-year').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Over the Few Years'
            },
            xAxis: {
                categories:[ <s:iterator value="year" id="d">'<s:property value="key"/>',</s:iterator> ]
            },
            yAxis: [
                {
                    title: {
                        text: 'Income($)'
                    }
                },
                {
                    title: {
                        text: "Sales"
                    },
                    opposite: true
                }],
            tooltip: {
                enabled: true,
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
                name: 'Income',
                data: [ <s:iterator value="year" id="d"><s:property value="value.money"/>,</s:iterator> ],
                yAxis:0
            }, {
                name: 'Sales',
                data: [ <s:iterator value="year" id="d"><s:property value="value.quantity"/>,</s:iterator> ],
                yAxis:1
            }],
            credits: {
                enabled:false
            },
            exporting: {
                enabled:false
            }
        });
    });
</script>

<jsp:include page="footer.jsp" />