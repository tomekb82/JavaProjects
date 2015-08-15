(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('photoApp.core.photos.photosFtr', ['$http', photosFtr]);

	function photosFtr($http){

		var _DIRNAME = "assets/img/";

		return {
			getFilenames: function () {
				return $http.get('/photo/filenames');

			},
			getDescriptionByName: function (filename) {
				return $http.get('/photo/description/' + filename);

			},
			getImageByName: function (filename) {
				return _DIRNAME + filename; 

			}/*,
			getAll: function () {
				return photos;
			},
			getById: function (id) {
				var result = null;
				angular.forEach(photos, function (m) {
					if (m.id == id) result = m;
				});
				return result;
			},
			deleteById: function (id) {
				angular.forEach(photos, function (m, i) {
					if (id == m.id) {
						photos.splice(i, 1);
					}
				});
			}*/
		}
	}



}());
