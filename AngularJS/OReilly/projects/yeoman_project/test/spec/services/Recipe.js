'use strict';

describe('Service: Recipe', function () {

  // load the service's module
  beforeEach(module('yeomanProjectApp'));

  // instantiate service
  var Recipe;
  beforeEach(inject(function (_Recipe_) {
    Recipe = _Recipe_;
  }));

  it('should do something', function () {
    expect(!!Recipe).toBe(true);
  });

});
