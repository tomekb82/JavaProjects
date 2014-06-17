'use strict';

angular.module('ngdemo.controllers')
.controller('IngredientsCtrl', ['$scope',
    function($scope) {

  console.log("IngredientsCtrl: ");
  $scope.addIngredient = function() {
    var ingredients = $scope.recipe.ingredients;
    ingredients[ingredients.length] = {};
  };
  $scope.removeIngredient = function(index) {
    $scope.recipe.ingredients.splice(index, 1);
  };
}]);
