'use strict';

angular.module('jhipsterApp')
    .controller('FooController', function ($scope, Foo) {
        $scope.foos = [];
        $scope.loadAll = function() {
            Foo.query(function(result) {
               $scope.foos = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Foo.save($scope.foo,
                function () {
                    $scope.loadAll();
                    $('#saveFooModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.foo = Foo.get({id: id});
            $('#saveFooModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.foo = Foo.get({id: id});
            $('#deleteFooConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Foo.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteFooConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.foo = {firstName: null, id: null};
        };
    });
