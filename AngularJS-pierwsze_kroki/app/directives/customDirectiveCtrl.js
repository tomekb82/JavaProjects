(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.directive("colorChanger", colorChanger)
	.directive("eventsEvaluation", eventsEvaluation)
	.controller('customDirectiveCtrl', ['$scope', customDirectiveCtrl]);

	function colorChanger() {
		return function (scope, element, attrs) {
			element.bind("mouseenter", function () {
				element.css("background", attrs.colorChanger);
			});
			element.bind("mouseleave", function () {
				element.css("background", "none");
			});
		}
	}

	function eventsEvaluation() {
		return {
			restrict: "E",
			scope: {	
				text: "@",
			},
			template:
				"<div>" +
				" {{text}}: <input ng-disabled='true' type='number' data-ng-model='number' class='form-control' />" +
				" <a ng-disabled='number<1' class='btn btn-default' href='#' data-ng-click='reduce()'><span class='glyphicon glyphicon-minus'></span></a> " +
				" <a ng-disabled='number>9' class='btn btn-default' href='#' data-ng-click='increase()'><span class='glyphicon glyphicon-plus'></span></a> " +
				"</div>",
			replace: true,
			transclude: false,
			controller: customDirectiveCtrl
		}
	}

	function customDirectiveCtrl($scope) {
		$scope.number = 5;
		$scope.increase = function () {
			$scope.number++;
		};
		$scope.reduce = function () {
			$scope.number--;
		};
	}

	function appConfiguration($stateProvider) {
		$stateProvider
                	.state('directive.custom', {
               	     		url: '/directive',
                    		templateUrl: 'app/directives/CustomDirectiveView.html',
                    		controller: 'customDirectiveCtrl'
                	});
	}


}());
