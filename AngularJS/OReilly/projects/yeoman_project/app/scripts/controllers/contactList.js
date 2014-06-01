'use strict';

angular.module('yeomanProjectApp')
  .controller('ContactListCtrl', ['$scope', '$location', 'users', function ($scope, $location, users) {

	$scope.users = users;

	 $scope.selectRowTable = function(row) {
                   $scope.selectedRow = row;
         };

}]);





