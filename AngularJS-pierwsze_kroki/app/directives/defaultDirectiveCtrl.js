(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.controller('defaultDirectiveCtrl', ['$scope', defaultDirectiveCtrl]);

	function defaultDirectiveCtrl($scope) {
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
			{ mountain: "Mount Everest", metres: 8850, country: 'Nepal-Chiny', execute: function (height){ $scope.result= height + ', function 1'; } },
			{ mountain: "K2", metres: 8611, country: 'Pakistan-Chiny', execute: function (height) { $scope.result = height + ', function 2'; } },
			{ mountain: "Kangczendzonga", metres: 8598, country: 'Pakistan-Chiny', execute: function (height) { $scope.result = height + ', function 3'; } },
			{ mountain: "Lhotse", metres: 8501, country: 'Nepal-Chiny', execute: function (height) { $scope.result = height + ', function 4'; } },
			{ mountain: "Makalu", metres: 8463, country: 'Nepal-Chiny', execute: function (height) { $scope.result = height + ', function 5'; } },
			{ mountain: "Cho Oyu", metres: 8201, country: 'Nepal-Chiny', execute: function (height) { $scope.result = height + ', function 6'; } }];
 			
		$scope.mountain = [];
		angular.forEach($scope.mountainsList, function (value, index) {
			$scope.mountain[index] = value.mountain;
		})

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
		// ngModelOptions
		var _age = 25;
		$scope.user = {
			age: function (newAge) {
				return angular.isDefined(newAge) ? (_age = newAge) : _age;
			}
		};
		// ngSubmit
		$scope.submit = function () {
			if ($scope.text) {
				$scope.answer = "Tekst: " + $scope.text;
			}
			else{
				$scope.answer = "Brak tekstu";
			}
		};
		// script
		$scope.leftValue = "Kliknąłeś przycisk 'left'";
		$scope.centerValue = "Kliknąłeś przycisk 'center'";
		$scope.rightValue = "Kliknąłeś przycisk 'right'";
		$scope.justifyValue = "Kliknąłeś przycisk 'justify'";
		// select
		$scope.grades = [
			'Bardzo łatwe',
			'Łatwe',	
			'Trudne',
			'Bardzo trudne'
		];
		
	}

	function appConfiguration($stateProvider) {
		$stateProvider
                	.state('directive.default', {
               	     		url: '/directive',
                    		templateUrl: 'app/directives/DefaultDirectiveView.html',
                    		controller: 'defaultDirectiveCtrl'
                	});
	}


}());
