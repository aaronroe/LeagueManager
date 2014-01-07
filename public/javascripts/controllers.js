var lmApp = angular.module('lmApp', []);

lmApp.controller('RosterInitCtrl', function($scope, $http) {

    $http.get('/athlete/recruitable.json').then(function(result) {
        $scope.recruitableAthletes = result.data;
    });

});