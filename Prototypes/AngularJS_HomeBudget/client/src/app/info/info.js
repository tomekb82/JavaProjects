angular.module('info', [], ['$routeProvider', function($routeProvider){

	$routeProvider
		.when('/info', {
    			templateUrl:'info/list.tpl.html',
    			controller:'ExpensesInfoListCtrl',
    			resolve:{
      				expenses:['Expenses', function(Expenses){
        				return Expenses.all();
     		 		}]
    			}
  		})
		.when('/info/users', {
    			templateUrl:'info/users.tpl.html',
    			controller:'ExpensesInfoListCtrl'
  		})
		.when('/info/expenses', {
    			templateUrl:'info/expenses.tpl.html',
    			controller:'ExpensesInfoListCtrl'
  		})
		.when('/info/reports', {
    			templateUrl:'info/reports.tpl.html',
    			controller:'ExpensesInfoListCtrl'
  		});
}]);

angular.module('info').controller('ExpensesInfoListCtrl', ['$scope', 'expenses', function($scope, expenses){
  $scope.expenses = expenses;


  $scope.isAuthenticated = true;//security.isAuthenticated;

 // console.log('AUTHiii= ', $scope.isAuthenticated);

}]);




