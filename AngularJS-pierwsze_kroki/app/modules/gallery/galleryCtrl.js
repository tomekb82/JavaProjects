(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.factory('FileDataService',  ['$http', '$q', fileDataService])
	.controller('galleryCtrl', ['$scope', 'FileDataService', galleryCtrl]);

	function galleryCtrl($scope, FileDataService) {
		$scope.files = [
			{ name: 'Plik 1.', URL: 'assets/gallery/desc1.json', img: 'assets/gallery/img1.jpg'},
			{ name: 'Plik 2.', URL: 'assets/gallery/desc2.json', img: 'assets/gallery/img2.jpg'},
			{ name: 'Plik 3.', URL: 'assets/gallery/desc3.json', img: 'assets/gallery/img3.jpg'}
		];
		$scope.getFileData = function (file) {
			FileDataService.getFileData(file).then(function (result) {
				$scope.fileData = result;
				$scope.fileImage = file.img;
			}, function (result) {
				alert("Wystąpił błąd!");
			});
		
		};
	}

	function fileDataService($http) {
		var factory = {
			getFileData: function (file) {
				console.log(file);
				var result = $http({ method: 'GET', url: file.URL });
				return result;
			}
		}
		return factory;
	}

	function appConfiguration($stateProvider) {
		$stateProvider
                	.state('gallery', {
               	     		url: '/gallery',
                    		templateUrl: 'app/modules/gallery/GalleryView.html',
                    		controller: 'galleryCtrl'
                	});
	}


}());




