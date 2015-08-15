(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.photos.photosListCtrl', ['$scope', '$http', '$q', 'photoApp.core.photos.photosFtr', 'photoApp.common.factory.httpGetFile', photosListCtrl]);
	
    	function photosListCtrl($scope, $http, $q, photos, httpGetFile) {
		
		$http.get('/photo/filenames').success(function (result){
			$scope.photos = result;
    		});

		var _DIRNAME = "assets/img/";

		$scope.photo = null;

		$scope.change = function(filename){
			if(filename != null){
				$http.get('/photo/description/' + filename).success(function (result){
					$scope.photo = result;
    				});	
				
				$scope.image = _DIRNAME + filename; 
				//todo: problem z kodowaniem obrazka pobieranego z serwera	
				/*$http.get('/photo/image/' + filename).success(function (result){
					//$scope.image = base64Encode(result);
					console.log($scope.image);
    				});*/	
			}
		}
		/*
		var filename = "assets/files/data.json";
		httpGetFile.getData("filename).then(function (data) {
			$scope.data = data;
			console.log(data);
		});

		$scope.combinedResult = null;
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
			$scope.combinedResult = fullResult;//.join(", ");	
		});
		*/
		/*$scope.photos = photos.getAll();
		$scope.photo = photos.getById(1);
		$scope.change = function(id){
			if(id != null){
				$scope.photo = photos.getById(id);
				console.log("change=" + $scope.photo.name);		
			}
		}*/

    	}
	

function base64Encode(str) {
    var CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var out = "", i = 0, len = str.length, c1, c2, c3;
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += CHARS.charAt(c1 >> 2);
            out += CHARS.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += CHARS.charAt(c1 >> 2);
            out += CHARS.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
            out += CHARS.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += CHARS.charAt(c1 >> 2);
        out += CHARS.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += CHARS.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += CHARS.charAt(c3 & 0x3F);
    }
    return out;
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
