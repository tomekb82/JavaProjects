'use strict';

angular.module('jhipsterphotoApp')
    .factory('Photo', function ($resource, DateUtils) {
        return $resource('api/photos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    })
    .factory('Description', function ($resource, DateUtils) {
            return $resource('api/photos/description/:filename', {}, {
                'query': { method: 'GET', isArray: true},
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        console.log("ff=" + data);
                        data = angular.fromJson(data);
                        return data;
                    }
                },
                'update': { method:'PUT' }
            });
        })

    ;
