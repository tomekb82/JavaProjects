(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('photoApp.core.todos.todoFtr', todoFtr);

	function todoFtr(){
		return [
		{
			'title': 'Randka z Julitą', 'done': false, "type": { "name":"Personalne", "gico": "heart" }, 'estimates': 3, "date": "11/11/2015"
		},
		{
			'title': 'Siłownia', 'done': false, "type": { "name": "Zdrowie", "gico": "tint" }, 'estimates': 2, "date": "12/11/2015"
		},
		{
			'title': 'AngularJS następne kroki', 'done': false, "type": { "name": "Nauka", "gico": "book" }, 'estimates': 4, "date": "14/11/2015"
		},
		{
			'title': 'Spotkanie z Janem', 'done': false, "type": { "name": "Biznes", "gico": "usd" }, 'estimates': 1, "date": "15/11/2015"
		}
		];
	}



}());
