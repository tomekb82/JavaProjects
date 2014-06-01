'use strict';

angular.module('yeomanProjectApp')
.factory('RecipeLoader', ['Recipe', '$route', '$q',
    function(Recipe, $route, $q) {
  return function() {
    var delay = $q.defer();
    Recipe.get({id: $route.current.params.recipeId}, function(recipe) {
      delay.resolve(recipe);
    }, function() {
      delay.reject('Unable to fetch recipe ' + $route.current.params.recipeId);
    });
    return delay.promise;
  };
}]);
