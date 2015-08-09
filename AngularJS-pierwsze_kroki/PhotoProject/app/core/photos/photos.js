(function () {
    'use strict';

    angular
        .module('photoApp')
        .config(['$stateProvider', appConfiguration])
        .controller('photosController', ['$scope', 'photos', photosController])
	.controller('photoController', ['$scope', '$stateParams', '$location', 'photos', photoController]);

	function appConfiguration($stateProvider) {
    		
    	}

    	function photosController($scope, photos) {
		$scope.photos = photos.getAll();
		$scope.photo = photos.getById(1);
		$scope.change = function(id){
			if(id != null){
				$scope.photo = photos.getById(id);
				console.log("change=" + $scope.photo.name);		
			}
		}
    	}
	function photoController($scope, $stateParams, $location, photos) {		
		//$scope.photos = photos.getAll();
		//$scope.id = $stateParams.id;
		$scope.photo = photos.getById($stateParams.id);
console.log("FFFFF=" + $scope.photo);
		$scope.delete = function (id) {
			photos.deleteById(id);
			$location.path('/photos')
		};	
    	}

  function FeatureListController($scope, $stateParams, photos){
        $scope.id = $stateParams.id;
        $scope.photo = $filter('filter')(photos, $stateParams.id);
    }
    function FeatureController($scope, $stateParams, photos){
        $scope.id = stateParams.id;
        $scope.photo = $filter('filter')(photos, $stateParams.id);
        if($scope.photo){
            $scope.feature = $scope.photo.features[stateParams.id];
        }
    }
    function FeatureEditController($scope, $stateParams, photos){
        $scope.id = $stateParams.id;
        $scope.photo = $filter('filter')(photos, $stateParams.id);
        if($scope.photo){
            $scope.index = $stateParams.index;
        }
    }

}());
