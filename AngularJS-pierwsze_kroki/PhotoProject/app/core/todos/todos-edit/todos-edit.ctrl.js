(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.todos.todosEditCtrl', ['$scope', '$location', 'photoApp.core.todos.todoCategoryFtr', todosEditCtrl]);

	function todosEditCtrl($scope, $location, categories) {
		$scope.categories = categories;
		$scope.formData = { type: $scope.categories[0], estimates: $scope.estimates = 1 };
		$scope.addTodo = function () {
			$scope.$parent.todos.push({
				'title': $scope.formData.newTodo,
				'done': false,
				'type': $scope.formData.type,
				'estimates': $scope.formData.estimates,
				'date': $scope.formData.date
			});
			$scope.formData.newTodo = '';
			$location.path('/list')
		};
	}


}());
