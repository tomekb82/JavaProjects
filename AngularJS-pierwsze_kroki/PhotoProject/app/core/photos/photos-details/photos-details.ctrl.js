(function () {
    'use strict';

    angular
        .module('photoApp')
	.controller('photoApp.core.photos.photosDetailsCtrl', ['$scope', '$stateParams', '$location', 'photoApp.core.photos.photosFtr', photosDetailsCtrl]);

	function photosDetailsCtrl($scope, $stateParams, $location, photos) {		
		//$scope.photos = photos.getAll();
		//$scope.id = $stateParams.id;
		$scope.photo = photos.getById($stateParams.id);
		$scope.delete = function (id) {
			photos.deleteById(id);
			$location.path('/photos')
		};	
    	}

}());
