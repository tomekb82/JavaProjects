'use strict';

var services = angular.module('angularspring.services', ['ngResource']);

services.factory('Expense', ['$resource', function($resource) {
        return $resource('mvc/rest_expenses/json2/:name', {name: '@name'});
}]);
