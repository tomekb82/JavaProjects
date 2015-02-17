'use strict';

angular.module('jhipsterApp')
    .controller('BarController', function ($scope, Bar, Bar2) {
        $scope.bars = [];
        $scope.bar2s = Bar2.query();
        $scope.loadAll = function() {
            Bar.query(function(result) {
               $scope.bars = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Bar.save($scope.bar,
                function () {
                    $scope.loadAll();
                    $('#saveBarModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.bar = Bar.get({id: id});
            $('#saveBarModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.bar = Bar.get({id: id});
            $('#deleteBarConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Bar.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteBarConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.bar = {field1: null, id: null};
        };
    });
