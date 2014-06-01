'use strict';

describe('Service: Multiuserloader', function () {

  // load the service's module
  beforeEach(module('yeomanProjectApp'));

  // instantiate service
  var Multiuserloader;
  beforeEach(inject(function (_Multiuserloader_) {
    Multiuserloader = _Multiuserloader_;
  }));

  it('should do something', function () {
    expect(!!Multiuserloader).toBe(true);
  });

});
