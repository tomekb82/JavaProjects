
angular.module('charts', [], ['$routeProvider', function($routeProvider){

  $routeProvider
    .when('/charts/globalChart', {
    	templateUrl:'charts/chart1.tpl.html',
    	controller:'ChartsListCtrl',
    })
    .when('/charts/timeChart', {
    	templateUrl:'charts/chart2.tpl.html',
    	controller:'ChartsListCtrl',
	resolve:{
      		expenses:['Expenses', function(Expenses){
        		return Expenses.all();
      		}]
    	}
    })
    .when('/charts/compUsers', {
    	templateUrl:'charts/chart3.tpl.html',
    	controller:'ChartsListCtrl',
    })
    .when('/charts/compTypes', {
    	templateUrl:'charts/chart4.tpl.html',
    	controller:'ChartsListCtrl',
    });

}]);

angular.module('charts').controller('ChartsListCtrl','expenses' ['$scope', function($scope, expenses){

   $scope.expenses = expenses;
   $scope.index = 0;
   console.log("EXP=", $scope.expenses);


}]);




