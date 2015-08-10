(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('photoApp.core.todos.todoCategoryFtr', todoCategoryFtr);
	
	function todoCategoryFtr(){
		return [
			{ name: 'Personalne', 'gico': 'heart' },
			{ name: 'Zdrowie', 'gico': 'tint' },
			{ name: 'Nauka', 'gico': 'book' },
			{ name: 'Biznes', 'gico': 'usd' },
			{ name: 'Dom', 'gico': 'home' },
			{ name: 'Inne', 'gico': 'paperclip' }];
	}

}());
