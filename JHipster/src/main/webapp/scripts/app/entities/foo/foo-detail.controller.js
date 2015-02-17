'use strict';

angular.module('jhipsterApp')
    .controller('FooDetailController', function ($scope, $stateParams, Foo) {
        $scope.foo = {};
        $scope.load = function (id) {
            Foo.get({id: id}, function(result) {
              $scope.foo = result;
            });
        };
        $scope.load($stateParams.id);
    });
