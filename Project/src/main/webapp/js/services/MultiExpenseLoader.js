'use strict';

var factory = angular.module('angularspring.services', []);

factory.factory('MultiExpenseLoader', ['expenses', '$q', function(expenses, $q) {
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

