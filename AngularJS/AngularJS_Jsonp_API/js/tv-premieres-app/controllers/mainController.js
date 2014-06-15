

app.filter('isGenre', function() {
    return function(input, genre) {
        if (typeof genre == 'undefined' || genre == null) {
            return input;
        } else {
            var out = [];
            for (var a = 0; a < input.length; a++){
                for (var b = 0; b < input[a].show.genres.length; b++){
                    if(input[a].show.genres[b] == genre) {
                        out.push(input[a]);
                    }
                }
            }
            return out;
        }
    };
});

app.controller("mainController", function($scope, $http){
    $scope.apiKey = "50f782399b18b28b40fb4f97e57ca0cd";
    $scope.results = [];
    $scope.filterText = null;
    $scope.availableGenres = [];
    $scope.genreFilter = null;

    $scope.orderFields = ["Air Date", "Rating"];
    $scope.orderDirections = ["Descending", "Ascending"];
    $scope.orderField = "Air Date"; //Default order field
    $scope.orderReverse = false;	

    $scope.setGenreFilter = function(genre) {
    	$scope.genreFilter = genre;

	$scope.customOrder = function(tvshow) {
    		switch ($scope.orderField) {
        		case "Air Date":
       			     return tvshow.episode.first_aired;
      			     break;
        		case "Rating":
        	    	     return tvshow.episode.ratings.percentage;
        	    	     break;
    		}
	};
    }

    $scope.init = function() {

        var overview = "";
        //API requires a start date
        var today = new Date();
        //Create the date string and ensure leading zeros if required
        var apiDate = today.getFullYear() + ("0" + (today.getMonth() + 1)).slice(-2) + "" + ("0" + today.getDate()).slice(-2);
        $http.jsonp('http://api.trakt.tv/calendar/premieres.json/' + $scope.apiKey + '/' + apiDate + '/' + 30 + '/?callback=JSON_CALLBACK').success(function(data) {

            //console.log(data);
            //As we are getting our data from an external source, we need to format the data so we can use it to our desired effect
            //For each day, get all the episodes
            angular.forEach(data, function(value, index){
                //The API stores the full date separately from each episode. Save it so we can use it later
                var date = value.date;
                //For each episodes, add it to the results array
                angular.forEach(value.episodes, function(tvshow, index){
                    //Create a date string from the timestamp so we can filter on it based on user text input
                    tvshow.date = date; //Attach the full date to each episode

		    overview = tvshow.episode.overview;
			       
                    $scope.results.push(tvshow);

 		    //Loop through each genre for this episode
                    angular.forEach(tvshow.show.genres, function(genre, index){
                        //Only add to the availableGenres array if it doesn't already exist
                        var exists = false;
                        angular.forEach($scope.availableGenres, function(avGenre, index){
                            if (avGenre == genre) {
                                exists = true;
                            }
                        });
                        if (exists == false) {
                            $scope.availableGenres.push(genre);
                        }
                    });



                });
            });
        }).error(function(error) {
 
        });


//var restRequest = gapi.client.request({
//  'path': '/plus/v1/activities',
//  'params': {'query': 'Google+', 'orderBy': 'best'}
//});

//var xhr = new XMLHttpRequest();
//xhr.open('GET', 'https://www.googleapis.com/plus/v1/activities?query=Google%2B&orderBy=best');


var xhr = new XMLHttpRequest();
var oauthToken = gapi.auth.getToken();
xhr.open('GET',
  'https://www.googleapis.com/plus/v1/people/me/activities/public' +
  '?access_token=' + encodeURIComponent(oauthToken.access_token));
xhr.send();



		    $http.jsonp('https://translate.google.pl/?hl=en&tab=wT#auto/pl/work').success(function(data) {
			console.log("translate: success");

		    }).error(function(error) {
 			console.log("translate: err, response=" + error);
                    }); 



    };
});