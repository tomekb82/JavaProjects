
angular.module('contact', [], ['$routeProvider', function($routeProvider){

  $routeProvider.when('/contact', {
    templateUrl:'contact/list.tpl.html',
    controller:'ExpensesInfoListCtrl',
    resolve:{
      expenses:['Expenses', function(Expenses){
        return Expenses.all();
      }]
    }
  });
}]);

angular.module('contact').controller('ExpensesInfoListCtrl', ['$scope', 'expenses', function($scope, expenses){
  $scope.expenses = expenses;


}]);




