app.controller("mainController", function($scope, $http, $mdDialog, calendarConfig, $window){
  $scope.status = ' ';
  $scope.customeFullscreen = false;
  $scope.status="";
  // $scope.eventOrgList = [];
  /* Need to link checked status */
    if($scope.eventOrgList == undefined){
      $http.get("data/eventOrgList").then(function(response) {
        $scope.eventOrgList = response.data;
        console.log("Get Result: " + $scope.eventOrgList);
      });
    }

    // console.log("Event Org List: " + $scope.eventOrgList);
    if($scope.orgList == undefined){
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
    }

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
    // $scope.getEventOrgList();
    // console.log("Get Result: " + $scope.eventOrgList);

    for (var i = 0; i < $scope.orgList.length; i++) {
      if($scope.eventOrgList.includes((i+1).toString())){
        $scope.orgList[i].prefCk = true;
      }
      if ($scope.orgList[i].prefCk && !$scope.eventOrgList.includes((i+1).toString())) {
        $scope.orgList[i].prefCk = false;
      }
    }
    // if($scope.eventOrgList.includes("1")){
    //   $scope.orgList[0].prefCk = true;
    // }

    $mdDialog.show({
      controller: DigitalController2,
      templateUrl: 'userSetting.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose:true,
      locals: {
        usrDialog: $scope.usrDialog,
        orgList: $scope.orgList,
      }
    });
    function DigitalController2($scope, $http, usrDialog, orgList){
      $scope.usrDialog = usrDialog;
      $scope.orgList = orgList;

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

    }
  };
  $scope.cancel = function() {
    // $scope.getEventOrgList();
    $mdDialog.cancel();
    $window.location.reload();
  };

  $scope.addorg = {
    preferSet: [],
  };

  $scope.getEventOrgList = function(){
    $http.get("data/eventOrgList").then(function(response) {
      $scope.eventOrgList = response.data;
      console.log("Get Result: " + $scope.eventOrgList);
    });
  }

  $scope.saveEventPref = function() {
    $scope.addorg.preferSet = [];
    for (index = 0; index < $scope.orgList.length; index++) {
      if ($scope.orgList[index].prefCk == true) {
        num = index + 1;
        $scope.addorg.preferSet.push(num.toString());
//        this.orgList[index].prefCk = $scope.orgList[index].prefCk;
      }
    }
    $http.post('setting/add_org', $scope.addorg).
      then(function(response) {
        $scope.getEventOrgList();
        console.log("Event Filter posted")
      },
      function(data){
        console.log("ERROR POSTING");
      });

      $scope.status="Preferences Saved.";

      var temp = [];
      // for (var i = 0; i < $scope.orgList.length; i++) {
      //   temp.push(angular.extend({}, $scope.orgList[i], {prefCk: true}));
      // }
      // $scope.orgList = temp;
    // if(this.addorg.preferSet.includes("1")){
    //   $scope.orgList[0].prefCk = true;
    // }
  }


  /*Work on post for user settings*/

  $scope.classId = '';

  $scope.classList =[];

  $scope.addClass = function() {
      $scope.classNbr = document.getElementById('ajax').value.split("(")[1];
      $scope.classNbr = $scope.classNbr.split(")")[0];
      console.log($scope.classNbr);

      $scope.name = document.getElementById('ajax').value;
      document.getElementById('ajax').value = "";

      var selected = false;
      tmp = {
          'name': $scope.name,
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

      var name = $scope.classNbr + "=";
      document.cookie = name + $scope.name.split(" -")[0];

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
