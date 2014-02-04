var lmApp = angular.module('lmApp', ['ui.calendar', 'ui.bootstrap']);

lmApp.controller('OverviewCtrl', function($scope, $window) {
    /**
     * Initializes the current date.
     */
    $scope.initializeDate = function(timestamp) {
        $scope.currentDate = new Date(timestamp);
        $scope.currentDate.setHours(0,0,0,0);
    }
    /**
     * Watch so that focus stays on current date. Otherwise every time the
     * calendar is manipulated, it will go to "today".
     */
    $scope.$watch('overview', function() {
        $scope.overview.fullCalendar('gotoDate', $scope.currentDate);
    });
    /**
     * Function that highlights the current week.
     */
    $scope.highlightCurrentWeek = function(date, cell) {
        if (date.isInSameWeekAs($scope.currentDate)) {
            cell.addClass("selected-week");
        }
    }

    // array that contains events that are displayed on the calendar.
    $scope.events = [];

    // config object that contains calendar.
    $scope.uiConfig = {
      calendar:{
        height: 450,
        editable: true,
        header:{
          left: 'title',
          center: '',
          right: 'prev,next'
        },
        dayRender: $scope.highlightCurrentWeek
      }
    };

    // The sources that contain events to draw.
    $scope.eventSources = [$scope.events];

    /**
     * Action to take when the advance week button is clicked.
     */
    $scope.advanceWeek = function() {
        $window.location.href = '/overview/advanceweek';
    };
});

lmApp.controller('RosterInitCtrl', function($scope, $http) {

    // pagination data
    $scope.currentPage = 0;
    $scope.resultsPerPage = 10;
    $scope.numPages = function() {
        return Math.ceil($scope.recruitableAthletes.length / $scope.resultsPerPage);
    }
    $scope.pages = [];

    // table sorting
    $scope.athleteSortIndex = "name";
    $scope.reverse = true;
    $scope.sortAthletesBy = function(key) {
        $scope.recruitableAthletes.sort(function(a,b) {
            if (a[key] > b[key]) {
                if ($scope.reverse) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if (a[key] < b[key]) {
                if ($scope.reverse) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                if (a.name > b.name) {
                    return 1;
                }
                else {

                }
                return 0;
            }
        });
        $scope.selectedColumn = key;
        $scope.reverse = !$scope.reverse;
        $scope.showPage(0);
    }
    $scope.isSelectedColumn = function(key) {
        if(key == $scope.selectedColumn) {
            if ($scope.reverse) {
                return "glyphicon-arrow-up"
            }
            else {
                return "glyphicon-arrow-down"
            }
        }
        return "";
    }
    $scope.selectedColumn = "";

    // pagination functions
    $scope.showNextPage = function() {
        if($scope.currentPage + 1 < $scope.numPages()) {
            $scope.currentPage++;
        }
    }
    $scope.showPreviousPage = function() {
        if($scope.currentPage > 0) {
            $scope.currentPage--;
        }
    }
    $scope.showPage = function(page) {
        $scope.currentPage = page;
    }

    // function for pagination that preserves checkboxes across pages.
    // not the most elegant solution, but it works.
    $scope.isVisible = function(athlete) {
        var index = $scope.recruitableAthletes.indexOf(athlete);
        if (index > $scope.currentPage * $scope.resultsPerPage &&
            index < ($scope.currentPage + 1) * $scope.resultsPerPage) {
            return true;
        }
        else {
            return false;
        }
    }

    $scope.toggleAthleteSelected = function(athlete) {
        if (athlete.selected == false) {
            athlete.selected = true;
        } else {
            athlete.selected = false;
        }
    };

    $http.get('/athlete/recruitable.json').then(function(result) {
        for (var i = 0; i < result.data.length; i++) {
            result.data[i].selected = false;
        }
        $scope.recruitableAthletes = result.data;

        for (var i = 0; i < $scope.numPages(); i++) {
            $scope.pages.push(i);
        }
    });

});


/**
 * Function that adds onto Date and compares two dates to see if they are in the same week.
 */
Date.prototype.isInSameWeekAs = function(dateToCompare){
    var thisDate = new Date(+this);

    // put into dawn of day for timezone of client.
    dateToCompare.setHours(0,0,0,0);
    thisDate.setHours(0,0,0,0);

    // get the beginning of the week.
    var beginningOfWeek = new Date(dateToCompare.getTime());
    beginningOfWeek.setDate(beginningOfWeek.getDate() - beginningOfWeek.getDay());
    beginningOfWeek.setHours(0,0,0,0);

    // get the end of the week.
    var endOfWeek = new Date(beginningOfWeek.getTime());
    endOfWeek.setDate(beginningOfWeek.getDate() + 6);
    endOfWeek.setHours(0,0,0,0);

    return thisDate.getTime() >= beginningOfWeek.getTime() && thisDate.getTime() <= endOfWeek.getTime();
};