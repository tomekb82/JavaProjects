'use strict';

angular.module('yeomanProjectApp')
.factory('Recipe', ['$resource', function($resource) {
	return $resource('/recipes/:id', {id: '@id'});
}]);

