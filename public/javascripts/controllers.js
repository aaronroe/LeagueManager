var lmApp = angular.module('lmApp', ['ui.calendar', 'ui.bootstrap']);

lmApp.controller('OverviewCtrl', function($scope) {
    // dummy init for the current week.
    $scope.currentDate = new Date();

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
          right: 'today prev,next'
        },
        dayRender: $scope.highlightCurrentWeek
      }
    };

    // The sources that contain events to draw.
    $scope.eventSources = [$scope.events];
});

lmApp.controller('RosterInitCtrl', function($scope, $http) {

    // pagination data
    $scope.currentPage = 0;
    $scope.resultsPerPage = 10;
    $scope.numPages = function() {
        return Math.ceil($scope.recruitableAthletes.length / $scope.resultsPerPage);
    }
    $scope.pages = [];

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

// function for pagination to start from.
lmApp.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        if (input === undefined) {
            return start;
        }
        else {
            return input.slice(start);
        }
    }
});

/**
 * Function that adds onto Date and compares two dates to see if they are in the same week.
 */
Date.prototype.isInSameWeekAs = function(dateToCompare){
    var thisDate = new Date(+this);
    dateToCompare.setHours(0,0,0);
    thisDate.setHours(0,0,0);
    var beginningOfWeek = dateToCompare.getDate() - dateToCompare.getDay();
    return thisDate.getDate() >= beginningOfWeek && thisDate.getDate() < beginningOfWeek + 7;
};