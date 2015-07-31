(function () {
    'use strict';

     angular
	.module('app')
	.provider("oneProv", oneProv)
	.config(['$stateProvider', 'oneProvProvider', appConfiguration])
	.value("confString", "AngularJS")
	.controller('providerCtrl', ['$scope', 'oneProv', providerCtrl]);

	function providerCtrl($scope, oneProv) {
		$scope.viewTest = oneProv.viewTest();
		oneProv.printLog();
	}

	function oneProv() {
		var provider = {};
		var config = { paramOne: "jest niesamowity!" };
		provider.addConfig = function (paramOne) {
			config.paramOne = paramOne;
		};
		provider.$get = function (confString) {
			var service = {};
			service.printLog = function () {
				console.log("Log z providera: " + confString + config.paramOne);
			},
			service.viewTest = function () {
				return "Log z providera: " + confString + config.paramOne;
			}
			return service;
		}
		console.log(provider);
		return provider;
	}

	function appConfiguration($stateProvider, oneProvProvider) {
		oneProvProvider.addConfig(" nowa konfiguracja");
		$stateProvider
                	.state('provider', {
               	     		url: '/provider',
                    		templateUrl: 'app/providers/ProviderView.html',
                    		controller: 'providerCtrl'
                	});
	}


}());
