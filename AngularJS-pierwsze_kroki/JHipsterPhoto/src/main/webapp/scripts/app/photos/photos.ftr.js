'use strict';

angular
	.module('jhipsterphotoApp')
	.factory('photosFtr', ['$http', photosFtr]);

	function photosFtr($http){

		var _DIRNAME = "resources/images/";

		return {
			getFilenames: function () {
				return $http.get('api/photos/filenames');

			},
			getDescription: function (filename) {
			    console.log("photosFtr: getDescription");
				return $http.get('api/photos/description/' + filename);

			},
			getImageByName: function (filename) {
				return _DIRNAME + filename;

			}
		}
	}
