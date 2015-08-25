(function () {
    'use strict';

     angular
	.module('jhipsterphotoApp')
	.factory('photosOpinionFtr', ['$resource', photosOpinionFtr]);

	function photosOpinionFtr($resource){
		return $resource('/photos/:photoId/opinion/:opinionId',
			{photoId: 1234, opinionId: '@id'}//,
			/*{likeOpinion: {method:'POST', params:{like:true}, isArray:false}*/);
	}



}());
