'use strict';

angular.module('ngdemo.services')
.factory('MultiRecipeLoader', ['Recipe', '$q', function(Recipe, $q) {
	return function() {
		var delay = $q.defer();
		Recipe.query(
			// on success
			function(recipes) {
                delay.resolve(recipes);
			}, 
			// on error
			function() {
				delay.reject('Unable to fetch recipes');
			}
		);
		return delay.promise;
	};
}]);


