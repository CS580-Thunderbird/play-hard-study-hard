app.controller("mainController", function($scope, $http, $mdDialog){

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
			startTime: moment().seconds(0).milliseconds(0).toDate(),
			endTime: moment().seconds(0).milliseconds(0).toDate()
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

				$scope.momentDate = moment($scope.usrDialog.sTime2.myDate.toISOString()).format('MM/DD/YYYY');
				$scope.momentStartTime = moment($scope.usrDialog.sTime2.startTime.toISOString()).format('hh:mm:ss a');
				$scope.momentEndTime = moment($scope.usrDialog.sTime2.endTime.toISOString()).format('hh:mm:ss a');

				for(var i = 0; i < $scope.usrDialog.days2.length; i++){
					$scope.usrDialog.days2[i].ck = false;
				}
				// $mdDialog.hide();
			}
		}
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

		$http.post("user/add_org?id=" + $scope.usrDialog.eventPref2.fitness);
	}

	$scope.classId = '';

	/* Search box suggestion for adding class */
	// Get the <datalist> and <input> elements.
    var dataList = document.getElementById('json-datalist');
    var input = document.getElementById('ajax');

    // Create a new XMLHttpRequest.
    var request = new XMLHttpRequest();

    // Handle state changes for the request.
    request.onreadystatechange = function(response) {
      if (request.readyState === 4) {
        if (request.status === 200) {
          // Parse the JSON
          var jsonOptions = JSON.parse(request.responseText);

          // Loop over the JSON array.
          jsonOptions.forEach(function(item) {
            // Create a new <option> element.
            var option = document.createElement('option');
            // Set the value using the item in the JSON array.
            option.value = item.code + " - " + item.description;
            // $scope.classId = item.code;
            // Add the <option> element to the <datalist>.
            dataList.appendChild(option);
          });

        } else {
          // An error occured :(
          input.placeholder = "Couldn't load datalist options :(";
        }
      }
    };
    // Set up and make the request.
    // request.open('GET', 'data/sampleCppClasses.json', true);
    request.open('GET', 'http://localhost:8080/data/classes', true);
    request.send();
});
