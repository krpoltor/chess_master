angular.module('app.component1')
    .controller('ListChallengesController',['$scope', 'ChallengesFactory', function($scope, ChallengesFactory) {
        'use strict';

        $scope.data = {
            challenges: []
        };

        ChallengesFactory.getChallenges().success(function(response) {
            $scope.data.challenges = response;

            for (var i = 0, len = $scope.data.challenges.length; i < len; i++) {
                var unixStartDate = $scope.data.challenges[i].startDate,
                    unixEndDate = $scope.data.challenges[i].endDate,
                    dateObject;

                dateObject = new Date(unixStartDate);
                $scope.data.challenges[i].startDate = dateObject.toUTCString();

                dateObject = new Date(unixEndDate);
                $scope.data.challenges[i].endDate = dateObject.toUTCString();

            }
        });

    }]);
