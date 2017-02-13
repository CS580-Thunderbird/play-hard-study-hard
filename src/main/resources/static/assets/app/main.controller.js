app.controller("mainController", function($scope, $mdDialog){
	$scope.status = ' ';
	$scope.customeFullscreen = false;

	$scope.usrDialog = {

		sName2: "",

		eventPref2: {
			fitness: false,
			asi: false,
			international: false
		},

		sTime2: {
			myDate: new Date(),
			startTime: new Date(),
			endTime: new Date()
		},

		days2: [
			{name:"Sa", ck:false},
			{name:"M", ck:false},
			{name:"Tu", ck:false},
			{name:"W", ck:false},
			{name:"Th", ck:false},
			{name:"F", ck:false},
			{name:"Su", ck:false}
		]
	};

	$scope.showTabDialog = function(ev) {
		$mdDialog.show({
			controller: DigitalController2,
			templateUrl: 'userSetting.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
			locals: {
				usrDialog: $scope.usrDialog,
			}
		});
		function DigitalController2($scope, usrDialog){
			$scope.usrDialog = usrDialog;

			$scope.addBtn = function(){
				
				$scope.usrDialog.sName2 = "";

				for(var i = 0; i < $scope.usrDialog.days2.length; i++){
					$scope.usrDialog.days2[i].ck = false;
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

	$scope.eventFilter = function() {

		$http.post("user/eventPref/" + $scope.eventPref.fitness + "ASI: " + scope.eventPref.asi + "international: " + $scope.eventPref.international)
		.then(function(data){

		});
	}

	$scope.classId = '';

	$scope.addClass = function(){

		$http.post("user/classes/" + $scope.classId)
		.then(function(data){


		});
	}
});
