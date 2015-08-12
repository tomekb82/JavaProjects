'use strict';

angular.module('jhipsterphotoApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
