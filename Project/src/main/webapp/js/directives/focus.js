'use strict';

angular.module('ngdemo.directives')
.directive('focus', function() {
	return {
		link: function(scope, element, attrs) {
			element[0].focus();
		}
	};
});


