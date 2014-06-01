'use strict';

describe('Service: Recipeloader', function () {

  // load the service's module
  beforeEach(module('yeomanProjectApp'));

  // instantiate service
  var Recipeloader;
  beforeEach(inject(function (_Recipeloader_) {
    Recipeloader = _Recipeloader_;
  }));

  it('should do something', function () {
    expect(!!Recipeloader).toBe(true);
  });

});
