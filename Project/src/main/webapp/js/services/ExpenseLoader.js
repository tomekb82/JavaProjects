'use strict';

angular.module('angularspring.services')
.factory('ExpenseLoader', ['Expense', '$route', '$q',function(Expense, $route, $q) {
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

