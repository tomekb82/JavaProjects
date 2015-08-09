(function () {
    'use strict';

    angular
        .module('app', ['ui.router', 'ngResource', 'ngSanitize','ngAnimate'])
        .config(['$urlRouterProvider','$locationProvider', appConfiguration]);

    function appConfiguration( $urlRouterProvider, $locationProvider) {
        $urlRouterProvider.otherwise('/home');
        $locationProvider.hashPrefix('!');
    }
}());

