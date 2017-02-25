app.controller("mainController", function($scope, $http, $mdDialog){
  $scope.status = ' ';
  $scope.customeFullscreen = false;
  $scope.eventPref = {};

  /* Need to link checked status */
  $scope.orgList = [ {
    org : "Fitness Classes",
    prefCk : false,
    filterCk : false
  }, {
    org : "ASI Events",
    prefCk : false,
    filterCk : false
  }, {
    org : "International Student Clubs",
    prefCk : false,
    filterCk : false
  } ];

  $scope.usrDialog = {
    sName2: "",

    eventPref2: {},

    sTime2: {
      startTime: moment().seconds(0).milliseconds(0).toDate(),
      endTime: moment().seconds(0).milliseconds(0).toDate(),
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
    function DigitalController2($scope, $http, usrDialog){
      $scope.usrDialog = usrDialog;

      $scope.addBtn = function(){

        $scope.momentStartTime = moment(this.usrDialog.sTime2.startTime.toISOString()).format('MMMM Do YYYY, hh:mm:ss a');
        $scope.momentEndTime = moment(this.usrDialog.sTime2.endTime.toISOString()).format('MMMM Do YYYY, hh:mm:ss a');

        // $scope.usrDialog.days2 = this.usrDialog.days2;
        // for(var i = 0; i < $scope.usrDialog.days2.length; i++){
        // 	$scope.usrDialog.days2[i].ck = false;
        // }
        console.log($scope.momentStartTime + "\n" + $scope.momentEndTime);
        $mdDialog.hide();

      };
			$scope.cancel = function() {
				$mdDialog.cancel();
			};
    }
  };

  $scope.addorg = {
    preferSet: [],
  };

  $scope.saveEventPref = function() {
    $scope.addorg.preferSet = [];
    for (index = 0; index < $scope.orgList.length; index++) {
      if ($scope.orgList[index].prefCk == true) {
        num = index + 1;
        $scope.addorg.preferSet.push(num.toString());
      }
    }

    $http.post('setting/add_org', $scope.addorg).
      then(function(response) {
        console.log("Event Filter posted")
      },
      function(data){
        console.log("ERROR POSTING");
      });

  }


  /*Work on post for user settings*/

  $scope.classId = '';

  $scope.classList =[];

  $scope.addClass = function() {
      $scope.classNbr = document.getElementById('ajax').value.split("(")[1];
      $scope.classNbr = $scope.classNbr.split(")")[0];
      console.log($scope.classNbr);

      var name = document.getElementById('ajax').value;
      var selected = false;
      tmp = {
          'name': name,
          'selected': selected,
          'classNbr': $scope.classNbr
      };
      $scope.classList.push(tmp);

      $http.get("setting/add_class?code=" + $scope.classNbr)
          .then(function(data) {
              console.log("Class added");
          },
          function(data) {
              console.log("ERROR GETTING - addClass");
      });

      document.getElementById('ajax').value = "";
  }

  $scope.deleteClass = function() {
    index = $scope.classList.length;
    while (index--) {
      if ($scope.classList[index].selected == true) {
        $http.get("/setting/delete_class?classNbr=" + $scope.classList[index].classNbr)
            .then(function(data) {
                console.log("Class deleted");
            },
            function(data) {
                console.log("ERROR GETTING - deleteClass");
        });
        $scope.classList.splice(index,1);
      }
    }
  }

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
            option.value = item.code + " - " + item.instructor + ": " + item.description + " (" + item.class_nbr + ")";
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
    request.open('GET', 'data/class-cpp.json', true);
    //request.open('GET', 'http://localhost:8080/data/classes', true);
    request.send();
});
