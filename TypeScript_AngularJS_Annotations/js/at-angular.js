var todos;
(function (todos) {
    'use strict';
    var directiveProperties = [
        'compile',
        'controller',
        'controllerAs',
        'bindToController',
        'link',
        'name',
        'priority',
        'replace',
        'require',
        'restrict',
        'scope',
        'template',
        'templateUrl',
        'terminal',
        'transclude'
    ];
    function instantiate(moduleName, name, mode) {
        return function (target) {
            angular.module(moduleName)[mode](name, target);
        };
    }
    function attachInjects(target) {
        var args = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            args[_i - 1] = arguments[_i];
        }
        (target.$inject || []).forEach(function (item, index) {
            target.prototype[(item.charAt(0) === '$' ? '$' : '$$') + item] = args[index];
        });
        return target;
    }
    todos.attachInjects = attachInjects;
    function inject() {
        var args = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            args[_i - 0] = arguments[_i];
        }
        return function (target, key, index) {
            if (angular.isNumber(index)) {
                target.$inject = target.$inject || [];
                target.$inject[index] = args[0];
            }
            else {
                target.$inject = args;
            }
        };
    }
    todos.inject = inject;
    function service(moduleName, serviceName) {
        return instantiate(moduleName, serviceName, 'service');
    }
    todos.service = service;
    function controller(moduleName, ctrlName) {
        return instantiate(moduleName, ctrlName, 'controller');
    }
    todos.controller = controller;
    function directive(moduleName, directiveName) {
        return function (target) {
            var config;
            /* istanbul ignore else */
            if (target.controller) {
                controller(moduleName, target.controller.split(' ').shift())(target);
            }
            config = angular.IDirective;
            /*config = directiveProperties.reduce((
                config: angular.IDirective,
                property: string
            ) => {
                return angular.isDefined(target[property]) ? angular.extend(config, {[property]: target[property]}) :
                    config; /* istanbul ignore next */
        }, { controller: target, scope: Boolean(target.templateUrl) };
        ;
            * /;
        angular.module(moduleName).directive(directiveName, function () { return (config); });
    }
    todos.directive = directive;
    ;
})(todos || (todos = {}));
function classFactory(moduleName, className) {
    return function (target) {
        function factory() {
            var args = [];
            for (var _i = 0; _i < arguments.length; _i++) {
                args[_i - 0] = arguments[_i];
            }
            return todos.attachInjects.apply(todos, [target].concat(args));
        }
        /* istanbul ignore else */
        if (target.$inject && target.$inject.length > 0) {
            factory.$inject = target.$inject.slice(0);
        }
        angular.module(moduleName).factory(className, factory);
    };
}
exports.classFactory = classFactory;
