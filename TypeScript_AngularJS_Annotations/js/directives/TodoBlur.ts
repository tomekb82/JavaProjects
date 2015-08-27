/// <reference path='../_all.ts' />

module todos {
    'use strict';

    /**
     * Directive that executes an expression when the element it is applied to loses focus.
     */
    @directive('todomvc', 'todoBlur')
    export class TodoBlur{

 	//public static controllerAs: 'someDirectiveCtrl';
    	public static templateUrl: string = '/partials/some-directive.html';

	public static link: angular.IDirectiveLinkFn = ($scope: ng.IScope, element: JQuery, attributes: any) => {
                element.bind('blur', () => { $scope.$apply(attributes.todoBlur); });
                $scope.$on('$destroy', () => { element.unbind('blur'); });
        };

	constructor(
        	@inject('$scope') private $$scope: angular.IScope,
        	@inject('$parse') private $$parse: angular.IParseService
    	) {
        	// do stuff with $$scope and $$parse;
    	}
    
   	 public init(anArg: string): boolean {
        	// do some stuff with this.$$parse and this.$$scope
		return true;
    	}
    }
}
