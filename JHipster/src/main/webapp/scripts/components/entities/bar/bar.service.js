'use strict';

angular.module('jhipsterApp')
    .factory('Bar', function ($resource) {
        return $resource('api/bars/:id', {}, {
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
