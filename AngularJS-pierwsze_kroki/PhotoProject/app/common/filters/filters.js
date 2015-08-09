﻿(function () {
    'use strict';

     angular
	.module('photoApp')
	.filter('rangeTime', rangeTime)
	
	function rangeTime(){
		return function (input, total, halfHour) {
			total = parseInt(total);
			for (var i = 1; i < total; i++) {
				if (halfHour) {
					input.push(i - 0.5);
				}
				input.push(i);
			}
			return input;
		};
	}


}());
