angular.module('admin-expenses', [
  'resources.expenses',
  'resources.users',
  'services.crud',
  'security.authorization'
])

.config(['crudRouteProvider', 'securityAuthorizationProvider', function (crudRouteProvider, securityAuthorizationProvider) {

  var getAllUsers = ['Expenses', 'Users', '$route', function(Expenses, Users, $route){
    return Users.all();
  }];

  crudRouteProvider.routesFor('Expenses', 'admin')
    .whenList({
      expenses: ['Expenses', function(Expenses) { return Expenses.all(); }],
      adminUser: securityAuthorizationProvider.requireAdminUser
    })
    .whenNew({
      expense: ['Expenses', function(Expenses) { return new Expenses(); }],
      users: getAllUsers,
      aims: ['$http', function($http) { return $http.get('/static/aims.json'); }],
      adminUser: securityAuthorizationProvider.requireAdminUser
    })
    .whenEdit({
      expense: ['Expenses', 'Users', '$route', function(Expenses, Users, $route) { return Expenses.getById($route.current.params.itemId); }],
      users: getAllUsers,
      aims: ['$http', function($http) { return $http.get('/static/aims.json'); }],
      adminUser: securityAuthorizationProvider.requireAdminUser
    });
}])


.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
})

.filter('isType', function() {
  return function(input, type) {
    var out = [];
      for (var i = 0; i < input.length; i++){
	console.log(input[i]);
	if(type.all_check==true){
        	out.push(input[i]);
	}else{
		if(type.global_check==true){
       	   		if(input[i].aim.type == 'global'){
              			out.push(input[i]);
	  		}
		}
		if(type.life_check==true){
          		if(input[i].aim.type == 'life'){
            			  out.push(input[i]);
	  		}
		}
		if(type.private_check==true){
       		   	if(input[i].aim.type == 'private'){
              			out.push(input[i]);
	  		}
		}
      	}
      }      
    return out;
  };
})
/*
.directive('myDownload', function ($compile) {
    return {
        restrict:'E',
        scope:{ getUrlData:'&getData'},
        link:function (scope, elm, attrs) {
            var url = URL.createObjectURL(scope.getUrlData());
            elm.append($compile(
                '<a class="btn btn-primary" download="expenses.json"' +
                    'href="' + url + '">' +
                    'Zapisz do pliku' +
                    '</a>'
            )(scope));
        }
    };
})
*/
.controller('ExpensesListCtrl', ['Expenses', '$scope', 'crudListMethods', 'expenses', '$filter', function(Expenses, $scope, crudListMethods, expenses, $filter) {
  $scope.expenses = expenses;
 
  var json = JSON.stringify($scope.expenses);
  $scope.getBlob = function(){
  	return new Blob([json], {type: "application/json"});
  }
 
  // Checkbox filters
  $scope.type =[];
  $scope.type.all_check = true; 
  $scope.expenses_filtered = $filter('isType')($scope.expenses, $scope.type);
 
   // Name filters
  $scope.myFilter = 'uppercase';
  $scope.myFilterFn = function(value) {
    //console.log(value);
    var filter = $filter($scope.myFilter);
    //console.log(filter);
    return filter(value);
  };

  $scope.all_amount=0;
  for (var i=0; i <$filter('isType')($scope.expenses, $scope.type).length; i++){
  	$scope.all_amount += parseInt($filter('isType')($scope.expenses, $scope.type)[i].value, 10);
  }
  
  $scope.currentPage = 0;
  $scope.pageSize = 10;
  $scope.numberOfPages=function(){
 	return Math.ceil($filter('isType')($scope.expenses, $scope.type).length/$scope.pageSize);                
  } 
  $scope.change=function(){
      $scope.all_amount=0;
  	for (var i=0; i <$filter('isType')($scope.expenses, $scope.type).length; i++){
        	$scope.all_amount += parseInt($filter('isType')($scope.expenses, $scope.type)[i].value, 10);
	}
	return $scope.currentPage = 0;
  }
  $scope.isNextPage=function(){
	return $scope.currentPage >= $filter('isType')($scope.expenses, $scope.type).length/$scope.pageSize - 1
  }

  angular.extend($scope, crudListMethods('/admin/expenses'));
}])

.controller('ExpensesEditCtrl', ['$scope', '$location', 'i18nNotifications', 'users', 'expense', 'aims', function($scope, $location, i18nNotifications, users, expense, aims) {


  $scope.expense = expense;
  $scope.users = users;
  $scope.date = '';

  //$scope.aims = aims.data;
  //console.log($scope.aims);

  $scope.aims = [{name: 'jedzenie',    type: 'life'},
		 {name: 'rachunki',    type: 'life'},
		 {name: 'ubrania',     type: 'private'},
   		 {name: 'paliwo/auto', type: 'private'},
		 {name: 'uroda',       type: 'private'},
		 {name: 'kosmetyki',   type: 'private'},
		 {name: 'kino',        type: 'entertainment'},
  		 {name: 'taniec',      type: 'entertainment'},
		 {name: 'restauracje', type: 'entertainment'},
		 {name: 'wycieczki',   type: 'entertainment'},
		 {name: 'książki'  ,   type: 'global'},
		 {name: 'szkolenia'  , type: 'global'}];
  

  $scope.getAim = function(aim) {
      return aim.name;
  };

  var timeNow = new Date();
  $scope.expense.time = timeNow;

  $scope.getFullName = function(user) {
      return user.firstName + ' ' + user.lastName;
  };
  
  $scope.onSave = function(expense) {
    i18nNotifications.pushForNextRoute('crud.expense.save.success', 'success', {id : expense.$id()});
    $location.path('/admin/expenses');

  };

  $scope.onError = function() {
    i18nNotifications.pushForCurrentRoute('crud.expense.save.error', 'error');
  };

}])

.controller('TeamMembersController', ['$scope', function($scope) {
  $scope.expense.teamMembers = $scope.expense.teamMembers || [];

  //prepare users lookup, just keep references for easier lookup
  $scope.usersLookup = {};
  angular.forEach($scope.users, function(value, key) {
    $scope.usersLookup[value.$id()] = value;
  });

  $scope.productOwnerCandidates = function() {
    return $scope.users.filter(function(user) {
      return $scope.usersLookup[user.$id()] && $scope.expense.canActAsProductOwner(user.$id());
    });
  };

  $scope.scrumMasterCandidates = function() {
    return $scope.users.filter(function(user) {
      return $scope.usersLookup[user.$id()] && $scope.expense.canActAsScrumMaster(user.$id());
    });
  };

  $scope.teamMemberCandidates = function() {
    return $scope.users.filter(function(user) {
      return $scope.usersLookup[user.$id()] && $scope.expense.canActAsDevTeamMember(user.$id()) && !$scope.expense.isDevTeamMember(user.$id());
    });
  };

  $scope.selTeamMember = undefined;

  $scope.addTeamMember = function() {
    if($scope.selTeamMember) {
      $scope.expense.teamMembers.push($scope.selTeamMember);
      $scope.selTeamMember = undefined;
    }
  };

  $scope.removeTeamMember = function(teamMember) {
    var idx = $scope.expense.teamMembers.indexOf(teamMember);
    if(idx >= 0) {
      $scope.expense.teamMembers.splice(idx, 1);
    }
    // If we have removed the team member that is currently selected then clear this object
    if($scope.selTeamMember === teamMember) {
      $scope.selTeamMember = undefined;
    }
  };
}]);
