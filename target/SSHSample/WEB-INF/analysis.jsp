<%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="header.jsp"%>

<div id="setting" class="reveal-modal panel panel-default">
    <div>
        <h4 id="book-price">Setting</h4>
    </div>
    <form id="take" method="GET" action="/addcart">
        <div class="panel-body">
            <label>Start Data</label>
            <br>
            <div class="col-md-4">
                <input id="start-year" type="number" class="form-control" placeholder="Year">
            </div>
            <div class="col-md-4">
                <input id="start-month" type="number" class="form-control" placeholder="Month">
            </div>
            <div class="col-md-4">
                <input id="start-day" type="number" class="form-control" placeholder="Day">
            </div>
        </div>

        <div class="panel-body">
            <label>End Data</label>
            <br>
            <div class="col-md-4">
                <input id="end-year" type="number" class="form-control" placeholder="Year">
            </div>
            <div class="col-md-4">
                <input id="end-month" type="number" class="form-control" placeholder="Month">
            </div>
            <div class="col-md-4">
                <input id="end-day" type="number" class="form-control" placeholder="Day">
            </div>
        </div>

        <div class="panel-body">
            <label>User ID</label>
            <br/>
            <div class="col-md-12">
                <input id="user-id" type="number" class="form-control" placeholder="User ID"/>
            </div>
        </div>

        <div class="panel-body">
            <div class="text-center">
                <input type="hidden" name="bookID" id="book-id">
                <input class="btn btn-primary" type="button" onclick="take()" value="Get Analysis Report"/>
            </div>
        </div>
    </form>
    <a class="close-reveal-modal">&#215;</a>
</div>

<script>
    function show_setting() {
        $('#setting').reveal({
            animation: 'fadeAndPop',                   //fade, fadeAndPop, none
            animationspeed: 300,                       //how fast animtions are
            closeonbackgroundclick: true,              //if you click background will modal close?
            dismissmodalclass: 'close-reveal-modal'    //the class of a button or element that will close an open modal
        });
    }
    function take() {
        var $startYear=$('#start-year').val();
        var $startMonth=$('#start-month').val();
        var $startDay=$('#start-day').val();

        var $endYear=$('#end-year').val();
        var $endMonth=$('#end-month').val();
        var $endDay=$('#end-day').val();

        var $userID=$('#user-id').val();

        var $startDate="";
        if ($startDay.length==2 && $startMonth.length==2 && $startYear.length==4) {
            $startDate += $startYear;
            $startDate += "-";
            if ($startMonth.length == 1) $startDate = $startDate + '0' + $startMonth;
            else $startDate += $startMonth;
            $startDate += "-";
            if ($startDay.length == 1) $startDate = $startDate + '0' + $startDay;
            else $startDate += $startDay;
        }

        var $endDate="";
        if ($endDay.length==2 && $endMonth.length==2 && $endYear.length==4) {
            $endDate += $endYear;
            $endDate += "-";
            if ($endMonth.length == 1) $endDate = $endDate + '0' + $endMonth;
            else $endDate += $endMonth;
            $endDate += "-";
            if ($endDay.length == 1) $endDate = $endDate + '0' + $endDay;
            else $endDate += $endDay;
        }

        var $url="/analysis?userID="+$userID+"&start="+$startDate+"&end="+$endDate;
        window.location.href=$url;
    }
</script>

<div class="container">
    <div class="row">
        <div class="col-md-offset-1 col-md-10">
            <div class="pull-right">
                <button class="btn btn-primary" onclick="show_setting()">Setting</button>
            </div>
        </div>
    </div>
</div>
<br>

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


<script src="/statics/js/highcharts.js"></script>

<s:if test="ifRange==false">
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
</s:if>
<s:else>
    <script>
        $(function () {
            $('#graph-day').highcharts({
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'In Range'
                },
                xAxis: {
                    categories:[ <s:iterator value="day" id="d">'<s:property value="key"/>',</s:iterator> ]
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
                    text: 'By Month'
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
            $('#graph-year').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'By Year'
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
</s:else>


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

<jsp:include page="footer.jsp" />