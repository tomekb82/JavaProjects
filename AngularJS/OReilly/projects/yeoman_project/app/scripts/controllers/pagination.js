'use strict';

angular.module('yeomanProjectApp')
.controller('PaginationCtrl', ['$scope', '$http', 'paginator',
  function($scope, $http, paginator) {
    $scope.query = 'Testing';
    $scope.limit = 100;
    var fetchFunction = function(offset, limit, callback) {
      console.log("PaginationCtrl: fetchFunction()");
      $http.get('/search', {params: {query: $scope.query, offset: offset, limit: limit}}).success(callback);
    };

    $scope.searchPaginator = paginator(fetchFunction, 10);
}]);
