(function () {
    'use strict';

    angular
        .module('photoApp')
	.controller('scotchController', ['$scope', scotchController]);
       
	// let's define the scotch controller that we call up in the about state
	function scotchController($scope) {
    		$scope.message = 'test';
		$scope.scotches = [
        		{name: 'Macallan 12', price: 50},
        		{name: 'Chivas Regal Royal Salute', price: 10000},
	        	{name: 'Glenfiddich 1937',price: 20000}
    		];
    
	}

}());





