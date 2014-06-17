'use strict';

angular.module('ngdemo.controllers')
  .controller('AboutCtrl', function ($scope) {
  
	$scope.title = 'Click me to expand';
    	$scope.text = 'Hi there folks, I am the content that was hidden but is now shown.';

});
