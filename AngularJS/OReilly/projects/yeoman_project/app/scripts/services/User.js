'use strict';

angular.module('yeomanProjectApp')
.factory('User', ['$resource', function($resource) {
        return $resource('/users/:name', {name: '@name'});
}]);
