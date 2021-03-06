﻿(function () {
    'use strict';

    angular
        .module('app')
        .config(['$stateProvider', appConfiguration])
        .controller('homeController', ['$scope', homeController]);

    function appConfiguration($stateProvider) {
        $stateProvider
                .state('home', {
                    url: '/home',
                    templateUrl: 'app/modules/home/HomeView.html',
                    controller: 'homeController'
                });
    }

    function homeController($scope) {
		$scope.message = "Witaj na mojej stronie !";
		$scope.value = false;
		$scope.stan = "home.emptyView";//"home.nestedView";
		$scope.change = function(){
			$scope.value = !$scope.value;
			$scope.stan = $scope.value ? "home.nestedView" : "home.emptyView";
			console.log("A:" + $scope.stan);
		};
    }

}());
