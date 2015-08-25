'use strict';

angular.module('jhipsterphotoApp')
    .controller('PhotoDetailController', function ($scope, $rootScope, $stateParams, entity, Photo) {
        $scope.photo = entity;
        $scope.load = function (id) {
            Photo.get({id: id}, function(result) {
                $scope.photo = result;
            });
        };
        $rootScope.$on('jhipsterphotoApp:photoUpdate', function(event, result) {
            $scope.photo = result;
        });
    });
