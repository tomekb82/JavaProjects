(function () {
    'use strict';

     angular
	.module('jhipsterphotoApp')
	.factory('httpGetFile', ['$http', httpGetFile])

	function httpGetFile($http){
		return {
			getData: function (filename) {
				return $http.get(filename)
					.then(function (result) {
						return result.data;
					});
			}
		}
	}


}());



