(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.photos.photosListCtrl', ['$scope', 'photoApp.core.photos.photosFtr', photosListCtrl]);
	
    	function photosListCtrl($scope, photos) {
		$scope.photos = photos.getAll();
		$scope.photo = photos.getById(1);
		$scope.change = function(id){
			if(id != null){
				$scope.photo = photos.getById(id);
				console.log("change=" + $scope.photo.name);		
			}
		}
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
