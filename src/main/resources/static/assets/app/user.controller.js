
app.controller('userInfo', function ($scope, $http) {

  $scope.loadUsers = function() {
	   $http.get("data/user")
	   	.then(function(response) {
	   		$scope.getUser = response.data;
	   });
  }

  $scope.loadUsers();
});
