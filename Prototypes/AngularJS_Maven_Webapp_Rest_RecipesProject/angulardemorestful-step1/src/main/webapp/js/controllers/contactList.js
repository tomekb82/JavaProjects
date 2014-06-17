'use strict';

angular.module('ngdemo.controllers')
  .controller('ContactListCtrl', ['$scope', '$location', 'users', function ($scope, $location, users) {

	$scope.users = users;

	 $scope.selectRowTable = function(row) {
                   $scope.selectedRow = row;
         };

}]);





