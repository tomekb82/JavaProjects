'use strict';

var myApp = angular.module('myApp', []);

myApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

myApp.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
})

/* Controllers */
myApp.controller('UserListCtrl', function ($scope, $http, $templateCache){

  $scope.todos = 'unknown';
  $scope.ready = false;
  $scope.edited = false;
  $scope.list = function() {
	  var url = 'http://localhost:1212/getTodos';	
	  $http.get(url).success(function(data, status) {
		$scope.todos = data;
		$scope.ready = true;
	  });

  };

  $scope.isReady = function() {
	return $scope.ready==true;
  }
  $scope.isEdit = function() {
	return $scope.edited==true;
  }
  
  $scope.list();

  $scope.currentPage = 0;
  $scope.pageSize = 10;
  $scope.numberOfPages=function(){
 	return Math.ceil($scope.todos.length/$scope.pageSize);                
  } 
  $scope.isNextPage=function(){
	return $scope.currentPage >= $scope.todos.length/$scope.pageSize - 1
  }

  $scope.edit=function(taskid){

  	var method = 'POST';
  	var url = 'http://localhost:1212/postTodos';
 	var formData = {
      		'taskid' : taskid
    	};
        var jdata = 'mydata='+JSON.stringify(formData);

        $http({
      		method: method,
      		url: url,
      		data:  jdata ,
      		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      		cache: $templateCache
    	}).success(function(data, status) {
		$scope.desc = data[0];
                $scope.edited = 1;//!$scope.edited;
                console.log("DESC" + $scope.desc);
	});

  }

})


