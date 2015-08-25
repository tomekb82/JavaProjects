'use strict';

    angular
        .module('jhipsterphotoApp')
        .controller('photosListCtrl', ['$scope', 'photosFtr', 'Description', photosListCtrl]);

    	function photosListCtrl($scope, photos, Description) {

            photos.getFilenames().success(function (result){
                console.log("photosListCtrl: filenames=" + result);
            	$scope.photos = result;
            });


            $scope.change = function(filename){
                if(filename != null){
                     var file = filename.split('.')[0];
                     Description.get({filename: file}, function(result) {
                        console.log("res" + result.date);
                        $scope.description = result;
                     });

            	    //photos.getDescription(file).success(function (result){
            		//    $scope.description = result;
                	//});
            		$scope.image = photos.getImageByName(filename);

            	}
            }
    	}

