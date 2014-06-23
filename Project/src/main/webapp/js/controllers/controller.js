'use strict';

/* Controllers */

var app = angular.module('angularspring.controllers', []);

    
    app.controller('MainController', function ($scope, $rootScope, $http, i18n, $location) {
        $scope.language = function () {
            return i18n.language;
        };
        $scope.setLanguage = function (lang) {
            i18n.setLanguage(lang);
        };
        $scope.activeWhen = function (value) {
            return value ? 'active' : '';
        };

        $scope.path = function () {
            return $location.url();
        };

        $scope.login = function () {
            $scope.$emit('event:loginRequest', $scope.username, $scope.password);
            $('#login').modal('hide');
        };
        $scope.logout = function () {
            $rootScope.user = null;
            $scope.username = $scope.password = null;
            $scope.$emit('event:logoutRequest');
            $location.url('/person');
        };

    });

    app.controller('PersonController', function ($scope, $http, i18n) {
        var actionUrl = 'mvc/rest_expenses/json',//'action/person/',
            load = function () {
                $http.get(actionUrl).success(function (data) {
                    $scope.persons = data;
                });
            };

        load();

        $scope.order = 'name';//'+firstName';

        $scope.orderBy = function (property) {
            $scope.order = ($scope.order[0] === '+' ? '-' : '+') + property;
        };

        $scope.orderIcon = function (property) {
            return property === $scope.order.substring(1) ? $scope.order[0] === '+' ? 'icon-chevron-up' : 'icon-chevron-down' : '';
        };
    });
        
    app.controller('PaginationController', ['$scope', '$http', 'paginator',function($scope, $http, paginator) {
        //$scope.query = 'Tomek';
        $scope.limit = 100;
        var fetchFunction = function(offset, limit, callback) {
          //console.log("PaginationController: fetchFunction(), query=" + $scope.query);
          if($scope.query != ""){
        	  $http.get('mvc/rest_expenses/json/limit_offset', {params: {query: $scope.query, offset: offset, limit: limit}}).success(callback);
          }else{
        	  $http.get('mvc/rest_expenses/json/limit_offset', {params: {offset: offset, limit: limit}}).success(callback);  
          }
        };
      
        $scope.searchPaginator = paginator(fetchFunction, 2);
        
        $scope.fetch = function() {
        	$scope.searchPaginator = paginator(fetchFunction, 2);
		};
    }]);
    
    app.controller('ListController', ['$scope', '$routeParams', 'expenses', 
                                        function ($scope, $routeParams, expenses) {
		$scope.expenses = expenses;
   }]);
    
    app.controller('EditController', ['$scope', '$routeParams', 'expense', 
                                        function ($scope, $routeParams, expense) {
    	$scope.expense = expense;	
   }]);
    app.controller('CommonController', ['$scope', '$routeParams', 
                                         function ($scope, $routeParams) {
    	/* filters */
        $scope.testFilter = 'program for demonstrating custom filter in angular js';
        
        /* directives */
        $scope.title = 'Click me to expand';
    	$scope.text = 'Hi there folks, I am the content that was hidden but is now shown.';
    		
        $scope.message = { text: 'nothing clicked yet' };
		$scope.clickUnfocused = function() {
			$scope.message.text = 'unfocused button clicked';
		};
		$scope.clickFocused = function() {
			$scope.message.text = 'focus button clicked';
		};
		
		/* forms */
    	$scope.msg = '';
		$scope.addUser = function () {
			// TODO for the reader: actually save user to database...
			$scope.message = 'Thanks, ' + $scope.user.first + ', we added you!';
		};
    }]);
        
    app.controller('AdminController', function ($scope, $http, i18n) {
        $http.get('action/user');
        
        
        var actionUrl = 'mvc/rest_expenses/json',//'action/person/',
        load = function () {
            $http.get(actionUrl).success(function (data) {
                $scope.persons = data;
            });
        };

        load();

        $scope.delete = function (person) {
        	$http.delete(actionUrl + person.id).success(function () {
        		load();
        	});
        };

        $scope.save = function () {
        	$http.post(actionUrl, $scope.person).success(function () {
        		load();
        	});
        };

        $scope.order = 'name';//'+firstName';

        $scope.orderBy = function (property) {
        	$scope.order = ($scope.order[0] === '+' ? '-' : '+') + property;
        };

        $scope.orderIcon = function (property) {
        	return property === $scope.order.substring(1) ? $scope.order[0] === '+' ? 'icon-chevron-up' : 'icon-chevron-down' : '';
        };
      
        
    });
