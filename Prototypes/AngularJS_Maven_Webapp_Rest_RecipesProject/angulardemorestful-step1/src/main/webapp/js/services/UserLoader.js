'use strict';

angular.module('ngdemo.services')
.factory('UserLoader', ['User', '$route', '$q',
    function(User, $route, $q) {
  return function() {
    var delay = $q.defer();
    console.log("UserLoader:", $route.current.params.userName);
    User.get({name: $route.current.params.userName}, function(user) {
      delay.resolve(user);
    }, function() {
      delay.reject('Unable to fetch user=', $route.current.params.userName);
    });
    return delay.promise;
  };
}]);

