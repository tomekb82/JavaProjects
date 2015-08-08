(function () {
    'use strict';

    angular
        .module('photoApp')
        .config(['$stateProvider', appConfiguration])
        .controller('photosController', ['$scope', photosController])
	.controller('photoController', ['$scope', '$stateParams', '$filter', photoController]);

	function appConfiguration($stateProvider) {
    		$stateProvider
    	
 // photos features list
    .state('photos.photo', {
        url: "/photo/:id",
        views: {
            "content@photos": {
                templateUrl: "app/modules/photos/photo.html",
                controller: 'photoController'
            }
        }
    })
   
    // single feature view
    .state('photos.photo.feature', {
        url: "/feature/:index",
        views: {
            "content@photos": {
                templateUrl: "app/modules/photos/photo-feature.html",
                controller: 'FeatureController'
            }
        }
    })
   
    // feature - edit
    .state('photos.photo.feature-edit', {
        url: "/feature-edit/:index",
        views: {
            "navbar@app": {
                template: "<b>Edit photos hoses here</b>"
            },
            "@app": {
                templateUrl: "app/modules/photos/photo-feature-edit.html",
                controller: 'FeatureEditController'
            }
        }
    })
   
    // feature - edit in MODAL
    .state('photos.photo.feature-view', {
        url: "/feature-view/:index",
        views: {
            "modal@": {
                templateUrl: "app/modules/photos/photo-modal-layout.html"
            },
            "@photos.photo.feature-view": {
                templateUrl: "app/modules/photos/photo-feature-edit.html",
                controller: 'FeatureEditController'
            }
        }
    })

    	}

    	function photosController($scope, photos) {
		$scope.photos = [
                    				{id:1, name: "AngulrJS", features: ['ready']},
                   				{id:2, name: "ReactJS", features: ['ready']},
                    				{id:3, name: "EmberJS", features: ['ready']}
                			];
    	}
	function photoController($scope, $stateParams, $filter) {
$scope.photos = [
                    				{id:1, name: "AngulrJS", features: ['ready']},
                   				{id:2, name: "ReactJS", features: ['ready']},
                    				{id:3, name: "EmberJS", features: ['ready']}
                			];
		 $scope.id = $stateParams.id;
		 
        	 $scope.photo = $filter('filter')($scope.photos, $stateParams.id);
console.log("id=" + $scope.photo.id);
		
		
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
