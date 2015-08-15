(function () {
    'use strict';

     angular
	.module('photoApp')
	.service('photoApp.common.service.fileMgmtSrv', fileMgmtSrv)
	
	function fileMgmtSrv(){
		return {
			getAllFilesFromFolder: function (dir) {
				var filesystem = require("fs");
    				var results = [];

    				filesystem.readdirSync(dir).forEach(function(file) {
        				file = dir+'/'+file;
        				var stat = filesystem.statSync(file);
        				if (stat && stat.isDirectory()) {
            					results = results.concat(_getAllFilesFromFolder(file))
						console.log("AA=" + results);
        				} else {
						results.push(file);
						console.log("AA=" + results);
					}

    				});
    				return results;
			}
		}
	}


}());


