'use strict';

describe('Controller: LinksCtrl', function () {

  // load the controller's module
  beforeEach(module('yeomanProjectApp'));

  var LinksCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LinksCtrl = $controller('LinksCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
