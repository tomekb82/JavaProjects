'use strict';

angular.module('jhipsterApp')
    .factory('Foo', function ($resource) {
        return $resource('api/foos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
