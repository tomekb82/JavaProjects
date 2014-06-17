'use strict';

angular.module('ngdemo.services')
.factory('User', ['$resource', function($resource) {
        return $resource('/users/:name', {name: '@name'});
}]);
