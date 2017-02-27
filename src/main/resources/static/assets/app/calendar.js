app.controller('CalendarCtrl', function($scope, $window, moment, calendarConfig, calendarEventTitle, $mdDialog) {

    calendarConfig.templates.calendarMonthCellEvents = '/calendarMonthCellEvents.html';

    var vm = this;

    vm.events = [{
      title: 'An event',
      color: calendarConfig.colorTypes.warning,
      startsAt: moment().startOf('week').add(8, 'hours').toDate(),
      endsAt: moment().startOf('week').add(9, 'hours').toDate(),
    },
    {
        title: 'Test Event',
        color: calendarConfig.colorTypes.info,
        startsAt: moment().startOf('month').toDate(),
        actions: [{
          label: '<i class=\'glyphicon glyphicon-remove\'></i>',
          onClick: function(args) {
            $scope.deleteEvent();
          }
        }],
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      },
      {
        title: 'Second Test Event',
        color: calendarConfig.colorTypes.info,
        startsAt: moment().startOf('month').toDate(),
        actions: [{
          label: '<i class=\'glyphicon glyphicon-remove\'></i>',
          onClick: function(args) {
            $scope.deleteEvent();
          }
        }],
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      },
      {
        title: $scope.orgList[0].org,
        color: calendarConfig.colorTypes.warning,
        startsAt: moment(),
        endsAt: moment().add(3, 'hours'),
      }
    ];

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

    // required so other demos work as before
    $scope.$on('$destroy', function() {
      angular.extend(calendarEventTitle, originalEventTitle);
    });

    $scope.addClassToCalendar = function() {
      vm.events = [];

      console.log(document.cookie);
      var ca = document.cookie.split(';');

      for(var i = 0; i < ca.length; i++) {
          var c = ca[i];
          c = c.split("=")[1];
          addedClass = {
              title: c,
              color: calendarConfig.colorTypes.warning,
              actions: [{
                label: '<i class=\'glyphicon glyphicon-remove\'></i>',
                onClick: function(args) {
                  $scope.deleteEvent();
                }
              }],
              startsAt: moment().startOf('week').add(8, 'hours').toDate(),
              endsAt: moment().startOf('week').add(9, 'hours').toDate(),
            };

          if (!ca[i].split("=")[0].includes("expires")) {
            vm.events.push(addedClass);
          }
      }

      document.cookie = "12345=;expires=Thu, 01 Jan 1970 00:00:00 UTC";
    }

  });
