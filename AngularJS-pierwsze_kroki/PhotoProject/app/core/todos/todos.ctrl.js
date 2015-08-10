(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.todos.todosCtrl', ['$scope', '$location', 'photoApp.core.todos.todoFtr', todosCtrl]);

	function todosCtrl($scope, $location, todoFtr) {
		$scope.todos = todoFtr;
		$scope.getClass = function (path) {
			if ($location.path().substr(0, path.length) == path) {
				return "active"
			} else {
				return ""
			}
		}
	}


}());
