(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('mainTodos', ['$scope', '$location', 'todos', mainTodos]);

	function mainTodos($scope, $location, todos) {
		$scope.todos = todos;
		$scope.getClass = function (path) {
			if ($location.path().substr(0, path.length) == path) {
				return "active"
			} else {
				return ""
			}
		}
	}


}());
