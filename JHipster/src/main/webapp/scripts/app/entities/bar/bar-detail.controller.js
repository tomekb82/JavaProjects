'use strict';

angular.module('jhipsterApp')
    .controller('BarDetailController', function ($scope, $stateParams, Bar, Bar2) {
        $scope.bar = {};
        $scope.load = function (id) {
            Bar.get({id: id}, function(result) {
              $scope.bar = result;
            });
        };
        $scope.load($stateParams.id);
    });
