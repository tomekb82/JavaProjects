angular.module('admin-charts', [
  'resources.expenses',
  'resources.users',
  'services.crud',
  'security.authorization'
])

.config(['crudRouteProvider', 'securityAuthorizationProvider', function (crudRouteProvider, securityAuthorizationProvider) {

  var getAllUsers = ['Expenses', 'Users', '$route', function(Expenses, Users, $route){
    return Users.all();
  }];

  crudRouteProvider.routesFor('Charts', 'admin')
    .whenList({
      expenses: ['Expenses', function(Expenses) { return Expenses.all(); }],
      adminUser: securityAuthorizationProvider.requireAdminUser
    });

}])

.controller('ChartsListCtrl', ['$scope', 'crudListMethods', 'expenses', function($scope, crudListMethods, expenses) {
  $scope.expenses = expenses;

  console.log("Jestem w kontrolerze chartów");

  $scope.isAdminOpen=false;
  $scope.line = 0;
  $scope.bar = 1;
  $scope.pieUser = 0;
  $scope.pieCat = 0;
  $scope.isAdmin = function() {
    return $scope.isAdminOpen==true;
  };
  $scope.isLine = function() {
    return $scope.line==1;
  };
  $scope.isBar = function() {
    return $scope.bar==1;
  };
  $scope.isPieUser = function() {
    return $scope.pieUser==1;
  };
  $scope.isPieCat = function() {
    return $scope.pieCat==1;
  };


  $scope.setPieUser = function() {
	$scope.isAdminOpen = false;
	$scope.pieUser = !$scope.pieUser;
  }	
  $scope.setPieCat = function() {
	$scope.isAdminOpen = false;
	$scope.pieCat = !$scope.pieCat;
  }	

  $scope.all_amount=0;
  for (var i=0; i<$scope.expenses.length; i++){
  	$scope.all_amount += parseInt($scope.expenses[i].value, 10);
  }

  angular.extend($scope, crudListMethods('/admin/charts'));
}]);
