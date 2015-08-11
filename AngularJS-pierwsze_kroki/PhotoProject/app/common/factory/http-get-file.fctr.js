(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('photoApp.common.factory.httpGetFile', ['$http', httpGetFile])
	
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



