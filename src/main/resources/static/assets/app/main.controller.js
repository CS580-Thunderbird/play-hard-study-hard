app.controller("mainController", function($scope, $mdDialog){
	$scope.status = ' ';
	$scope.customeFullscreen = false;
	$scope.sRepeat=false;
	$scope.days = [
		{name:"Sa", ck:false},
		{name:"M", ck:false},
		{name:"Tu", ck:false},
		{name:"W", ck:false},
		{name:"Th", ck:false},
		{name:"F", ck:false},
		{name:"Su", ck:false}
	];

	$scope.eventPref={
		fitness: false,
		asi: false,
		international: false
	};

	$scope.showTabDialog = function(ev) {
		$mdDialog.show({
			controller: DigitalController2,
			templateUrl: 'userSetting.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
			locals: {
				eventPref: $scope.eventPref,
				days: $scope.days,
				sRepeat: $scope.sRepeat
			}
		});
		function DigitalController2($scope, days, sRepeat, eventPref){
			$scope.eventPref = eventPref;
			$scope.days = days;
			$scope.sRepeat = sRepeat;
			$scope.closeWindow = function(){

				for(var i = 0; i < $scope.days.length; i++){
					$scope.days[i].ck = false;
				}
			$mdDialog.hide();
			}
		}
	};
	$scope.myDate = new Date();


	$scope.showFilterTabDialog = function(ev) {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'filterTabDialog.k.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true
		})
		.then(function(answer) {
			$scope.status = 'You said the information was "' + answer + '".';
		}, function() {
			$scope.status = 'You cancelled the dialog.';
		});
	};

	function DialogController($scope, $mdDialog) {
		$scope.hide = function() {
			$mdDialog.hide();
		};

		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.answer = function(answer) {
			$mdDialog.hide(answer);
		};
	}

	/*Work on post for user settings*/
	


});
