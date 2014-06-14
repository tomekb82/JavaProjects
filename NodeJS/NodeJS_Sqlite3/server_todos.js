var application_root = __dirname,
	express = require("express"),
	path = require("path");

var fs = require("fs");
var file = "todo.db";
var exists = fs.existsSync(file);
var sqlite3 = require("sqlite3").verbose();
var db = new sqlite3.Database(file);

if(!exists) {
  console.log("Creating DB file.");
  fs.openSync(file, "w");
}

var app = express();

app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});

// Config
app.configure(function () {
  app.use(express.bodyParser());
  app.use(express.methodOverride());
  app.use(app.router);
  app.use(express.static(path.join(application_root, "public")));
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

app.get('/todos', function (req, res) {
   res.send('Server for TODOS is running');
});

app.get('/getTodos', function (req, res) {

	db.all("SELECT * FROM tasks", function(err, rows){
		if( err || !rows) {
			console.log("No todos found");
	 	}else {
 			res.writeHead(200, {'Content-Type': 'application/json'});
			str='[';
 			rows.forEach(function (row) {
				if(row.status=="done"){
					chart = "success";
				}else if(row.status=="deleted"){
					chart = "warning";
				}else if(row.status=="todo" || row.status=="totest"){
					chart = "danger";
				}else{
					chart = "active";
				}
        	    	 	str = str + '{ "taskid" : "' + row.taskid + 
					    '", "creator": "' + row.creator +
					    '", "status": "' + row.status +
					    '", "assignees": "' + row.assignees +
 					    '", "projects": "' + row.projects.substring(1,row.projects.length-1) +	
					    '", "file": "' + row.file +
					    '", "last_change": "' + row.last_change +	
					    '", "chart": "' + chart +		
					    '"},' 
					  +'\n';
        		});

			str = str.trim();
			str = str.substring(0,str.length-1);
			str = str + ']';
			res.end( str);
		}
	});
	
});

app.post('/postTodos', function (req, res) {
        console.log("POST: ");
        console.log(req.body.mydata);
        var jsonData = JSON.parse(req.body.mydata);
        console.log(jsonData.taskid);

	db.all("SELECT * FROM tasks", function(err, rows){
		if( err || !rows) {
			console.log("No todos found");
	 	}else {
 			res.writeHead(200, {'Content-Type': 'application/json'});
			str='[';
			rows.forEach(function (row) {
			  if(row.taskid == jsonData.taskid){
				console.log(row);
				if(row.status=="done"){
					chart = "success";
				}else if(row.status=="deleted"){
					chart = "warning";
				}else if(row.status=="todo*" || row.status=="totest"){
					chart = "danger";
				}else{
					chart = "active";
				}
				desc = row.description.replace(/[^a-zA-Z 0-9]+/g,'');
				comment = row.comments.replace(/[^a-zA-Z 0-9]+/g,'');
				console.log(desc);
        	    	 	str = str + '{ "taskid" : "' + row.taskid + 
					    '", "description": "' + desc +	
					    '", "comments": "' + comment +	
					    '"},' 
					  +'\n';
			    }
			});
			str = str.trim();
			str = str.substring(0,str.length-1);
			str = str + ']';
			res.end( str);
		}
	});
	
});

app.listen(1212);
