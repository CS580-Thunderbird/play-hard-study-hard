var app = angular.module("myApp", []);

app.controller('admin', function ($scope, $http) {
	
	/*$scope.loadOrgs = function(){
		$http.get("admin/organizer/list")
			.success(function(data){
			
		});
	};*/


	$scope.addOrg = function() {
	
		 $http.post("admin/organizer/" + $scope.new_id + "?name=" + $scope.new_name + "&link=" + $scope.new_link)
		  	.success(function(data){
		  		//$scope.loadOrgs();
		  	});
		 
		 
	  }
	  
	 
	  
	// $scope.loadOrgs();

});
