app.controller('CalendarCtrl', function($scope, $window, moment, calendarConfig, calendarEventTitle, $mdDialog) {

    calendarConfig.templates.calendarMonthCellEvents = '/calendarMonthCellEvents.html';

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
    vm.events = [{
      title: 'ASI Event',
      color: {
        primary: asiColor, // the primary event color (should be darker than secondary)
        secondary: secondaryColor // the secondary event color (should be lighter than primary)
      },
      startsAt: moment().startOf('week').add(8, 'hours').toDate(),
      endsAt: moment().startOf('week').add(9, 'hours').toDate(),
    },
    {
        title: 'CS 580',
        color: {
          primary: classColor, // the primary event color (should be darker than secondary)
          secondary: secondaryColor // the secondary event color (should be lighter than primary)
        },
        // startsAt: moment().startOf('month').toDate(),
        actions: actions,
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      },
      {
        title: 'International Student Club',
        color: {
          primary: intlClubColor, // the primary event color (should be darker than secondary)
          secondary: 'secondaryColor' // the secondary event color (should be lighter than primary)
        },
        // startsAt: moment().startOf('month').toDate(),
        actions: actions,
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      },
      {
        title: $scope.orgList[0].org,
        color: {
          primary: fitnessColor, // the primary event color (should be darker than secondary)
          secondary: 'secondaryColor' // the secondary event color (should be lighter than primary)
        },
        actions: actions,
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      }
    ];

    $scope.eventList = vm.events;

    vm.calendarView = 'month';
    vm.viewDate = moment().startOf('month').toDate();

    vm.timespanClicked = function(date, cellInfo) {
      if (cellInfo.badgeTotal == 0) {
        $scope.showTabDialog();
      }
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

    $scope.makeEvent = function(title, startTime, endTime, color) {
      return {
        title: title,
        color: { // color for event
          primary: color,
          secondary: 'secondaryColor'
        },
        actions: [{
          label: '<i class=\'glyphicon glyphicon-remove\'></i>',
          onClick: function(args) {
            $scope.deleteEvent();
          }
        }],
        startsAt: startTime,
        endsAt: endTime,
      };
    }

    $scope.addEventToCalendar = function() {

      // temporary load data from JSON file
      $scope.asiJSON = "data/sampleEvents.json";
      $scope.loadJSON($scope.asiJSON, function(response) {
        var actual_JSON = JSON.parse(response);

        actual_JSON.forEach(function(item) {
          var momentObjStart = moment(item.startsAt, moment.ISO_8601);
          var momentObjEnd = moment(item.endsAt, moment.ISO_8601);

          var addedEvent = $scope.makeEvent(item.title, momentObjStart, momentObjEnd, eval(item.orgColor));

          console.log(addedEvent);
          $scope.eventList.push(addedEvent);

        });

      });
    }

    $scope.addClassToCalendar = function() {

      // temporary load data from JSON file
      $scope.cppClassJSON = "data/sampleCppClasses.json";
      $scope.loadJSON($scope.cppClassJSON, function(response) {

        var actual_JSON = JSON.parse(response);

        actual_JSON.forEach(function(item) {
          var momentObjStart = moment(item.start_date, 'YYYY-MM-DD');
          var momentObjEnd = moment(item.end_date, 'YYYY-MM-DD');

          var addedClass = $scope.makeEvent(item.code, momentObjStart, momentObjEnd, classColor);

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
