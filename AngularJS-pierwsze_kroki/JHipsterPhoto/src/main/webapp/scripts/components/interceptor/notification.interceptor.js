 'use strict';

angular.module('jhipsterphotoApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-jhipsterphotoApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-jhipsterphotoApp-params')});
                }
                return response;
            },
        };
    });