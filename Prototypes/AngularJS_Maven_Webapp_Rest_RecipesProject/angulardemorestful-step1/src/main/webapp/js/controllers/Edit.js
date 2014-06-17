'use strict';

angular.module('ngdemo.controllers')
.controller('EditCtrl', ['$scope', '$location', 'recipe',
    function($scope, $location, recipe) {
  $scope.recipe = recipe;

  $scope.save = function() {
    $scope.recipe.$save(function(recipe) {
      $location.path('/view/' + recipe.id);
    });
  };

  $scope.remove = function() {
    $scope.recipe.$delete();
    $location.path('/list');
  };
}]);
