<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>OMS</title>
    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <!-- Place favicon.ico in the root directory -->
    <!-- build:css styles/vendor.css -->
    <!-- bower:css -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
    <!-- endbower -->
    <!-- endbuild -->
    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="/main/webapp/styles/reset.css">
    <link rel="stylesheet" href="/main/webapp/styles/layout.css">
    <link rel="stylesheet" href="/main/webapp/styles/main.css">
    <link rel="stylesheet" href="/main/webapp/styles/flightDynamics.css">
    <!-- endbuild -->
</head>

<body ng-app="OMS">
    <!--[if IE]>
      <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <div id="Wrapper" ng-controller="fileDynamicCtrl">
        <header id="Header">
            <div class="logo">
                <a href="#">OMS</a>
            </div>
            <div class="nav">
                <ul>
                    <li><a id="FlightDynamics" href="flightDynamics.html" class="active">航班动态</a></li>
                    <li><a id="SpeedExplain" href="index.html" >机坪动态</a></li>
                    <li><a id="Work" href="#!/b">工作统计</a></li>
                    <li><a id="FlightDynamics" href="flightDynamics.html">菜单</a></li>
                    <li><a id="FlightDynamics" href="flightDynamics.html">菜单</a></li>
                </ul>
            </div>
            <div class="clock">
                <div class="time">
                    00:00
                </div>
                <div class="day">
                    2016-6-14 星期一
                </div>
            </div>
            <div class="opt">
                <div class="search-bar">
                    <div class="input-group">
                        <select name="" class="form-control">
                            <option value="">请选择</option>
                        </select>
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-calendar"></i>
                        </span>
                    </div>
                </div>
                <div class="select-bar">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入关键字">
                        <span class="input-group-addon btn btn-primary">搜索</span>
                    </div>
                </div>
            </div>
        </header>
        <div id="Content" get-dynamic>
            <div id="FlightContent">
                <table class="table">
                    <thead>
                        <tr class="classify">
                            <th colspan="7">进港</th>
                            <th colspan="4">机型机位</th>
                            <th colspan="7">出港</th>
                        </tr>
                        <tr class="thead">
                            <th>
                                <div style="margin-top:-2rem;">航班号</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">始发站</div>
                            </th>
                            <th>
                                <div>计划</div>
                                <div>落地</div>
                            </th>
                            <th>
                                <div>动态</div>
                                <div>时间</div>
                            </th>
                            <th>
                                <div>进港</div>
                                <div>状态</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">保障人</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">备注</div>
                            </th>
                            <th>
                                <div>进港</div>
                                <div>机位</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">飞机号</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">机型</div>
                            </th>
                            <th>
                                <div>出港</div>
                                <div>机位</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">航班号</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">目的站</div>
                            </th>
                            <th>
                                <div>计划</div>
                                <div>起飞</div>
                            </th>
                            <th>
                                <div>动态</div>
                                <div>时间</div>
                            </th>
                            <th>
                                <div>出港</div>
                                <div>状态</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">保障人</div>
                            </th>
                            <th>
                                <div style="margin-top:-2rem;">备注</div>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="x in dynamicList">
                            <td class="arrival-flight">
                                <span class="label">
                                    {{x.arrival.Fno}}
                                </span>
                            </td>
                            <td class="dep">{{x.arrival.Fdep}}</td>
                            <td class="arrival-plan-ground">
                                <span class="label label-yellow">
                                    {{x.arrival.DestTime}}
                                </span>
                            </td>
                            <td class="arrival-dynamic-time">
                                <span class="label label-yellow">
                                    {{x.arrival.RealDepTime}} <!-- &nbsp;&nbsp;<span class="label label-default">实 --></span>
                                </span>
                            </td>
                            <td class="arrival-status">
                                <span class="label label-red">
                                    {{x.arrival.Attribus}} 
                                </span>
                            </td>
                            <td class="arrival-group-people"><!-- 诸葛明月 --></td>
                            <td class="arrival-remark"><!-- 进港 --></td>
                            <td class="arrival-seat">{{x.arrival.Location}}</td>
                            <td class="flight-no">{{x.arrival.Acno}}</td>
                            <td class="flight-model">{{x.arrival.Type}}</td>
                            <td class="departure-seat">{{x.departure.Location}}</td>
                            <td class="departure-flight">
                                <span class="label">
                                    <!-- <i class="glyphicon glyphicon-heart fav"></i> --> {{x.departure.Fno}}
                                </span>
                            </td>
                            <td class="dest">{{x.departure.Fdep}}</td>
                            <td class="departure-plan-ground">
                                <span class="label label-yellow">
                                    {{x.departure.DestTime}}
                                </span>
                            </td>
                            <td class="departure-dynamic-time">
                                <span class="label label-yellow">
                                    {{x.departure.RealDepTime}} <!-- &nbsp;&nbsp;<span class="label label-success">预</span> -->
                                </span>
                            </td>
                            <td class="departure-status">
                                <span class="label">
                                    {{x.departure.Attribus}} 
                                </span>
                            </td>
                            <td class="departure-group-people"><!-- 孙行者 --></td>
                            <td class="departure-remark"><!-- 123 --></td>
                        </tr>
                        <!-- <tr>
                            <td class="arrival-flight">
                                <span class="label">
                                    <i class="glyphicon glyphicon-heart fav"></i> CA2158
                                </span>
                            </td>
                            <td class="dep">大连</td>
                            <td class="arrival-plan-ground">
                                <span class="label">
                                    07:40
                                </span>
                            </td>
                            <td class="arrival-dynamic-time">
                                <span class="label label-yellow">
                                    07:45 &nbsp;&nbsp;<span class="label label-primary">计</span>
                                </span>
                            </td>
                            <td class="arrival-status">
                                <span class="label">
                                    到达
                                </span>
                            </td>
                            <td class="arrival-group-people">湘西老道</td>
                            <td class="arrival-remark">进港</td>
                            <td class="arrival-seat">106</td>
                            <td class="flight-no">B5539</td>
                            <td class="flight-model">A322</td>
                            <td class="departure-seat">115</td>
                            <td class="departure-flight">
                                <span class="label label-success">
                                    <i class="glyphicon glyphicon-heart fav"></i> CA2159
                                </span>
                            </td>
                            <td class="dest">哈尔冰</td>
                            <td class="departure-plan-ground">
                                <span class="label label-yellow">
                                    07:40
                                </span>
                            </td>
                            <td class="departure-dynamic-time">
                                <span class="label">
                                    07:45 &nbsp;&nbsp;<span class="label label-default">实</span>
                                </span>
                            </td>
                            <td class="departure-status">
                                <span class="label">
                                    
                                </span>
                            </td>
                            <td class="departure-group-people">西门吹雪</td>
                            <td class="departure-remark">123</td>
                        </tr> -->
                    </tbody>
                </table>
            </div>
        </div>
        <footer id="Footer">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-active">全部航班</button>
                <button type="button" class="btn">已关注航班</button>
            </div>
            <div class="mequre">
                <marquee id="Affiche" align="left" behavior="scroll" loop="-1" scrollamount="10" scrolldelay="100" onMouseOut="this.start()" onMouseOver="this.stop()">
                    <span class="label label-red">CA1234 机位从A110变更至806</span>
                    <span class="label label-green">CZ1234 已经落地 实落时间：08:34</span>
                    <span class="label label-yellow">CA1234 动态时间更改至5小时</span>

                    <span class="label label-red">CA1266 机位从A110变更至806</span>
                    <span class="label label-green">CZ1266 已经落地 实落时间：08:34</span>
                    <span class="label label-yellow">CA1266 动态时间更改至5小时</span>

                    <span class="label label-red">CA1277 机位从A110变更至806</span>
                    <span class="label label-green">CZ1277 已经落地 实落时间：08:34</span>
                    <span class="label label-yellow">CA1277 动态时间更改至5小时</span>
                </marquee>
            </div>
        </footer>
    </div>
    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
    <script>
    (function(b, o, i, l, e, r) {
        b.GoogleAnalyticsObject = l;
        b[l] || (b[l] =
            function() {
                (b[l].q = b[l].q || []).push(arguments)
            });
        b[l].l = +new Date;
        e = o.createElement(i);
        r = o.getElementsByTagName(i)[0];
        e.src = 'https://www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e, r)
    }(window, document, 'script', 'ga'));
    ga('create', 'UA-XXXXX-X');
    ga('send', 'pageview');
    </script>
    <!-- build:js scripts/vendor.js -->
    <!-- bower:js -->
    <script src="bower_components/jquery/dist/jquery.js"></script>
    <script src="bower_components/angular/angular.js"></script> 
    <!-- endbower -->
    <!-- endbuild -->
    <!-- build:js scripts/plugins.js -->
<!--     <script src="bower_components/bootstrap/js/affix.js"></script>
    <script src="bower_components/bootstrap/js/alert.js"></script>
    <script src="bower_components/bootstrap/js/dropdown.js"></script>
    <script src="bower_components/bootstrap/js/tooltip.js"></script>
    <script src="bower_components/bootstrap/js/modal.js"></script>
    <script src="bower_components/bootstrap/js/transition.js"></script>
    <script src="bower_components/bootstrap/js/button.js"></script>
    <script src="bower_components/bootstrap/js/popover.js"></script>
    <script src="bower_components/bootstrap/js/carousel.js"></script>
    <script src="bower_components/bootstrap/js/scrollspy.js"></script>
    <script src="bower_components/bootstrap/js/collapse.js"></script>
    <script src="bower_components/bootstrap/js/tab.js"></script> -->
    <!-- endbuild -->
    <!-- build:js scripts/main.js -->
    <script src="scripts/FlightDynamics/FlightDynamics.js"></script>
    <!-- endbuild -->
</body>


</html>
