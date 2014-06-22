'use strict';

var directive = angular.module('angularspring.directives', []);

directive.directive('ngbkFocus', function() {
	return {
		link: function(scope, element, attrs) {
			element[0].focus();
		}
	};
});


