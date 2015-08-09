(function () {
    'use strict';

     angular
	.module('photoApp')
	.factory('categories', categories);
	
	function categories(){
		return [
			{ name: 'Personalne', 'gico': 'heart' },
			{ name: 'Zdrowie', 'gico': 'tint' },
			{ name: 'Nauka', 'gico': 'book' },
			{ name: 'Biznes', 'gico': 'usd' },
			{ name: 'Dom', 'gico': 'home' },
			{ name: 'Inne', 'gico': 'paperclip' }];
	}

}());
