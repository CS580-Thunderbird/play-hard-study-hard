app.controller('CalendarCtrl', function($scope, moment, calendarConfig, calendarEventTitle, $mdDialog) {

    var vm = this;

    vm.events = [{
      title: 'An event',
      color: calendarConfig.colorTypes.warning,
      startsAt: moment().startOf('week').add(8, 'hours').toDate(),
      endsAt: moment().startOf('week').add(9, 'hours').toDate(),
    },
    // {
    //   title: 'Test Event',
    //   color: calendarConfig.colorTypes.warning,
    //   startsAt: moment(),
    //   endsAt: moment().add(3, 'hours'),
    // },
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
    ];

    vm.calendarView = 'month';
    vm.viewDate = moment().startOf('month').toDate();

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

  });
