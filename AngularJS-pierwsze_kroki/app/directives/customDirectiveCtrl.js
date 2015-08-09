(function () {
    'use strict';

     angular
	.module('app')
	.config(['$stateProvider', appConfiguration])
	.directive("customDirective", customDirective)
	.directive('transcludeDirective', transcludeDirective)
	.directive('requireDirective', requireDirective)
	.directive("colorChanger", colorChanger)
	.directive("eventsEvaluation", eventsEvaluation)
	.directive('ngDatePicker', ngDatePicker)
	.controller('customDirectiveCtrl', ['$scope', customDirectiveCtrl]);

	function customDirective(){
    		return {
            		restrict: 'EA',
            		template: '<div>Dyrektywa szyta na miarę!</div>'
            	}
	}


	function transcludeDirective(){
    		return {
            		restrict: 'E',
            		transclude: true,
            		scope: { name: '@'}, // jednostronne
            		template: '<div>' +
                        	'<div>{{name}}</div><br>' +
                        	'<div ng-transclude></div>' +
                        	'</div>'
            	}
	}


	function requireDirective(){
    		return {
        	    	restrict: 'A',
            		require: '^ngModel',
            		scope: { ngModel: '='}, // dwustronne
            		template: '<div><h4>Text: {{ngModel}} </h4></div>'
            	}
	}

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

	function ngDatePicker(){
		return {
			restrict: 'A',
			require: 'ngModel',
			link: function (scope, element, attrs, ctrl) {
				element.datepicker({
					changeYear: true,
					changeMonth: true,
					showWeek: true,
					firstDay: 1,
					dayNames: "pl",
					dateFormat: 'dd/m/yy',
					onSelect: function (date) {
						ctrl.$setViewValue(date);
						scope.$apply();
					}
				});
			}
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
