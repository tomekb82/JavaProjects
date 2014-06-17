'use strict';

angular.module('yeomanProjectApp')
.factory('MultiUserLoader', ['User', '$q', function(User, $q) {
        return function() {
                var delay = $q.defer();
                User.query(
                        // on success
                        function(users) {
                                delay.resolve(users);
                        }, 
                        // on error
                        function() {
                                delay.reject('Unable to fetch users');
                        }
                );
                return delay.promise;
        };
}]);

