//var express = require("express"),
//  app     = express(),
//  port    = parseInt(process.env.PORT, 10) || 8082;


var express = require('express')
  , app = express()
  , server = require('http').createServer(app)
  , io = require('socket.io').listen(server);

app.configure(function(){
  app.use(express.methodOverride());
  app.use(express.bodyParser());
  app.use(express.static(__dirname + '/app'));
  app.use(app.router);
});



io.sockets.on('connection', function (socket) {
  socket.emit('new:msg', 'Welcome to AnonBoard');

  socket.on('broadcast:msg', function(data) {
    // Tell all the other clients (except self) about the new message
    socket.broadcast.emit('new:msg', data.message);
  });
});


var recipes_map = {
  '1': {
    "id": "1",
    "title": "Cookies",
    "description": "Delicious, crisp on the outside, chewy on the outside, oozing with chocolatey goodness cookies. The best kind",
    "ingredients": [
      {
        "amount": "1",
        "amountUnits": "packet",
        "ingredientName": "Chips Ahoy"
      }
    ],
    "instructions": "1. Go buy a packet of Chips Ahoy\n2. Heat it up in an oven\n3. Enjoy warm cookies\n4. Learn how to bake cookies from somewhere else"
  },
  '2': {
    id: 2,
    'title': 'Recipe 2',
    'description': 'Description 2',
    'instructions': 'Instruction 2',
    ingredients: [
      {amount: 13, amountUnits: 'pounds', ingredientName: 'Awesomeness'}
    ]
  }
};
var next_id = 3;

/* Test resource: User */
var users = [
        {name:'Jan Kowalski', city:'Warszawa'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Anna Mucha', city:'Warszawa'},
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}, 
        {name:'Jan Nowak', city:'Krakow'}
];

app.get('/users', function(req, res) {

  	console.log('Read ALL users: ', users);

  // Simulate delay in server
  setTimeout(function() {
    res.send(users);
  }, 500);


});
app.get('/users/:name', function(req, res) {

  	var name = req.params.name;

        for(var id in users){
		if (users[id].name == name){
  			console.log('Read user: ', users[id]);
			res.send(users[id])
		}
	}
});

app.post('/users/:name', function(req, res) {

  var user = {};
  user = req.body;

  console.log('Add new user:', user);
  users.push(user); 
  console.log('Add new user,  ALL:', users);
  res.send(user);
});

app.delete('/users/:name', function(req, res) {
  	console.log('Deleting user with name', req.params.name);
  
  	var name = req.params.name;
  	for(var id in users){
		if (users[id].name == name){
  			users.splice(id, 1);
		}
	}

  console.log(users);
});

/* PAGINATION*/
app.get('/search', function(req, res) {

  var query = req.query['query'];
  var offset = req.query['offset'] * 1;
  var limit = req.query['limit'] * 1;
  var items = [];
  console.log("Paginacja, limit=", limit);
  for (var i = 0; i < limit; i++) {
    items.push('Item for ' + query + ' ' + (offset + i));
  }
  res.send(items);
});



/* Recipes: */
app.get('/recipes', function(req, res) {
  var recipes = [];

  for (var key in recipes_map) {
    recipes.push(recipes_map[key]);
  }

  // Simulate delay in server
  setTimeout(function() {
    res.send(recipes);
  }, 500);
});

app.get('/recipes/:id', function(req, res) {
  console.log('Requesting recipe with id', req.params.id);
  res.send(recipes_map[req.params.id]);
});

app.delete('/recipes/:id', function(req, res) {
  console.log('Deleting recipe with id', req.params.id);
  
  delete recipes_map[req.params.id];
 
  console.log(recipes_map);

});

app.post('/recipes', function(req, res) {
  var recipe = {};
  recipe.id = next_id++;
  recipe.title = req.body.title;
  recipe.description = req.body.description;
  recipe.ingredients = req.body.ingredients;
  recipe.instructions = req.body.instructions;

  recipes_map[recipe.id] = recipe;

  res.send(recipe);
});

app.post('/recipes/:id', function(req, res) {
  var recipe = {};
  recipe.id = req.params.id;
  recipe.title = req.body.title;
  recipe.description = req.body.description;
  recipe.ingredients = req.body.ingredients;
  recipe.instructions = req.body.instructions;

  recipes_map[recipe.id] = recipe;

  res.send(recipe);
});

var port = 8082;
server.listen(8082);
//app.listen(port);
console.log('Now serving the app at http://localhost:' + port + '/');
