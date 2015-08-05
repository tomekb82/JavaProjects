(function () {
    'use strict';

    angular
        .module('app')
        .config(['$stateProvider', todosConfig])
		.factory('todos', todosFactory)
        .controller('todosController', ['$scope','todos', todosController]);

    function todosConfig($stateProvider) {
        $stateProvider
            .state('todos', {
                url: '/todos',
                templateUrl: 'app/modules/todos/TodosView.html',
                controller: 'todosController'
            });
    }

    function todosController($scope, todos) {
		$scope.toggleJson = false;
		$scope.estimates = [0.5, 1, 2, 3, 4, 5, 8];
		$scope.categories = [
			{name: 'Personal', 'gico': 'heart'},
			{name: 'Health', 'gico': 'tint'},
			{name: 'Learning', 'gico': 'book'},
			{name: 'Business', 'gico': 'usd'},
			{name: 'Home', 'gico': 'home'},
			{name: 'Other', 'gico': 'paperclip'}
		];
		$scope.formData = {type: $scope.categories[0], estimates: $scope.estimates[0]};
		
		$scope.todos = todos;
		$scope.addTodo = function(){
			$scope.todos.push({'title': $scope.formData.newTodo, 'done': false, 'type': $scope.formData.type, 'estimates': $scope.formData.estimates});
			$scope.formData.newTodo = '';
			$scope.formData.type = '';
			$scope.formData.estimates = '';
		}
		$scope.deleteCompleted = function(){
			$scope.todos = $scope.todos.filter(function(item){
				return !item.done;
			});
		}
		
    }
	
	function todosFactory() {
		return [
		{
			'title' : 'Gym', 'done' : false, 'type': {"name" : "Health", "gico": "tint"}, 'estimates' : 2
		}
		];
    }

}());