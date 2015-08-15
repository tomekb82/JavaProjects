(function () {
    'use strict';

    angular
        .module('photoApp')
	.controller('photoApp.core.about.aboutListCtrl', ['$scope', aboutListCtrl]);
       
	// let's define the scotch controller that we call up in the about state
	function aboutListCtrl($scope) {
    		$scope.message = 'test';
		$scope.scotches = [
        		{name: 'Macallan 12', price: 50},
        		{name: 'Chivas Regal Royal Salute', price: 10000},
	        	{name: 'Glenfiddich 1937',price: 20000}
    		];
    
	}

}());







