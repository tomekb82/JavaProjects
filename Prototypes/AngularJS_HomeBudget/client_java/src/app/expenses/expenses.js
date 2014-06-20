angular.module('expenses', ['resources.expenses', 'productbacklog', 'sprints', 'security.authorization'])

.config(['$routeProvider', 'securityAuthorizationProvider', function ($routeProvider, securityAuthorizationProvider) {
  $routeProvider.when('/expenses', {
    templateUrl:'expenses/expenses-list.tpl.html',
    controller:'ExpensesViewCtrl',
    resolve:{
      currentUser: ['security', function(security){
	return security.requestCurrentUser();
      }],
      expenses:['Expenses', 'security', function (Expenses, security) {
        //TODO: fetch only for the current user
	 return security.requestCurrentUser().then(function(response) { 
		var user = response.firstName + " " + response.lastName;
                return Expenses.query({'user': user});
	 });	
      }],
      authenticatedUser: securityAuthorizationProvider.requireAuthenticatedUser
    }
  });
}])

.factory('expensesFactory', function () {

   var currentUserFactory = '';
   var factory = {};
  
   factory.getCurrentUser = function() {
  	return currentUserFactory; 
   };

   factory.postCurrentUser = function(user) {
  	currentUserFactory = user; 
   };
   
   return factory;
})

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

.controller('ExpensesViewCtrl', ['Expenses', '$scope', '$location', 'expenses', '$http','expensesFactory', 'currentUser', function (Expenses, $scope, $location, expenses, $http, expensesFactory, currentUser) {

  	$scope.expenses = expenses;

       	var json = JSON.stringify($scope.expenses);
    	$scope.getBlob = function(){
        	return new Blob([json], {type: "application/json"});
   	}
 
  	$scope.currentUser = currentUser;

	$scope.all_amount=0;
	for (var i=0; i<$scope.expenses.length; i++){
		$scope.all_amount += parseInt($scope.expenses[i].value, 10);
	} 
	expensesFactory.postCurrentUser($scope.currentUser);



      	$.getJSON('https://api.mongolab.com/api/1/databases/heroku_app18138245/collections/expenses?apiKey=-Z6EPVudg0zGJlkg-WgJmRWNm0gZUwid', function(data) {
	var all; var life; var global; var private; var others;
	var dataPieCat = [];
	var i=0; var found = 0;	

    	for(var i=0; i<data.length; i++){
		if(i==0){
			all = 0;
                	private = 0;
           	     	global = 0;
               	 	life = 0;
                	others = 0;
		}
		
		if(data[i].user ==  (currentUser.firstName + ' ' + currentUser.lastName)) {
			all += data[i].value;
			if(data[i].aim.type == "private"){
				private += data[i].value;
			}else if(data[i].aim.type == "life"){
				life += data[i].value;
			}else if(data[i].aim.type == "global"){
				global += data[i].value;
			}else{
			    	others += data[i].value;
			}	
		}
        }

	dataPieCat.push(['Global',  ((global/all)*100)]);
	dataPieCat.push(['Private',  ((private/all)*100)]);
	dataPieCat.push(['Life',  ((life/all)*100)]);
	dataPieCat.push(['Other',  ((others/all)*100)]);

        $('#pieCat').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Procentowe wydatki'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: dataPieCat
        }]
    });

    });
  
  $scope.viewProject = function (expenseId) {
    $location.path('/expenses/'+expenseId);
  };

  $scope.manageBacklog = function (expenseId) {
    $location.path('/expenses/'+expenseId+'/productbacklog');
  };

  $scope.manageSprints = function (expenseId) {
    $location.path('/expenses/'+expenseId+'/sprints');
  };
}]);
