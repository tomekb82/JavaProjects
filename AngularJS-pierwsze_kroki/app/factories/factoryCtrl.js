var app = angular.module('app', []);
app.value("stringValue", "AngularJS");
app.factory("oneFactory", ['stringValue', function (stringValue) {
return "Wartość z fabryki + wartość z value: " + stringValue;
}]);
app.controller('defaultCtrl', ['$scope', 'oneFactory',
function ($scope, oneFactory) {
// dostęp na poziomie kontrolera
console.log(oneFactory);
// przypisujemy do scope, by uzyskać widoczność w widoku
$scope.oneFactory = oneFactory;
}]);
