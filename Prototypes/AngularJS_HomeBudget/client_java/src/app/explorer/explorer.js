
angular.module('explorer', ['ngResource'], ['$routeProvider', function($routeProvider){

  $routeProvider.when('/explorer', {
    templateUrl:'explorer/list.tpl.html',
    controller:'ExplorerCtrl'
  });
}]);


angular.module('explorer').factory('jsonFactory', function($http, $resource) { 

	var jsonFactory= {     
        	getHttp: function() {    
                	var url = '/static/expenses.json';              
                        var promise = $http.get(url).then(function (response) {
                        	return response.data;
                        });     
                        return promise;
               	},
                getResource: function() {    
                	var url = '/static/expenses.json';           
                    	return $resource(url).query();
                }

        };
        return jsonFactory;
});

angular.module('explorer').controller('ExplorerCtrl', ['$scope','$http','jsonFactory', function($scope, $http, jsonFactory){

  $scope.expenses='';

 	    jsonFactory.getHttp().then(function(data){
                    $scope.getInnerHttp = data;
                    console.log("getInnerHttp = ", $scope.getInnerHttp);
             });
                   
            var getRes = jsonFactory.getResource();
            console.log("getRES = ", getRes);   

  $scope.all_amount=0;
  for (var i=0; i <$scope.expenses.length; i++){
        $scope.all_amount += parseInt($scope.expenses[i].value, 10);
  }

  $scope.currentPage = 0;
  $scope.pageSize = 10;
  $scope.numberOfPages=function(){
        return Math.ceil($scope.expenses.length/$scope.pageSize);
  }
  $scope.isNextPage=function(){
        return $scope.currentPage >= $scope.expenses.length/$scope.pageSize - 1
  }


  $scope.fileLoaded=false; 
  $scope.isFileLoaded=function(){
        return $scope.fileLoaded==true;
  }
	
  $scope.fileGet='';
  $scope.loadFile=function(){
  	$scope.fileLoaded = !$scope.fileLoaded; 
	console.log("fileGet name ",$scope.file);
	console.log("fileGet path =",$scope.file[3]);

        var url='/static/' + $scope.file.name;
        console.log("URL==",url);
	$http.get(url).success(function(data, status) {
    			$scope.status = status;
    			console.log(data);
			$scope.expenses = data;
  			$scope.numberOfPages();
  			$scope.fileLoaded=true; 
    	});
  }


  $scope.setFile = function(element) {
        $scope.$apply(function($scope) {
            $scope.file = element.files[0];
        });
  }







  //$scope.uploadFile= function(content) {
  //	console.log(content);
  //      $scope.uploadResponse = content.msg;
  //}

}]);




