'use strict';

describe('Service: Userloader', function () {

  // load the service's module
  beforeEach(module('yeomanProjectApp'));

  // instantiate service
  var Userloader;
  beforeEach(inject(function (_Userloader_) {
    Userloader = _Userloader_;
  }));

  it('should do something', function () {
    expect(!!Userloader).toBe(true);
  });

});
