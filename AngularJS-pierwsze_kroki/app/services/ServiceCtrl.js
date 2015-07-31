var app = angular.module('app', []);
app.value("stringValue", "AngularJS");

function OneService() {
	this.printLog = function () {
		console.log("Log z serwisu - AngularJS");
	},
	this.newValue = function () {
		return "Nowa wartość z serwisu!";
	}
};

app.service("oneService", OneService);

app.service("twoService", function () {
	this.printLog = function () {
		console.log("Log z drugiego serwisu - AngularJS");
	},
	this.newValue = function () {
		return "Nowa wartość z drugiego serwisu!";
	}
});

app.controller('serviceCtrl', function ($scope, oneService, twoService) {
oneService.printLog();
$scope.newValue = oneService.newValue();
twoService.printLog();
$scope.newValue2 = twoService.newValue();
});
