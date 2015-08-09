(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('listTodos', ['$scope', listTodos]);

	function listTodos($scope) {
		$scope.deleteCompleted = function () {
			$scope.$parent.todos = $scope.$parent.todos.filter(function (item) {
				return !item.done;
			});
		};
	}


}());
