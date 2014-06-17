'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', [
'ngdemo.filters',
'ngdemo.services',
'ngdemo.directives',
'ngdemo.controllers',

//'ngCookies',
'ngResource',
//'ngSanitize',
//'ngRoute'

])

    .config(['$routeProvider', function ($routeProvider) {
            $routeProvider

             .when('/view1', {
                templateUrl: 'partials/partial1.html',
                controller: 'MyCtrl1'})

             .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
              })
              .when('/home', {
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl'
              })
              .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl'
              })
              /* Contacts */
              .when('/contactList', {
        	resolve: {
                  users: ["MultiUserLoader", function(MultiUserLoader) {
                    return MultiUserLoader();
                  }]
                },
                templateUrl: 'views/contactList.html',
                controller: 'ContactListCtrl'
              })
              .when('/contactNew', {
                templateUrl: 'views/contactEditNew.html',
                controller: 'ContactNewCtrl'
              })
              .when('/contactEdit/:userName', {
        	resolve: {
        	  user: ["UserLoader", function(UserLoader) {
                    return UserLoader();
                  }]
                },
                templateUrl: 'views/contactEditNew.html',
                controller: 'ContactEditCtrl'
              })

              /* Recipes */
              .when('/list', {
        	resolve: {
                  recipes: ["MultiRecipeLoader", function(MultiRecipeLoader) {
                    return MultiRecipeLoader();
                  }]
                },
                templateUrl: 'views/List.html',
                controller: 'ListCtrl'
              })
              .when('/edit/:recipeId', {
        	resolve: {
                  recipe: ["RecipeLoader", function(RecipeLoader) {
                    return RecipeLoader();
                  }]
                },
                templateUrl: 'views/Edit.html',
                controller: 'EditCtrl'
              })
              .when('/new', {
                templateUrl: 'views/Edit.html', // the same source View as for EDIT
                //templateUrl: 'views/New.html',
                controller: 'NewCtrl'
              })
              .when('/view/:recipeId', {
        	resolve: {
                  recipe: ["RecipeLoader", function(RecipeLoader) {
                    return RecipeLoader();
                  }]
                },
                templateUrl: 'views/View.html',
                controller: 'ViewCtrl'
              })
              .when('/Ingredients', {
                templateUrl: 'views/Ingredients.html',
                controller: 'IngredientsCtrl'
              })
              .when('/links', {
                templateUrl: 'views/links.html',
                controller: 'LinksCtrl'
              })
              .when('/pagination', {
                templateUrl: 'views/pagination.html',
                controller: 'PaginationCtrl'
              })
              .when('/team', {
                templateUrl: 'views/team.html',
                controller: 'TeamCtrl'
              })
              .when('/broadcast', {
                templateUrl: 'views/broadcast.html',
                controller: 'BroadcastCtrl'
              })
              .otherwise({
                redirectTo: '/view1'
                 //redirectTo: '/'
              });

    }]);
