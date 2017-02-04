app.controller("mainController", function($scope, $mdDialog){
	$scope.status = ' ';
	$scope.customeFullscreen = false;

	$scope.showTabDialog = function(ev) {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'userSetting.html',
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
	
	$scope.showFilterTabDialog = function(ev) {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'filterTabDialog.tmpl.html',
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

	$scope.showPrerenderedDialog = function(ev) {
		$mdDialog.show({
			controller: DialogController,
			contentElement: '#myDialog',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose: true
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
});