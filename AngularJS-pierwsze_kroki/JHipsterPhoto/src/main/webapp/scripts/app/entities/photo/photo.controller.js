'use strict';

angular.module('jhipsterphotoApp')
    .controller('PhotoController', function ($scope, Photo) {
        $scope.photos = [];
        $scope.loadAll = function() {
            Photo.query(function(result) {
               $scope.photos = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Photo.get({id: id}, function(result) {
                $scope.photo = result;
                $('#deletePhotoConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Photo.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePhotoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.photo = {name: null, opinions: null, id: null};
        };
    });
