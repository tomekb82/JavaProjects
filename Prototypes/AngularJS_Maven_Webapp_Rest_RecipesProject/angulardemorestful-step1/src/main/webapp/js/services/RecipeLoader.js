'use strict';

angular.module('ngdemo.services')
.factory('RecipeLoader', ['Recipe', '$route', '$q',
    function(Recipe, $route, $q) {
    console.log("RecipeLoader: " + $route.current.params.recipeId);
  return function() {
    console.log("RecipeLoader: " + $route.current.params.recipeId);
    var delay = $q.defer();
    console.log("RecipeLoader: " + $route.current.params.recipeId);
    Recipe.get({id: $route.current.params.recipeId}, function(recipe) {
      console.log("RecipeLoader: " + recipe);
      delay.resolve(recipe);
    }, function() {
      console.log("RecipeLoader: " + recipe);
      delay.reject('Unable to fetch recipe ' + $route.current.params.recipeId);
    });
    return delay.promise;
  };
}]);
