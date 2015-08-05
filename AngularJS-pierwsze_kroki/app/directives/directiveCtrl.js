(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.controller('directiveCtrl', ['$scope', directiveCtrl]);

	function directiveCtrl($scope) {
	}

	function appConfiguration($stateProvider) {
		$stateProvider
                	.state('directive', {
               	     		url: '/directive',
                    		templateUrl: 'app/directives/DirectiveView.html',
                    		controller: 'directiveCtrl'
                	});
	}


}());
