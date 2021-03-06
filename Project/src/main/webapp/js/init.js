'use strict';

//Declare app level module which depends on filters, and services

//the HTTP headers to be used by all requests
var httpHeaders;

//the message to be shown to the user
var message;

//Define the main module.
//The module is accessible everywhere using "angular.module('angularspring')", therefore global variables can be avoided totally.
var as = angular.module('angularspring', [
                      
'angularspring.services',
'angularspring.filters',
'angularspring.directives',
'angularspring.controllers',

/* externals */
'ngResource'

]);


	as.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        //configure the rounting of ng-view
        $routeProvider
        	/* Expenses */
        	.when('/view_expense', { 
        		controller: 'PersonController', 
        		templateUrl: 'angularjs_pages/public/view_expense.html'})
        	.when('/new_expense', { 
        		controller: 'AdminController', 
        		templateUrl: 'angularjs_pages/admin/new_expense.html'})
        	.when('/del_expense', { 
        		controller: 'AdminController', 
        		templateUrl: 'angularjs_pages/admin/del_expense.html'})
        	/* Services */
        	.when('/service', { 
        		resolve: {
        			expenses: ["MultiExpenseLoader", function(MultiExpenseLoader) {
        				return MultiExpenseLoader();
                    }]
                },
        		controller: 'ListController', 
        		templateUrl: 'angularjs_pages/public/service.html'})
        	.when('/service/:name', { 
        		resolve: {
        			expense: ["ExpenseLoader", function(ExpenseLoader) {
        				return ExpenseLoader();
                    }]
                },
        		controller: 'EditController', 
        		templateUrl: 'angularjs_pages/public/service_detail.html'})	
        	/* Filters */
        	.when('/filter', { 
        		controller: 'CommonController', 
        		templateUrl: 'angularjs_pages/public/filter.html'})
        	/* Directives */
        	.when('/directive', { 
        		controller: 'CommonController', 
        		templateUrl: 'angularjs_pages/public/directive.html'})
        	/* Forms */
        	.when('/form', { 
        		controller: 'CommonController', 
        		templateUrl: 'angularjs_pages/public/form.html'})
        	/* Pagination */
        	.when('/pagination', { 
        		controller: 'PaginationController', 
        		templateUrl: 'angularjs_pages/public/pagination.html'});
        
        //configure $http to catch message responses and show them
        $httpProvider
        	.responseInterceptors.push(function ($q) {
        		var setMessage = function (response) {
        			//if the response has a text and a type property, it is a message to be shown
        			if (response.data.text && response.data.type) {
        				message = {
        						text: response.data.text,
        						type: response.data.type,
        						show: true
        				};
        			}
        		};
        		return function (promise) {
        			return promise.then(
        					//this is called after each successful server request
        					function (response) {
        						setMessage(response);
        						return response;
        					},
        					//this is called after each unsuccessful server request
        					function (response) {
        						setMessage(response);
        						return $q.reject(response);
        					}
        			);
        		};
        	});
        	//configure $http to show a login dialog whenever a 401 unauthorized response arrives
        	$httpProvider
        		.responseInterceptors.push(function ($rootScope, $q) {
        			return function (promise) {
        				return promise.then(
        						//success -> don't intercept
        						function (response) {
        							return response;
        						},
        						//error -> if 401 save the request and broadcast an event
        						function (response) {
        							if (response.status === 401) {
        								var deferred = $q.defer(),
        								req = {
        									config: response.config,
        									deferred: deferred
        								};
        								$rootScope.requests401.push(req);
        								$rootScope.$broadcast('event:loginRequired');
        								return deferred.promise;
        							}
        							return $q.reject(response);
        						}
        				);
        			};
        		});
        	httpHeaders = $httpProvider.defaults.headers;
	}]);		

 	as.run(function ($rootScope, $http, base64) {
 		//make current message accessible to root scope and therefore all scopes
        $rootScope.message = function () {
            return message;
        };

        /**
         * Holds all the requests which failed due to 401 response.
         */
        $rootScope.requests401 = [];

        $rootScope.$on('event:loginRequired', function () {
            $('#login').modal('show');
        });

        /**
         * On 'event:loginConfirmed', resend all the 401 requests.
         */
        $rootScope.$on('event:loginConfirmed', function () {
            var i,
                requests = $rootScope.requests401,
                retry = function (req) {
                    $http(req.config).then(function (response) {
                        req.deferred.resolve(response);
                    });
                };

            for (i = 0; i < requests.length; i += 1) {
                retry(requests[i]);
            }
            $rootScope.requests401 = [];
        });

        /**
         * On 'event:loginRequest' send credentials to the server.
         */
        $rootScope.$on('event:loginRequest', function (event, username, password) {
            httpHeaders.common['Authorization'] = 'Basic ' + base64.encode(username + ':' + password);
            $http.get('action/user').success(function (data) {
                $rootScope.user = data;
                $rootScope.$broadcast('event:loginConfirmed');
            });
        });

        /**
         * On 'logoutRequest' invoke logout on the server and broadcast 'event:loginRequired'.
         */
        $rootScope.$on('event:logoutRequest', function () {
            httpHeaders.common['Authorization'] = null;
        });
    });

