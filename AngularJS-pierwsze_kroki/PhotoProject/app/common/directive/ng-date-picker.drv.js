(function () {
    'use strict';

     angular
	.module('photoApp')
	.directive('ngDatePicker', ngDatePicker);
	
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



}());
