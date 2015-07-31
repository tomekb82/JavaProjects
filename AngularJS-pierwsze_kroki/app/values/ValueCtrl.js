var app = angular.module('app', []);
app.value("numberValue", 100);
app.value("stringValue", "AngularJS");
app.value("objectValue", { v1: 123, v2: "ABCD", v3: { "v31": "ABCD" } });
app.controller('valueCtrl',	['$scope', 'objectValue', 'stringValue', 'numberValue',
	function ($scope, objectValue, stringValue, numberValue) {
		// dostęp na poziomie kontrolera
		console.log(objectValue);
		console.log(stringValue);
		console.log(numberValue);
		// przypisujemy do scope, by uzyskać widoczność w widoku
		$scope.objectValue = objectValue;
		$scope.stringValue = stringValue;
		$scope.numberValue = numberValue;
	}]);
