var lmApp = angular.module('lmApp', []);

lmApp.controller('RosterInitCtrl', function($scope, $http) {

    $scope.toggleAthleteSelected = function(athlete) {
        if (athlete.selected == false) {
            athlete.selected = true;
        } else {
            athlete.selected = false;
        }
    };

    $http.get('/athlete/recruitable.json').then(function(result) {
        for (var i=0; i < result.data.length; i++) {
            result.data[i].selected = false;
        }
        $scope.recruitableAthletes = result.data;
    });

});