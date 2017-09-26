
var app=angular.module('OMS', ['ngRoute']).config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	
}]);

app.controller('fileDynamicCtrl', ['$scope', '$http', function($scope, $http) {

}]);

app.directive('getDynamic', ['$http', function($http) {
	// Runs during compile
	return {
		link: function($scope, iElm, iAttrs, controller) {
			var theOldData = null; //存储上一次轮询数据的字段
			//获取航班信息
			//Ajax轮询获取数据
			$http({
				url: "/osm/getData/getFlightList ",
				method: "GET",
				dataType: "json",
				withCredentials: true,
				crossDomain: true,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).then(function(data) {
				var list = [];
				var departure = {}; //出港
				var arrival = {}; //进港
				for (var i = 0; i < data.data.RetEntity.length; i++) {
					var isConnect = false;
					for (var j = (i + 1); j < data.data.RetEntity.length; j++) {
						//判断是否是联程航班
						if (data.data.RetEntity[i].SerialNo == data.data.RetEntity[j].SerialNo) {
							if (data.data.RetEntity[i].Fdep == "ZUH") {
								departure = data.data.RetEntity[i]; //如果珠海在起飞站，则表示出港
								arrival = data.data.RetEntity[j];
							} else {
								departure = data.data.RetEntity[j];
								arrival = data.data.RetEntity[i]; //如果珠海在降落站，则表示进港
							}
							list.push({
								"arrival": arrival,
								"departure": departure
							});
							isConnect = true;
							return false;
						}
					}
					if (!isConnect) {
						if (data.data.RetEntity[i].Fdep == "ZUH") {
							departure = data.data.RetEntity[i]; //如果珠海在起飞站，则表示出港
							arrival = null;
						} else {
							departure = null;
							arrival = data.data.RetEntity[i]; //如果珠海在降落站，则表示进港
						}
						list.push({
							"arrival": arrival,
							"departure": departure
						});
					}
				}
				$scope.dynamicList = list;
			});
		}
	};
}]);

/**
 * 数据对比服务
 * @param  {[type]} ){	return {}}]      [description]
 * @return {[type]}            [description]
 */
app.service('DataComparison', ['', function() {
	var comparison = function(newdata, olddata) {
		for(var key in newdata){
			if(newdata[key]!=null){
				for(var childkey in newdata[key]){
					//DestTime/RealDepTime/Attribus
					if(childkey=="DestTime"){
						if(newdata[key][childkey]!=olddata[key][childkey]){
							//如果计划起飞不一致
						}
					}
					if(childkey=="RealDepTime"){
						if(newdata[key][childkey]!=olddata[key][childkey]){
							//如果动态时间不一致
						}
					}
					if(childkey=="Attribus"){
						if(newdata[key][childkey]!=olddata[key][childkey]){
							//如果进港状态不一致
						}
					}
				}
			}
		}
	}
	return {

	}
}]);