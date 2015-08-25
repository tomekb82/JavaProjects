'use strict';

angular.module('jhipsterphotoApp').controller('PhotoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Photo',
        function($scope, $stateParams, $modalInstance, entity, Photo) {

        $scope.photo = entity;
        $scope.load = function(id) {
            Photo.get({id : id}, function(result) {
                $scope.photo = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('jhipsterphotoApp:photoUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            $scope.photo.opinions = [];
            $scope.photo.opinions[0] = $scope.photo.op1;
            $scope.photo.opinions[1] = $scope.photo.op2;
            console.log("==================  save()");
            if ($scope.photo.id != null) {
                Photo.update($scope.photo, onSaveFinished);
            } else {
                Photo.save($scope.photo, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
