﻿(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.controller('directiveCtrl', ['$scope', directiveCtrl]);

	function directiveCtrl($scope) {
		// dyrektywa a
		$scope.deleteElement = function () {
			console.log('Element usunięto!');
		}
		// dyrektywa ngBind
		$scope.test = '123';
		$scope.focus = false;
		$scope.blur = false;
		// ngChange
		$scope.change = function () {
			console.log($scope.testModel);
		};
		//ngClass, ngRepeat, ngClick
		$scope.sorting = '-metres';
		$scope.result = null;
		$scope.mountainsList = [
			{ mountain: "Mount Everest", metres: 8850, execute: function (height){ $scope.result= height + ', function 1'; } },
			{ mountain: "K2", metres: 8611, execute: function (height) { $scope.result = height + ', function 2'; } },
			{ mountain: "Kangczendzonga", metres: 8598, execute: function (height) { $scope.result = height + ', function 3'; } },
			{ mountain: "Lhotse", metres: 8501, execute: function (height) { $scope.result = height + ', function 4'; } },
			{ mountain: "Makalu", metres: 8463, execute: function (height) { $scope.result = height + ', function 5'; } },
			{ mountain: "Cho Oyu", metres: 8201, execute: function (height) { $scope.result = height + ', function 6'; } }];
		var drive = function (source, target) {
			var t = $scope.mountainsList[target];
			$scope.mountainsList[target] = $scope.mountainsList[source];
			$scope.mountainsList[source] = t;
		};
		$scope.up = function (index) {
			drive(index, index - 1);
		};
		$scope.down = function (index) {
			drive(index, index + 1);
		};
		$scope.saveChanges = function (index, mountain, metres) {
			$scope.mountainsList[index]= { 'mountain': mountain, 'metres':metres };
		};
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
