'use strict';

angular
	.module('jhipsterphotoApp')
	.filter('fileTypeSimple', fileTypeFltr)
	.filter('fileType', fileTypeFltr)

	function fileTypeSimpleFltr(){
		return function (data) {
			return /(png|jpeg|jpg|gif)$/.test(data.type);
		};
	}

	function fileTypeFltr(){
		return function(input, regex) {
      			var patt = new RegExp(regex);
      			var out = [];
			var filename = [];

      			if(input === undefined) {
          			return out;
      			}

      			for (var i = 0; i < input.length; i++){
          			if(patt.test(input[i])){
					filename = input[i].split('/');
              				out.push(filename[filename.length-1]);
				}
      			}
    			return out;
  		}
	}

