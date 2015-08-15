(function () {
    'use strict';

    angular
        .module('photoApp')
        .config(['$urlRouterProvider','$locationProvider', '$stateProvider', appConfiguration]);

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
                		templateUrl: "app/common/view/layout.view.html"
            		},
            		"navbar@app": {
                		templateUrl: "app/common/view/navbar.view.html"
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
				templateUrl: 'app/core/about/about.view.html' 
			},
            		// the child views will be defined here (absolutely named)
            		'columnOne@about': { 
				template: 'Look I am a column!' 
			},
            		// for column two, we'll define a separate controller 
            		'columnTwo@about': { 
               			templateUrl: 'app/core/about/about-list/about-list.view.html',
                		controller: 'photoApp.core.about.aboutListCtrl'
            		}
		}
        })

	// TODOS PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('todos', {
        	url: '/todos',
		parent: 'app',
        	views: {
            		// the main template will be placed here (relatively named)
            		'': { 
				templateUrl: 'app/core/todos/todos.view.html',
				controller: 'photoApp.core.todos.todosCtrl' 
			},
			"content@todos": {
              			templateUrl: 'app/core/todos/todos-default.view.html'
        		},
		}
        })
	.state('todos.list', {
        	url: '/list',
        	views: {
			"content@todos": { 
               			templateUrl: 'app/core/todos/todos-list/todos-list.view.html',
                		controller: 'photoApp.core.todos.todosListCtrl'
            		}
		}
        })
	.state('todos.edit', {
        	url: '/edit',
        	views: {
            		// for column two, we'll define a separate controller 
            		"content@todos": { 
               			templateUrl: 'app/core/todos/todos-edit/todos-edit.view.html',
                		controller: 'photoApp.core.todos.todosEditCtrl'
            		}
		}
        })
	.state('todos.json', {
        	url: '/json',
        	views: {
			"content@todos": { 
               			templateUrl: 'app/core/todos/todos-json/todos-json.view.html'
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
   	    			templateUrl: 'app/core/home/home.view.html'
			}
		}
   	})
	// nested list with custom controller
    	.state('home.list', {
        	url: '/list',
       		templateUrl: 'app/core/home/home-list/home-list.view.html',
        	controller: function($scope) {
            		$scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
		}
        	
    	})

    	// nested list with just some random string data
    	.state('home.paragraph', {
        	url: '/paragraph',
        	template: 'I could sure use a drink right now.'
    	})

	// PHOTOS STATES AND NESTED VIEWS ========================================
	.state('photos', {
        	url: "/photos",
		parent: 'app',
		views: {
        		"": {
             			templateUrl: "app/core/photos/photos.view.html"
        		},
			"content@photos": {
              			templateUrl: "app/core/photos/photos-default.view.html",
				controller: "photoApp.core.photos.photosFormCtrl"
				
        		}/*,
		
        		"info@photos": {
              			template: "Default product list page, ready for your customization <br/> {{data}}",
				controller: "photoApp.core.photos.photosListCtrl"
				
        		},
        		"menu-details@photos": {
              			templateUrl: "app/core/photos/photos-list/photos-list.view.html",
               			controller: "photoApp.core.photos.photosListCtrl"
        		}*/
		},
		// inna opcja wstrzykiwania danych niż przez fabryki
		/*resolve: {
        		photos: function(){
               			return [
               				{id:1, name: "AngulrJS", features: ['ready']},
               				{id:2, name: "ReactJS", features: ['ready']},
               				{id:3, name: "EmberJS", features: ['ready']}
              			];
       			}
		}*/
	})
	.state('photos.list', {
        	url: '/list',
        	views: {
			"content@photos": { 
               			templateUrl: "app/core/photos/photos-list/photos-list.view.html",
               			controller: "photoApp.core.photos.photosListCtrl"
            		}
		}
        })
	.state('photos.form', {
        	url: '/form',
        	views: {
			"content@photos": { 
               			templateUrl: "app/core/photos/photos-form/photos-form.view.html",
				controller: "photoApp.core.photos.photosFormCtrl"
            		}
		}
        })
	.state('photos.details', {
        	url: "/details/:id",
		views: {
        		"content@photos": {
             			templateUrl: "app/core/photos/photos-details/photos-details.view.html",
				controller: "photoApp.core.photos.photosDetailsCtrl"
			}
		}
	})


	// photos features list
    	.state('photos.photo', {
        	url: "/photo/:id",
        	views: {
            		"content@photos": {
                		templateUrl: "app/core/photos/photos-details/photos-details.view.html",
				controller: "photoApp.core.photos.photosDetailsCtrl"
            		}
	        }
    	})
    	// single feature view
    	.state('photos.photo.feature', {
        	url: "/feature/:index",
        	views: {
            		"content@photos": {
                		templateUrl: "app/core/photos/photo-feature.html",
                		controller: 'FeatureController'
            		}
        	}
    	})
    	// feature - edit
    	.state('photos.photo.feature-edit', {
        	url: "/feature-edit/:index",
        	views: {
            		"navbar@app": {
                		template: "<b>Edit photos hoses here</b>"
            		},
            		"@app": {
                		templateUrl: "app/core/photos/photo-feature-edit.html",
                		controller: 'FeatureEditController'
            		}
        	}
    	})
   
    	// feature - edit in MODAL
    	.state('photos.photo.feature-view', {
        	url: "/feature-view/:index",
        	views: {
            	"modal@": {
                	templateUrl: "app/core/photos/photo-modal-layout.html"
            	},
            	"@photos.photo.feature-view": {
                	templateUrl: "app/core/photos/photo-feature-edit.html",
                	controller: 'FeatureEditController'
            	}
        	}
    	})
   
}
}());





