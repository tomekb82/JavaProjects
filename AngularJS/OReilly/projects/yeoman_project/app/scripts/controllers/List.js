'use strict';

angular.module('yeomanProjectApp')
.controller('ListCtrl', ['$scope', 'recipes',
    function($scope, recipes) {
  $scope.recipes = recipes;
}]);
