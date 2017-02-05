var app = angular.module("myApp", []);

app.controller('admin', function ($scope, $http) {

	//Test
	$scope.logout = function () {
		window.location = '/signout';
	};
	
	$scope.addOrg = function() {
		  $http.post("admin/organizer/" + $scope.new_id + "?name=" + $scope.new_name + "&link=" + $scope.new_link)
		  	.success(function(data){
		  		
		  	});
	  }


});
