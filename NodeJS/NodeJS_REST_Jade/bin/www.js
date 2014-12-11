#!/usr/bin/env node
var debug = require('debug')('{name}');
var app = require('../nodetest2/app');
app.set('port', process.env.PORT || 3000);

var server = app.listen(app.get('port'), function() {
  debug('Express server listening on port ' + server.address().port);
});
