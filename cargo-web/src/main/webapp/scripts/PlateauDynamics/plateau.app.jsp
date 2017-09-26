<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="java.util.*" %>

var localObj = window.location;
window.contextPath = "/" + localObj.pathname.split("/")[1];

var app = angular.module('PlateauModule', []);

app.controller('MainCtrl', ['$scope', '$http', '$filter', function($scope, $http, $filter) {
    var date = new Date;
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    $scope.flightDate = year + "-" + (month > 9 ? month : "0" + month) + "-" + (day > 9 ? day : "0" + day);
    $scope.autoFlushTime = 0; //自动刷新的时间
    $scope.autoFlushText = "开启自动刷新"; //自动刷新按钮文字
    $scope.key = "";
    $scope.scrollIndex = 0; //滚动条位置
    $scope.laydate = function() {
        laydate({
            istime: false,
            istoday:false,
            isclear:false,
            format: 'YYYY-MM-DD',
            choose: function(datas) {
                $scope.flightDate = datas;
            }
        });
    };
    /**
     * 监听航班日期的变更
     * @param  {[type]} newVal  [description]
     * @param  {[type]} oldVal) {                   if ((newVal ! [description]
     * @return {[type]}         [description]
     */
    $scope.$watch('flightDate', function(newVal, oldVal) {
        if ((newVal != oldVal) && newVal != "") {
            $scope.getList();
        }
    });


    /**
     * 手动获取数据
     * @return {[type]} [description]
     */
    $scope.getList = function() {
        var postConf = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function(data) {
                return $.param(data);
            }
        };
        var data = {
            startFdate: $scope.flightDate,
            endFdate: $scope.flightDate,
        };
        var layIndex = layer.load();
        $http.post(contextPath + '/getData/getFlightsMsg.action', data, postConf)
            .then(function(response) {
                if (!response.data.status) {
                    layer.alert(response.data.message, {
                        icon: 0,
                        title: 'Error'
                    });
                    layer.close(layIndex);

                    return false;
                }
                $scope.flights = response.data.data.flights;
                $scope.list = $scope.flights;
                var employees = response.data.data.employees;
                var arr = new Array(22);
                for (var i = 0; i < employees.length; i++) {
                    if (employees[i].substitute == null || employees[i].substitute == "") {
                        arr[employees[i].position] = employees[i].name;
                    } else if ((employees[i].name == "" || employees[i].name == null) && (arr[employees[i].position] == "" || arr[employees[i].position] == null)) {
                        arr[employees[i].position] = "";
                    } else {
                        arr[employees[i].position] = employees[i].substitute;
                    }
                }

                $('#Content').niceScroll({
                    cursorcolor: "#ccc",
                    cursoropacitymax: 1,
                    touchbehavior: false,
                    cursorwidth: "3px",
                    cursorborder: "0",
                    cursorborderradius: "1px",
                    autohidemode: false,
                    enablescrollonselection: true
                });
                layer.close(layIndex);
                var scrollIndex = response.data.data.index;//滚动条Y轴位置
                $("#FlightContent").getNiceScroll(0).doScrollTop((scrollIndex-1) * 25);
            });
    };
    /**
     * 点击值班人
     * @param  {[type]} event       [description]
     * @param  {[type]} columnIndex [description]
     * @return {[type]}             [description]
     */
    $scope.clickSpan = function(event, columnIndex) {
        var oldVal = $(event.target).siblings('input').val();
        $(event.target).hide();
        $(event.target).siblings('input').attr('data-cloumn-index', columnIndex).attr('data-oldVal', oldVal).show().focus();
    };

    /**
     * 输入完交班人之后的操作
     * @param  {[type]} event [description]
     * @return {[type]}       [description]
     */
    $scope.blurInout = function(event) {
        var cloumIndex = $(event.target).attr('data-cloumn-index');
        var oldVal = $(event.target).attr('data-oldVal');
        var newVal = $(event.target).val();
        if (oldVal == "" || oldVal == null) {
            oldVal = newVal;
            newVal = null;
        }
        if (oldVal != newVal) {
            //提交数据
            var postConf = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function(data) {
                    return $.param(data);
                }
            };
            var data = {
                date: $scope.flightDate,
                position: cloumIndex,
                name: oldVal,
                substitute: newVal
            };
            $http.post(contextPath + '/excel/recordEmployee', data, postConf).then(function(res) {
                if (!res.data.status) {
                    layer.alert('更改交班人失败！', {
                        icon: 0
                    });
                    $(event.target).val("");
                }
            });
        }
        $(event.target).hide().siblings('span').text(newVal == null ? oldVal : newVal).show();
    };
    /**
     * 导出
     * @return {[type]} [description]
     */
    $scope.downLoad = function() {
        window.open(contextPath + "/excel/exportExcel?date=" + $scope.flightDate);
    };

    /**
     * 自动刷新
     * @return {[type]} [description]
     */
    $scope.autoFlush = function() {
        var t = $scope.autoFlushTime;
        if ($scope.autoFlushText == "关闭自动刷新") {
            $scope.autoFlushText = "开启自动刷新";
            if ($scope.autoFlushLab) {
                clearInterval($scope.autoFlushLab);
            }
        } else {
            if (t == 0 || t == "0") {
                return false;
            }
            $scope.autoFlushLab = setInterval(function() {
                $scope.getList();
            }, t * 1000);
            $scope.autoFlushText = "关闭自动刷新";
        }
    };
    /**
     * 点击备注td
     * @param  {[type]} event    [description]
     * @param  {[type]} serialno [description]
     * @return {[type]}          [description]
     */
    $scope.clickRemarkSpan = function(event) {
        $(event.target).hide();
        $(event.target).siblings('input').show().focus();
    };

    /**
     * 搜索
     * @return {[type]} [description]
     */
    $scope.serachKey = function() {
        var key = $scope.key;
        if (key && key != "") {
            $scope.list = $filter("filter")($scope.flights, key);
        } else {
            $scope.getList();
        }
    };
    /**
     * 提交备注修改
     * @param  {[type]} event    [触发事件的元素]
     * @param  {[type]} serialno [航班组ID]
     * @return {[type]}          [description]
     */
    $scope.postRemark = function(event, serialno) {
        var newVal = $(event.target).val();
        var oldVal = $(event.target).attr('data-oldVal');
        //如果用户没有进行任何操作
        if (oldVal == newVal) {
            $(event.target).siblings('span').css('display', 'inline-block');
            $(event.target).css('display', 'none');
            return false;
        }
        if (newVal.length > 6) {
            var a = newVal.substring(0, 4);
            $(event.target).siblings('span').text(a + "...");
            $(event.target).parent('td').attr('title', newVal);
        } else {
            $(event.target).siblings('span').text(newVal);
        }
        $(event.target).siblings('span').css('display', 'inline-block');
        $(event.target).css('display', 'none');
        var createTime = $scope.flightDate;
        var data = {
            serialno: serialno,
            creationDate: createTime,
            noteContent: newVal
        }
        var postConf = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function(data) {
                return $.param(data);
            }
        }
        $http.post(contextPath + '/note/saveNote', data, postConf)
            .then(function(res) {
                if (!res.data.status) {
                    layer.alert(res.data.message, {
                        icon: 0,
                        title: "Error"
                    });
                }
            });
    };
}]);

/**
 * 时钟&&UI初始化
 * @param  {String}
 * @return {[type]}        [description]
 */
app.directive('clockAction', ['$http', function($http) {
    // Runs during compile
    return {
        template: '<div class="time">{{time}}</div><div class="day">{{day}} {{week}}</div>',
        restrict: 'AE',
        link: function($scope, iElm, iAttrs, controller) {
            $scope.time = "";
            $scope.day = "";
            $scope.week = "";
            //编译时生产,放在外面,每次执行增加一秒
            var date = new Date(<%=new java.util.Date().getTime()%>);
            window.setInterval(function() {
                $scope.$apply(function() {
             		date.setSeconds(date.getSeconds()+1); 
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var day = date.getDate();
                    $scope.day = year + "-" + (month > 9 ? month : "0" + month) + "-" + (day > 9 ? day : "0" + day);
                    var hours = date.getHours() > 9 ? date.getHours() : "0" + date.getHours();
                    var minutes = date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes();
                    var seconds = date.getSeconds() > 9 ? date.getSeconds() : "0" + date.getSeconds();
                    $scope.time = hours + ":" + minutes + ":" + seconds;
                    var weekday = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
                    $scope.week = weekday[date.getDay()];
                });
            }, 1000);
        }
    };
}]);

/**
 * 获取数据的指令
 * @param  {[type]}
 * @return {[type]} 
 */
app.directive('getDataAction', ['$http', function($http) {
    // Runs during compile
    return {
        restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
        link: function($scope, iElm, iAttrs, controller) {

            var now = getNowTime();
            //提交数据
            var postConf = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function(data) {
                    return $.param(data);
                }
            };
            var data = {
                startFdate: now,
                endFdate: now,
            };
            var layIndex = layer.load();
            $http.post(contextPath + '/getData/getFlightsMsg.action', data, postConf)
                .then(function(response) {
                    $('#ZBJKSpan').css('display', 'inline-block');
                    if (!response.data.status) {
                        layer.alert(response.data.message, {
                            icon: 0,
                            title: 'Error'
                        });
                        layer.close(layIndex);

                        return false;
                    }
                    $scope.flights = response.data.data.flights
                    $scope.list = $scope.flights;
                    var employees = response.data.data.employees;
                    var arr = new Array(22);
                    for (var i = 0; i < employees.length; i++) {
                        if (employees[i].substitute == null || employees[i].substitute == "") {
                            arr[employees[i].position] = employees[i].name;
                        } else if ((employees[i].name == "" || employees[i].name == null) && (arr[employees[i].position] == "" || arr[employees[i].position] == null)) {
                            arr[employees[i].position] = "";
                        } else {
                            arr[employees[i].position] = employees[i].substitute;
                        }
                    }
                    $scope.employees = arr;
                    $('#FlightContent').niceScroll({
                        cursorcolor: "#ccc",
                        cursoropacitymax: 1,
                        touchbehavior: false,
                        cursorwidth: "10px",
                        cursorborder: "0",
                        cursorborderradius: "1px",
                        autohidemode: false,
                        enablescrollonselection: true
                    });
                    layer.close(layIndex);
                    var scrollIndex = response.data.data.index;
                    $("#FlightContent").getNiceScroll(0).doScrollTop((scrollIndex-1) * 25);
                });
        }
    };
}]);

/**
 * 航班号上下排列过滤器
 */
app.filter('twoline', function() {
    return function(input) {
        if (input.indexOf(',') > -1) {
            var a = input.split(',');
            return a[0] + "\n" + a[1];
        }
        return input;
    }
});
/**
 * 字太长省略
 */
app.filter('ellipsiStr', function() {
    return function(input) {
        if (input == null) {
            return input;
        }
        if (input.length > 6) {
            var newStr = input.substring(0, 4);
            return newStr + "...";
        }
        return input;
    }
});

/**
 * 去掉字符串空格
 * @param  {[type]} s [description]
 * @return {[type]}   [description]
 */
function trim(s) {
    return trimRight(trimLeft(s));
};
//去掉左边的空白 
function trimLeft(s) {
    if (s == null) {
        return "";
    }
    var whitespace = new String(" \t\n\r");
    var str = new String(s);
    if (whitespace.indexOf(str.charAt(0)) != -1) {
        var j = 0,
            i = str.length;
        while (j < i && whitespace.indexOf(str.charAt(j)) != -1) {
            j++;
        }
        str = str.substring(j, i);
    }
    return str;
};
//去掉右边的空白
function trimRight(s) {
    if (s == null) return "";
    var whitespace = new String(" \t\n\r");
    var str = new String(s);
    if (whitespace.indexOf(str.charAt(str.length - 1)) != -1) {
        var i = str.length - 1;
        while (i >= 0 && whitespace.indexOf(str.charAt(i)) != -1) {
            i--;
        }
        str = str.substring(0, i + 1);
    }
    return str;
};

/**
 * 返回当前时间
 * @return {[type]} [description]
 */
function getNowTime() {
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    var now = year + "-" + month + "-" + day;
    return now;
}