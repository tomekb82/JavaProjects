(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.photos.photosFormCtrl', ['$scope', photosFormCtrl]);
	
    	function photosFormCtrl($scope) {
		var date = new Date();
		$scope.contactForm = {
			date: date
		};
		$scope.update = function (contact) {
			$scope.contactForm = angular.copy(contact);
		};

    }

}());
