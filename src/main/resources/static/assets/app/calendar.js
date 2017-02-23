app.controller('CalendarCtrl', function($scope, moment, calendarConfig, calendarEventTitle) {

    var vm = this;

    vm.events = [{
      title: 'An event',
      color: calendarConfig.colorTypes.warning,
      startsAt: moment().startOf('week').add(8, 'hours').toDate(),
      endsAt: moment().startOf('week').add(9, 'hours').toDate(),
    },
    {
      title: 'Test Event',
      color: calendarConfig.colorTypes.warning,
      startsAt: moment(),
      endsAt: moment().add(3, 'hours'),
    }
    ];

    vm.calendarView = 'month';
    vm.viewDate = moment().startOf('month').toDate();

    var originalEventTitle = angular.copy(calendarEventTitle);

    calendarEventTitle.monthViewTooltip = calendarEventTitle.weekViewTooltip = calendarEventTitle.dayViewTooltip = function() {
      return '';
    };

    // required so other demos work as before
    $scope.$on('$destroy', function() {
      angular.extend(calendarEventTitle, originalEventTitle);
    });

  });
