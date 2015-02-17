'use strict';

angular.module('jhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('bar', {
                parent: 'entity',
                url: '/bar',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/bar/bars.html',
                        controller: 'BarController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('bar');
                        return $translate.refresh();
                    }]
                }
            })
            .state('barDetail', {
                parent: 'entity',
                url: '/bar/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/bar/bar-detail.html',
                        controller: 'BarDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('bar');
                        return $translate.refresh();
                    }]
                }
            });
    });
