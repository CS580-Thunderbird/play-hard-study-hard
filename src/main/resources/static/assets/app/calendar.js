app.controller('CalendarCtrl', function($scope, $http, $window, moment, calendarConfig, calendarEventTitle, $mdDialog) {

    calendarConfig.templates.calendarMonthCellEvents = '/customCalendarTemplates/calendarMonthCellEvents.html';
    calendarConfig.templates.calendarSlideBox = '/customCalendarTemplates/calendarSlideBox.html';

    var vm = this;

    var classColor = '#c1d0ef';
    var asiColor = '#cfefc1';
    var intlClubColor = '#d9c1ef';
    var fitnessColor = '#efe9c1';
    var secondaryColor = '#ffffff';

    var actions=[{
        label: '<i class=\'glyphicon glyphicon-remove\'></i>',
        onClick: function(args) {
          $scope.deleteEvent();
        }
      },
      {
        label: '<i class=\'glyphicon glyphicon-plus\'></i>',
        onClick: function(args) {
          $scope.addSuggestedEvent();
        }
      }
    ];

    vm.events = [];

    $scope.eventList = vm.events;

    vm.calendarView = 'month';
    vm.viewDate = moment().startOf('month').toDate();
    $scope.event_Suggestions = false;
    $scope.eventPost = {
      eventArray: [],
    };

    $scope.eventSuggestions = function() {
      $scope.event_Suggestions = !$scope.event_Suggestions
      $scope.eventPost.eventArray = angular.copy($scope.eventOrgList);
      if($scope.event_Suggestions){
        $http.post('data/events', $scope.eventPost).
          then(function(response) {
            $scope.Events = response.data;
            console.log("Event Filter posted: " + $scope.eventPost.eventArray);
            console.log("Events: " + $scope.Events[0]);
          },
          function(data){
            console.log("ERROR POSTING");
          });
      }
      // vm.events.push($scope.Events);
      // $scope.eventList.push($scope.Events);

      console.log("Event suggestions pressed: " + $scope.event_Suggestions);

    }

    vm.timespanClicked = function(date, cellInfo) {
      if (cellInfo.badgeTotal == 0 || $scope.dateClicked == true) {
        $scope.showTabDialog(this, 1);
        $scope.dateClicked == false;

        $scope.usrDialog.sTime2.startTime = date;
        $scope.usrDialog.sTime2.endTime = date;
      }
    }

    vm.dateClicked = function (date, cellInfo) {
      $scope.dateClicked = true;
      return false;
    }

    var originalEventTitle = angular.copy(calendarEventTitle);

    calendarEventTitle.monthViewTooltip = calendarEventTitle.weekViewTooltip = calendarEventTitle.dayViewTooltip = function() {
      return '';
    };

    $scope.deleteEvent = function() {
      $mdDialog.show(
        $mdDialog.alert()
          .parent(angular.element(document.querySelector('#popupContainer')))
          .clickOutsideToClose(true)
          .title('Event Deleted')
          .textContent('You have decided to delete this event.')
          .ariaLabel('Alert Dialog Demo')
          .ok('Close')
      );
    }

    $scope.addSuggestedEvent = function() {
      $mdDialog.show(
        $mdDialog.alert()
          .parent(angular.element(document.querySelector('#popupContainer')))
          .clickOutsideToClose(true)
          .title('Added Event')
          .textContent('This event has been added to your Calendar.')
          .ariaLabel('Alert Dialog Demo')
          .ok('Close')
      );
    }


    // required so other demos work as before
    $scope.$on('$destroy', function() {
      angular.extend(calendarEventTitle, originalEventTitle);
    });

    $scope.addClassToCalendarCookie = function() {
      vm.events = [];

      console.log(document.cookie);
      var ca = document.cookie.split(';');

      for(var i = 0; i < ca.length; i++) {
          var c = ca[i];
          c = c.split("=")[1];

          var addedClass = $scope.makeEvent(c, moment().startOf('week').add(8, 'hours').toDate(),
              moment().startOf('week').add(9, 'hours').toDate(), classColor);

          if (!ca[i].split("=")[0].includes("expires")) {
            vm.events.push(addedClass);
          }
      }

      document.cookie = "12345=;expires=Thu, 01 Jan 1970 00:00:00 UTC";
    }

    $scope.loadJSON = function (file, callback) {
      var xobj = new XMLHttpRequest();
      xobj.overrideMimeType("application/json");
      xobj.open('GET', file, true);
      xobj.onreadystatechange = function () {
            if (xobj.readyState == 4 && xobj.status == "200") {
              // Required use of an anonymous callback as .open will NOT return a value but simply returns undefined in asynchronous mode
              callback(xobj.responseText);
            }
      };
      xobj.send(null);
    }

    $scope.makeEvent = function(title, startTime, endTime, color, actionArray) {
      return {
        title: title,
        color: { // color for event
          primary: color,
          secondary: 'secondaryColor'
        },
        actions: actionArray,
        startsAt: startTime,
        endsAt: endTime,
      };
    }

    $scope.addEventToCalendar = function() {

      // temporary load data from JSON file
      // $scope.asiJSON = "data/sampleEvents.json";
      // $scope.loadJSON($scope.asiJSON, function(response) {
      //   var actual_JSON = JSON.parse(response);
      //
      //   actual_JSON.forEach(function(item) {
      //     var momentObjStart = moment(item.startsAt, moment.ISO_8601);
      //     var momentObjEnd = moment(item.endsAt, moment.ISO_8601);
      //
      //     var addedEvent = $scope.makeEvent(item.title, momentObjStart, momentObjEnd, eval(item.orgColor), actions);
      //
      //     console.log(addedEvent);
      //     $scope.eventList.push(addedEvent);
      //
      //   });
      //
      // });
//      $scope.eventList.push($scope.Events);
      for (var i = 0; i < $scope.Events.length; i++) {
        // console.log("EVENT LIST: " + $scope.Events[i]);
        $scope.eventList.push($scope.Events[i]);
      }
    }

    $scope.addClassToCalendar = function() {

      // temporary load data from JSON file
      $scope.cppClassJSON = "data/sampleCppClasses.json";
      $scope.loadJSON($scope.cppClassJSON, function(response) {

        var actual_JSON = JSON.parse(response);

        actual_JSON.forEach(function(item) {
          var momentObjStart = moment(item.start_date, 'YYYY-MM-DD');
          var momentObjEnd = moment(item.end_date, 'YYYY-MM-DD');

          var addedClass = $scope.makeEvent(item.code, momentObjStart, momentObjEnd, classColor, []);

          console.log(addedClass);
          $scope.eventList.push(addedClass);

        });

      });
    }

    $scope.addToCalendar = function () {
       $scope.addClassToCalendar();
       $scope.addEventToCalendar();
    }

  });
