var lmApp = angular.module('lmApp', []);

lmApp.controller('RosterInitCtrl', function($scope, $http) {

    $scope.selectedAthlete = null;

    $scope.setSelectedAthlete = function(selectedAthlete) {
        if ($scope.selectedAthlete != null) {
            $scope.selectedAthlete["selected"] = false;
        }
        selectedAthlete["selected"] = true;
        $scope.selectedAthlete = selectedAthlete;
    };

    $http.get('/athlete/recruitable.json').then(function(result) {
        for (var i=0; i < result.data.length; i++) {
            result.data[i]["selected"] = false;
        }
        $scope.recruitableAthletes = result.data;
    });

});