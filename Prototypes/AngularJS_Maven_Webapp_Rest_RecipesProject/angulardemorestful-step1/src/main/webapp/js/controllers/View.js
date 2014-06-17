'use strict';

angular.module('ngdemo.controllers')
.controller('ViewCtrl', ['$scope', '$location', 'recipe',
    function($scope, $location, recipe) {
  $scope.recipe = recipe;

  console.log('ViewCtrl:' + $scope.recipe);

  $scope.edit = function() {
    $location.path('/edit/' + recipe.id);
  };
}]);
