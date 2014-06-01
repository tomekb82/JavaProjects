'use strict';

angular.module('yeomanProjectApp')
  .controller('ContactNewCtrl', ['$scope', '$location', 'User', function ($scope, $location, User) {

	$scope.user = new User();
	$scope.delete = false;

	/* METHODS: POST */
	$scope.save = function() {
		console.log('ContactNewCtrl save()');
    	 	$scope.user.$save(function(user) {
			$location.path('/contactList');
    		});
  	};

}]);





