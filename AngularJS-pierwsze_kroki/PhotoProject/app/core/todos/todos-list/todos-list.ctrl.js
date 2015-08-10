(function () {
    'use strict';

    angular
        .module('photoApp')
        .controller('photoApp.core.todos.todosListCtrl', ['$scope', todosListCtrl]);

	function todosListCtrl($scope) {
		$scope.deleteCompleted = function () {
			$scope.$parent.todos = $scope.$parent.todos.filter(function (item) {
				return !item.done;
			});
		};
	}


}());
