var express = require('express');
var app = express();

app.use(express.static("../"));

app.get('/test', function (req, res) {
  	console.log("hello");
	res.send('Hello World!');
});



app.get('/photos/1234/opinion', function (req, res, next){
	
    	var results = ['Opinia 1', 'Opinia 2', 'Opinia 3'];
	console.log('opinions');
    	res.send(results);
});

var _DIRNAME = "/home/tomek/Desktop/repo/JavaProjects/AngularJS-pierwsze_kroki/PhotoProject/assets";

app.get('/photo/filenames', function (req, res, next){
	var filesystem = require("fs");
    	var results = [];
	filesystem.readdirSync(_DIRNAME +"/img").forEach(function(file) {
        	file = _DIRNAME+'/img/'+file;
        	var stat = filesystem.statSync(file);
        	if (stat && stat.isDirectory()) {
            		results = results.concat(_getAllFilesFromFolder(file))
        	} else {
			results.push(file);
		}
    	});
    	res.send(results);
});

app.get('/photo/description/:filename', function (req, res, next){
 
	var fs = require('fs');
	var filename = req.params.filename.split('.');
	var jsonFilename = _DIRNAME + "/files/" + filename[0] + ".json";
	var jsonDesc = JSON.parse(fs.readFileSync(jsonFilename, 'utf8'));
	res.send(jsonDesc);
  
});

app.get('/photo/image/:filename', function (req, res, next){
 
	var fs = require('fs');
	var filename = req.params.filename;
  	var img = fs.readFileSync(_DIRNAME + "/img/" + filename);
	res.writeHead(200, {'Content-Type': 'image/jpeg'});
     	//res.writeHead(200, {'Content-Type': 'image/jpg' });
     	res.end(img);
	//res.send(img);
  
});

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);
});

