'use strict';

angular.module('ngdemo.services')
.factory('socket', function ($rootScope) {
 console.log('io.1');
  var socket = io.connect('http://localhost:8082');
 console.log('io.2');
  return {
    on: function (eventName, callback) {
      socket.on(eventName, function () {
        var args = arguments;
        $rootScope.$apply(function () {
          callback.apply(socket, args);
        });
      });
    },
    emit: function (eventName, data, callback) {
      socket.emit(eventName, data, function () {
        var args = arguments;
        $rootScope.$apply(function () {
          if (callback) {
            callback.apply(socket, args);
          }
        });
      })
    }
  };
});
