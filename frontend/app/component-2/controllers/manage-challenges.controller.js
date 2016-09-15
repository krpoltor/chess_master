angular.module('app.component2')
    .controller('ManageChallengesController', ['$scope', '$modal', '$route', 'LoginForModalFactory', 'ChallengesFactory',
    function($scope, $modal, $route, LoginForModalFactory, ChallengesFactory) {
        'use strict';

        $scope.data = {
            challenges: []

            // ,
            // authenticated: false
        };

        // if (!$scope.data.authenticated) {
        $modal.open({
            templateUrl: '/component-2/modal/login-dialog.tpl.html',
            controller: 'LoginDialogController',
            size: 'lg'
        }).result.then(function(result) {
            {
                $scope.loginValue = LoginForModalFactory.login;

                ChallengesFactory.getPlayerChallenges($scope.loginValue).success(function(response) {
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

            }

        });

        // }



        // $scope.data.authenticated = AuthenticatedModalService.getAuthenticated();

        //$route.reload();



    }]);
