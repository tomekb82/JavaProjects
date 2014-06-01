'use strict';

angular.module('yeomanProjectApp')
.controller('ViewCtrl', ['$scope', '$location', 'recipe',
    function($scope, $location, recipe) {
  $scope.recipe = recipe;

  $scope.edit = function() {
    $location.path('/edit/' + recipe.id);
  };
}]);
