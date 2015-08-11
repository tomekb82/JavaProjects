(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.photos.photosListCtrl', ['$scope', '$http', '$q', 'photoApp.core.photos.photosFtr', 'photoApp.common.factory.httpGetFile',  photosListCtrl]);
	
    	function photosListCtrl($scope, $http, $q, photos, httpGetFile) {
		var filename = "assets/files/data.json";
		httpGetFile.getData(filename).then(function (data) {
			$scope.data = data;
		});

		var data1 = $http.get('assets/files/data1.json'),
			data2 = $http.get('assets/files/data2.json'),
			data3 = $http.get('assets/files/data3.json');
		$q.all([data1, data2, data3]).then(function (result) {
			var full = [];
			angular.forEach(result, function (response) {
				full.push(response.data);
			});
			return full;
		}).then(function (fullResult) {
			$scope.combinedResult = fullResult.join(", ");
		});

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
