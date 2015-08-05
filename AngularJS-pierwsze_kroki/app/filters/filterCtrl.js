(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.controller('filterCtrl', ['$scope', filterCtrl]);

	function filterCtrl($scope) {
		$scope.text =  "asdadsadsadsad";
		$scope.teraz = Date.now();
		$scope.uzytkownicy = [
		{
			imie:"jan", wiek:24, miasto: "Warszawa"
		},
		{
			imie:"piotr", wiek:23, miasto: "kraków"
		},
		{
			imie:"karol", wiek:45, miasto: "Lublin"
		}];

	}

	function appConfiguration($stateProvider) {
		$stateProvider
                	.state('filter', {
               	     		url: '/filter',
                    		templateUrl: 'app/filters/FilterView.html',
                    		controller: 'filterCtrl'
                	});
	}


}());
