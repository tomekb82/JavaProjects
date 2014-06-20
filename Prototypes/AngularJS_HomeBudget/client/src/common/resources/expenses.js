angular.module('resources.expenses', ['mongolabResource']);
angular.module('resources.expenses').factory('Expenses', ['mongolabResource', function ($mongolabResource) {

  var Expenses = $mongolabResource('expenses');

  Expenses.forUser = function(userId, successcb, errorcb) {
    //TODO: get projects for this user only (!)
    return Expenses.query({}, successcb, errorcb);
  };

  Expenses.prototype.isProductOwner = function (userId) {
    return this.productOwner === userId;
  };
  Expenses.prototype.canActAsProductOwner = function (userId) {
    return !this.isScrumMaster(userId) && !this.isDevTeamMember(userId);
  };
  Expenses.prototype.isScrumMaster = function (userId) {
    return this.scrumMaster === userId;
  };
  Expenses.prototype.canActAsScrumMaster = function (userId) {
    return !this.isProductOwner(userId);
  };
  Expenses.prototype.isDevTeamMember = function (userId) {
    return this.teamMembers.indexOf(userId) >= 0;
  };
  Expenses.prototype.canActAsDevTeamMember = function (userId) {
    return !this.isProductOwner(userId);
  };

  Expenses.prototype.getRoles = function (userId) {
    var roles = [];
    if (this.isProductOwner(userId)) {
      roles.push('PO');
    } else {
      if (this.isScrumMaster(userId)){
        roles.push('SM');
      }
      if (this.isDevTeamMember(userId)){
        roles.push('DEV');
      }
    }
    return roles;
  };

  return Expenses;
}]);
