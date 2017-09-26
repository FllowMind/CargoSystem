<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>OMS--珠海货运系统</title>
    <!-- <link rel="apple-touch-icon" href="apple-touch-icon.png"> -->
    <!-- Place favicon.ico in the root directory -->
    <!-- build:css styles/vendor.css -->
    <!-- bower:css -->
    <!-- endbower -->
    <!-- endbuild -->
    <!-- build:css styles/main.css -->
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/layout.css">
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/schedule.css">
    <!-- endbuild -->
</head>

<body ng-app="PlateauModule">
    <!--[if IE]>
      <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <div id="Wrapper" ng-controller="MainCtrl" style="overflow:hidden;">
        <header id="Header">
            <div class="logo">
                <a href="#">OMS</a>
            </div>
            <div class="nav">
                <ul>
                	<li><a id="SpeedExplain" href="/index" class="active">机坪动态</a></li>
                    <!-- <li><a id="FlightDynamics" href="#">航班动态</a></li>
                    <li><a id="Work" href="#">工作统计</a></li> -->
                </ul>
            </div>
            <div class="clock" clock-action>
                <div class="time">
                    00:00
                </div>
                <div class="day">
                    2016-6-14 星期一
                </div>
            </div>
            <div class="opt">

                <div class="bar search-bar">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入关键字" ng-model="key">
                        <button type="button" ng-click="serachKey()">搜索</button>
                    </div>
                </div>
                <div class="bar select-bar">
                    <button type="button" ng-click="laydate()">{{flightDate}}</button>
                    <button type="button" ng-click="downLoad()">导出</button>
                </div>
            </div>
        </header>
        <div id="Content" get-data-action><!--  get-data-action -->
            <div id="FlightContent">
                <div class="content-header">
                    机坪调度室航班保障动态
                </div>
                <div class="content-body">
                    <div class="table-header">
                        <table>
                            <tr>
                                <th colspan="2"><span>值班主管</span></th>
                                <th><span ng-click="clickSpan($event,3)" class="name-span">{{employees[2]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[2]}}"> </th>
                                <th><span ng-click="clickSpan($event,3)" class="name-span">{{employees[2]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[2]}}"> </th>
                                <th rowspan="2" width="60">机型</th>
                                <th rowspan="2" width="100">航段</th>
                                <th rowspan="2" width="30">机位</th>
                                <th rowspan="2" width="50">
                                    <div>进港</div>
                                    <div>动态</div>
                                </th> 
                                <th rowspan="2" width="50">
                                    <div>出港</div>
                                    <div>动态</div>
                                </th>
                                <th rowspan="2" width="50">
                                    <div>保障</div>
                                    <div>时限</div>
                                </th>
                                <th><span>值班调度</span></th>
                                <th><span ng-click="clickSpan($event,12)" class="name-span">{{employees[11]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[11]}}"></th>
                                <th><span ng-click="clickSpan($event,13)" class="name-span">{{employees[12]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[12]}}"></th>
                                <th><span ng-click="clickSpan($event,14)" class="name-span">{{employees[13]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[13]}}"></th>
                                <th><span>机坪队长</span></th>
                                <th><span ng-click="clickSpan($event,16)" class="name-span">{{employees[15]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[15]}}"></th>
                                <th><span ng-click="clickSpan($event,17)" class="name-span">{{employees[16]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[16]}}"></th>
                                <th><span ng-click="clickSpan($event,18)" class="name-span">{{employees[17]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[17]}}"></th>
                                <th><span ng-click="clickSpan($event,19)" class="name-span">{{employees[18]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[18]}}"></th>
                                <th><span>行李队长</span></th>
                                <th><span ng-click="clickSpan($event,21)" class="name-span">{{employees[20]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[20]}}"></th>
                                <th><span ng-click="clickSpan($event,22)" class="name-span">{{employees[21]}}</span><input type="text" ng-blur="blurInout($event)" value="{{employees[21]}}"></th>
                                <th><span></span></th>
                            </tr>
                            <tr>
                                <th width="30">序号</th>
                                <th width="30">任务</th>
                                <th width="70">航班号</th>
                                <th width="70">飞机号</th>
                                <th width="60" class="import-td">监装监控</th>
                                <th width="60" class="import-td">吨位预配</th>
                                <th width="80" class="import-td">配载出装机单</th>
                                <th width="60" class="import-td">货物出库</th>
                                <th width="60" class="import-td">航班到达</th>
                                <th width="60" class="import-td">开货舱门</th>
                                <th width="60" class="import-td">行李上盘</th>
                                <th width="60" class="import-td">开始装机</th>
                                <th width="60" class="import-td">行李出仓</th>
                                <th width="60" class="import-td">关货舱门</th>
                                <th width="60" class="import-td">二次开舱门</th>
                                <th width="60" class="import-td">二次关舱门</th>
                                <th width="60" class="import-td">备注</th>
                            </tr>
                        </table>
                    </div>
                    <div class="table-body">
                        <table>
                            <tr  ng-repeat="x in list">
                                <td width="30" nowrap class="import-td status_{{x.status}}">{{$index+1}}</td>
                                <td width="30" nowrap class="import-td status_{{x.status}}">{{x.propertyname}}</td>
                                <td width="70" nowrap class="import-td status_{{x.status}}">{{x.fno|twoline}}</td>
                                <td width="70" nowrap class="import-td status_{{x.status}}">{{x.acno|uppercase}}</td>
                                <td width="60" nowrap class="import-td status_{{x.status}}">{{x.type}}</td>
                                <td width="100" nowrap class="import-td status_{{x.status}}">{{x.leg}}</td>
                                <td width="30" nowrap class="import-td status_{{x.status}}">{{x.location}}</td>
                                <td width="50" nowrap class="import-td status_{{x.status}}">{{x.indynamic}}</td>
                                <td width="50" nowrap class="import-td status_{{x.status}}">{{x.outdynamic}}</td>
                                <td width="50" nowrap class="import-td status_{{x.status}}">{{x.limittime}}</td>

                                <td width="60" nowrap class="status_{{x.status}}">{{x.impoupopid}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.cdpztime}}</td>
                                <td width="80" nowrap class="status_{{x.status}}">{{x.sczjdotime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.hwcctime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.putWheel}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.kcmtime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.xlxptime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.hwksxjtime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.xlcctime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.gcmtime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.eckcmtime}}</td>
                                <td width="60" nowrap class="status_{{x.status}}">{{x.ecgcmtime}}</td>
                                <td width="60" class="remark-td status_{{x.status}}" title="{{x.notecontent}}"><span style="width:100%;overflow:hidden" class="remark-sapn" ng-click="clickRemarkSpan($event)">{{x.notecontent | ellipsiStr}}</span><input style="width:96%;" name="" data-oldVal="{{x.notecontent}}" ng-blur="postRemark($event,x.serialno)" value="{{x.notecontent}}"></td>
                            </tr>
                            
                        </table>
                        <div style="height: 4rem;"></div>
                    </div>
                </div>
            </div>
        </div>
        <footer id="Footer">
             <input type="text" ng-model="autoFlushTime">秒<button class="btn" type="button" ng-click="autoFlush()">{{autoFlushText}}</button>
        </footer>
    </div>
    <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
    <script>
        (function (b, o, i, l, e, r) {
            b.GoogleAnalyticsObject = l;
            b[l] || (b[l] =
                function () {
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
    <script src="bower_components/layer/src/layer.js"></script>
    <script src="bower_components/laydate/laydate.js"></script>
    <script src="bower_components/jquery.nicescroll/jquery.nicescroll.min.js"></script>
    <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
    <!-- endbower -->
    <!-- build:js scripts/main.js -->
    <script src="scripts/PlateauDynamics/plateau.app.jsp"></script>
    <!-- endbuild -->
</body>

</html>