(function () {
    'use strict';

    angular
        .module('app')
        .config(['$stateProvider', appConfiguration])
        .controller('nestedViewController', ['$scope', nestedViewController])
	.controller('emptyViewController', ['$scope', emptyViewController]);

    function appConfiguration($stateProvider) {
        $stateProvider
                .state('home.nestedView', {                  
                    templateUrl: 'app/modules/nestedView/NestedView.html',
                    controller: 'nestedViewController'
                })
                .state('home.emptyView', {                  
                    templateUrl: 'app/modules/nestedView/EmptyView.html',
                    controller: 'emptyViewController'
                });
    }

    function nestedViewController($scope) {
        console.log('nestedView loaded');
    }
	
    function emptyViewController($scope) {
        console.log('emptyView loaded');
    }
}());
