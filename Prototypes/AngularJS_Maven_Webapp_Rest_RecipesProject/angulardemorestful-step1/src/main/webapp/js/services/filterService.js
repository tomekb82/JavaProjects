'use strict';

angular.module('ngdemo.services')
.factory('filterService', function() {
    return {
      activeFilters: {},
      searchText: ''
    };
  });
