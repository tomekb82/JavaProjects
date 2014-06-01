'use strict';

angular.module('yeomanProjectApp')
.directive('focus', function() {
	return {
		link: function(scope, element, attrs) {
			element[0].focus();
		}
	};
});


