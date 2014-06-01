'use strict';

angular.module('yeomanProjectApp')
  .controller('BroadcastCtrl', function ($scope, socket) {
  
  $scope.message = '';
  $scope.messages = [];

  // When we see a new msg event from the server
  socket.on('new:msg', function (message) {
  $scope.messages.push(message);
});

// Tell the server there is a new message
$scope.broadcast = function() {
  socket.emit('broadcast:msg', {message: $scope.message});
  $scope.messages.push($scope.message);
  $scope.message = '';
};

});
