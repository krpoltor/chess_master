angular.module('app.component2')
    .controller('ManageChallengesController', ['$scope', '$modal', '$route', 'LoginForModalFactory', 'ChallengesFactory', 'AuthenticatedFactory',
        function($scope, $modal, $route, LoginForModalFactory, ChallengesFactory, AuthenticatedFactory) {
            'use strict';

            $scope.data = {
                challenges: []
            };

            $scope.isAuthenticated = false;

            $scope.$watch(function() {
                return AuthenticatedFactory.getAuthenticated();
            }, function(newValue, oldValue) {
                if (newValue !== oldValue) $scope.isAuthenticated = newValue;
            });

            $modal.open({
                templateUrl: '/component-2/modal/login-dialog.tpl.html',
                controller: 'LoginDialogController',
                size: 'lg'
            }).result.then(function() {
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
        }
    ]);
