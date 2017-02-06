var app = angular.module("myApp", []);

app.controller('admin', function ($scope, $http) {
	
	$scope.loadOrgs = function(){
		$http.get("admin/organizer/list")
			.then(function(data){
			$scope.records = data.data;
		});
	};


	$scope.addOrg = function() {
	
		 $http.post("admin/organizer/" + $scope.new_id + "?name=" + $scope.new_name + "&link=" + $scope.new_link)
		  	.then(function(data){
		  		$scope.loadOrgs();
		  	});
		 
		 
	  }
	  
	 
	  
	$scope.loadOrgs();

});
