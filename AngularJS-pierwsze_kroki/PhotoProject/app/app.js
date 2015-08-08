(function () {
    'use strict';

    angular
        .module('photoApp', ['ui.router', 'ngSanitize','ngAnimate'])
        .config(['$urlRouterProvider','$locationProvider', '$stateProvider', appConfiguration])
	.controller('scotchController', ['$scope', scotchController]);

	// let's define the scotch controller that we call up in the about state
	function scotchController($scope) {
    		$scope.message = 'test';
		$scope.scotches = [
        		{name: 'Macallan 12', price: 50},
        		{name: 'Chivas Regal Royal Salute', price: 10000},
	        	{name: 'Glenfiddich 1937',price: 20000}
    		];
    
	}

    function appConfiguration($urlRouterProvider, $locationProvider, $stateProvider) {
        $urlRouterProvider.otherwise('/home');
        //$locationProvider.hashPrefix('!');
	//$locationProvider.html5Mode(true);

	$stateProvider
        
	// main application layout
    	.state('app', {
        	abstract: true,  // no urls, abstract state to be inherited
        	views: {
            		"": {
                		templateUrl: "views/layout.html"
            		},
            		"navbar@app": {
                		templateUrl: "views/navbar.html"
            		}
        	}
    	})

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
        	url: '/about',
		parent: 'app',
        	views: {
            		// the main template will be placed here (relatively named)
            		'': { 
				templateUrl: 'views/partial-about.html' 
			},
            		// the child views will be defined here (absolutely named)
            		'columnOne@about': { 
				template: 'Look I am a column!' 
			},
            		// for column two, we'll define a separate controller 
            		'columnTwo@about': { 
               			templateUrl: 'views/table-data.html',
                		controller: 'scotchController'
            		}
		}
        })
      

    	// HOME STATES AND NESTED VIEWS ========================================
   	.state('home', {
   	    url: '/home',
	    parent: 'app',
    	    views: {
            		// the main template will be placed here (relatively named)
            		'': { 	
   	    			templateUrl: 'views/partial-home.html'
			}
		}
   	})
	



		.state('photos', {
        		url: "/photos",
			parent: 'app',
			views: {
            			"": {
                			templateUrl: "app/modules/photos/photos.html"
            			},
            			"content@photos": {
                			template: "Default product list page, ready for your customization"
            			},
            			"menu@photos": {
                			templateUrl: "app/modules/photos/photos-menu.html",
                			controller: "photosController"
            			}
			},
			resolve: {
            			photos: function(){
                			return [
                    				{id:1, name: "AngulrJS", features: ['ready']},
                   				{id:2, name: "ReactJS", features: ['ready']},
                    				{id:3, name: "EmberJS", features: ['ready']}
                			];
            			}
			}


    		})
    	// nested list with custom controller
    	.state('home.list', {
        	url: '/list',
       		templateUrl: 'views/partial-home-list.html',
        	controller: function($scope) {
            		$scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
		}
        	
    	})

    	// nested list with just some random string data
    	.state('home.paragraph', {
        	url: '/paragraph',
        	template: 'I could sure use a drink right now.'
    	})
    }
}());





