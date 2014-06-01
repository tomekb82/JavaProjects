'use strict';

describe('Controller: IngredientsCtrl', function () {

  // load the controller's module
  beforeEach(module('yeomanProjectApp'));

  var IngredientsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    IngredientsCtrl = $controller('IngredientsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
