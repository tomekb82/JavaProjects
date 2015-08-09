(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('photos', photos);

	function photos(){
		var photos =  [
			{id:1, name: "AngulrJS", features: ['ready'], img: "A1"},
			{id:2, name: "ReactJS", features: ['ready'], img: "A2"},
			{id:3, name: "EmberJS", features: ['ready'], img: "A3"}
		];
		return {
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
			}
		}
	}



}());
