﻿(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.factory('FileDataService',  ['$http', '$q', fileDataService])
	.controller('galleryDetailsCtrl', ['$scope', 'FileDataService', galleryDetailsCtrl])
	.controller('galleryDetailsCtrl2', ['$scope', '$http', '$stateParams', 'FileDataService', galleryDetailsCtrl2]);

	function galleryDetailsCtrl2($scope, $http, $stateParams, FileDataService) {

		$scope.files = [
			{ name: 'Plik 1.', URL: 'assets/gallery/desc1.json', img: 'assets/gallery/img1.jpg'},
			{ name: 'Plik 2.', URL: 'assets/gallery/desc2.json', img: 'assets/gallery/img2.jpg'},
			{ name: 'Plik 3.', URL: 'assets/gallery/desc3.json', img: 'assets/gallery/img3.jpg'}
		];
		$scope.savedFile = $scope.files[$stateParams.id];
		$scope.fileImage = $scope.savedFile.img;
		$http({ method: 'GET', url: $scope.savedFile.URL }).then(function (result) {
				$scope.fileData = result;
			}, function (result) {
				alert("Wystąpił błąd!");
			});
		
		$scope.getFileData = function (file) {
			if(file != null){
			FileDataService.getFileData(file).then(function (result) {
				$scope.fileData = result;
				//$scope.fileImage = file.img;
			}, function (result) {
				alert("Wystąpił błąd!");
			});
			}
		
		};
	}

	function galleryDetailsCtrl($scope, FileDataService) {

		$scope.files = [
			{ name: 'Plik 1.', URL: 'assets/gallery/desc1.json', img: 'assets/gallery/img1.jpg'},
			{ name: 'Plik 2.', URL: 'assets/gallery/desc2.json', img: 'assets/gallery/img2.jpg'},
			{ name: 'Plik 3.', URL: 'assets/gallery/desc3.json', img: 'assets/gallery/img3.jpg'}
		];

		//$scope.getFileData($scope.savedFile);
		$scope.getFileData = function (file) {
			if(file != null){
			FileDataService.getFileData(file).then(function (result) {
				$scope.fileData = result;
				$scope.fileImage = file.img;
			}, function (result) {
				alert("Wystąpił błąd!");
			});
			}
		
		};
		$scope.opinions = [];
		$scope.addOpinion = function(){
			$scope.opinions.push($scope.formData.newOpinion);
			$scope.formData.newOpinion = '';
		}
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
            .state('gallery.details', {
                url: '/gallery',
                views: {
                    '': { templateUrl: 'app/modules/gallery/GalleryDetailsView.html',controller: 'galleryDetailsCtrl' },
                    'viewOne@gallery.details': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView1.html', controller: 'galleryDetailsCtrl'
                    },
                    'viewTwo@gallery.details': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView2.html',controller: 'galleryDetailsCtrl'
                    },
                    'viewThree@gallery.details': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView3.html',controller: 'galleryDetailsCtrl'
                    }
                }
            })
	    .state('galleryDetails', {
             	url: '/gallery/:id',
		views: {
                    '': { templateUrl: 'app/modules/gallery/GalleryDetailsView.html',controller: 'galleryDetailsCtrl2' },
                    'viewOne@galleryDetails': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView1.html', controller: 'galleryDetailsCtrl2'
                    },
                    'viewTwo@galleryDetails': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView2.html',controller: 'galleryDetailsCtrl'
                    },
                    'viewThree@galleryDetails': {
                        templateUrl: 'app/modules/gallery/GalleryDetailsView3.html',controller: 'galleryDetailsCtrl2'
                    }
                }
             });
    }



}());




