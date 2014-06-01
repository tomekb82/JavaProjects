'use strict';

describe('Service: Multirecipeloader', function () {

  // load the service's module
  beforeEach(module('yeomanProjectApp'));

  // instantiate service
  var Multirecipeloader;
  beforeEach(inject(function (_Multirecipeloader_) {
    Multirecipeloader = _Multirecipeloader_;
  }));

  it('should do something', function () {
    expect(!!Multirecipeloader).toBe(true);
  });

});
