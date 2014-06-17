'use strict';

angular.module('ngdemo.services')
.factory('Recipe', ['$resource', function($resource) {
	return $resource('/ngdemo/rest/recipes/:id', {id: '@id'},
	 {
        query: {
            method: 'GET',
            params: {},
            isArray: true
        }
    });
}]);

