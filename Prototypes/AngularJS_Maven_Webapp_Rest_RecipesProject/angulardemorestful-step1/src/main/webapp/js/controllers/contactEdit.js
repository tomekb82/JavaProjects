'use strict';

angular.module('ngdemo.controllers')
  .controller('ContactEditCtrl', ['$scope', '$location', 'user', function ($scope, $location, user) {

	$scope.user = user;
	$scope.delete = true;

	/* METHODS: POST */
	$scope.save = function() {
    	 	$scope.user.$save(function(user) {
			$location.path('/contactList');
    		});
  	};

        $scope.remove = function() {
    		$scope.user.$delete();
    		$location.path('/contactList');
  	};
}]);





