(function () {
    'use strict';

    angular
        .module('app')
        .config(['$stateProvider', formConf])
        .controller('formController', ['$scope', formController]);

    function formConf($stateProvider) {
        $stateProvider
                .state('forms', {
                    url: '/forms', {',
                    templateUrl: 'app/modules/forms/FormView.html',
                    controller: 'formController'
                });
    }

    function formController($scope) {
		var date = new Date();
		$scope.contactForm = {
			date: date
		};
		$scope.update = function (contact) {
			$scope.contactForm = angular.copy(contact);
		};

    }

}());
