'use strict';

/* Services */

var services = angular.module('angularspring.services', ['ngResource']);

	services.factory('ExpenseFactory', function ($resource) {
    	return $resource('mvc/rest_expenses/json/:name', {name: '@name'}, {
        	query: {
            	method: 'GET',
            	params: {},
            	isArray: true
        	  },
    		get: {
    			method: 'GET',
    			params: {},
    			isArray: false
    	  }
        });
    });
	
	services.factory('Expense', ['$resource', function($resource) {
	        return $resource('mvc/rest_expenses/json/:name', {name: '@name'});
	}]);
	
	services.factory('MultiExpenseLoader', ['expenses', '$q', function(expenses, $q) {
		return function() {
			var delay = $q.defer();
	        	Expense.query(
	        			// on success
	                    function(expenses) {
	                    	delay.resolve(expenses);
	                    }, 
	                    // on error
	                    function() {
	                    	delay.reject('Unable to fetch expenses');
	                    }
	             );
	        return delay.promise;
	   };
	}]);
	
	services.factory('ExpenseLoader', ['Expense', '$route', '$q',function(Expense, $route, $q) {
		return function() {
			var delay = $q.defer();
	    
			Expense.get({name: $route.current.params.name}, function(expense) {
				delay.resolve(expense);
			}, function() {
				delay.reject('Unable to fetch expense ', $route.current.params.name);
			});
	    
			return delay.promise;
	  };
	}]);
	


	
	 
    
    

