angular.module('app.component2')
    .controller('ManageChallengesController', ['$scope', '$modal', '$route', 'LoginForModalFactory', 'ChallengesFactory', 'AuthenticatedFactory', 'CrudService',
        function($scope, $modal, $route, LoginForModalFactory, ChallengesFactory, AuthenticatedFactory, CrudService) {
            'use strict';

            $scope.data = {
                challenges: [],
                modifiedChallenge: [],
                isAuthenticated: false, //AuthenticatedFactory.getIsAuthenticated(),//false,
                //isLogged:  false,//AuthenticatedFactory.getIsLogged(),
                loginValue: '',
                disableModification: false,
                oponnent: ''
            };

            // $scope.$watch(function() {
            //     return [AuthenticatedFactory.getIsAuthenticated(), AuthenticatedFactory.getIsLogged()];
            // }, function(newValues, oldValues) {
            //     if (newValues[0] !== oldValues[0]) {
            //         $scope.data.isAuthenticated = newValues[0];
            //     }
            //     if (newValues[1] !== oldValues[1]) {
            //         $scope.data.isLogged = newValues[1];
            //     }
            // });

            // $scope.$watch(function() {
            //     return AuthenticatedFactory.getIsLogged();
            // }, function(newValue, oldValue) {
            //     if (newValue !== oldValue) $scope.data.isLogged = newValue;
            // });

            $scope.accept = function(element) {
              $scope.data.modifiedChallenge = $scope.data.challenges[element.$index];
                if ($scope.data.modifiedChallenge.status == 'AWAITING_REPLY' ) {
                  $scope.data.modifiedChallenge.status = 'ACCEPTED';
                  CrudService.put($scope.data.modifiedChallenge);
                  $scope.data.disableModification = true;
                }else {
                  alert('You cannot accept/decline this challenge twice you cheeky breeky');
                }
            };

            $scope.decline = function(element) {
              $scope.data.modifiedChallenge = $scope.data.challenges[element.$index];
                if ($scope.data.modifiedChallenge.status == 'AWAITING_REPLY' ) {
                  $scope.data.modifiedChallenge.status = 'DECLINED';
                  CrudService.put($scope.data.modifiedChallenge);
                  $scope.data.disableModification = true;
                }else {
                  alert('You cannot accept/decline this challenge twice you cheeky breeky');
                }
            };

            $scope.addChallenge = function(){
              $modal.open({
                  templateUrl: '/component-2/modal/add-challenge-dialog.tpl.html',
                  controller: 'ModalDialogController',
                  size: 'lg'
              });
            };

            $modal.open({
                templateUrl: '/component-2/modal/login-dialog.tpl.html',
                controller: 'ModalDialogController',
                size: 'lg'
            }).result.then(function() {
                $scope.data.loginValue = LoginForModalFactory.login;

                ChallengesFactory.getPlayerChallenges($scope.data.loginValue).success(function(response) {
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
                    $scope.data.isAuthenticated = true;

                }).error(function() {
                    $scope.data.isAuthenticated = false;
                });
            });
        }

    ]);
