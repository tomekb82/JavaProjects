'use strict';

angular.module('ngdemo.controllers')
.controller('ListCtrl', ['$scope', 'recipes',
    function($scope, recipes) {

        $scope.recipes = recipes;

        console.log("ListCtrl: " + $scope.recipes[0].ingredients);

}]);
