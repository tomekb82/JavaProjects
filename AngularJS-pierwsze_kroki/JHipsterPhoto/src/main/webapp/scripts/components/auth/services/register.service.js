'use strict';

angular.module('jhipsterphotoApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


