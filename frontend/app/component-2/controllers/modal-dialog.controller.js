angular.module('app.component2')
    .controller('ModalDialogController', ['$scope', '$modalInstance', 'PlayersFactory', 'LoginForModalFactory', 'AuthenticatedFactory', 'CrudService',
        function($scope, $modalInstance, PlayersFactory, LoginForModalFactory, AuthenticatedFactory, CrudService) {
            'use strict';

            var players = {
                whitePlayer: '',
                blackPlayer: ''
            };

            $scope.data = {
                loginForm: {
                    login: '',
                    password: ''
                },
                loginValue: '',
                isAuthenticated: false //, AuthenticatedFactory.getIsAuthenticated(),//false,
                    //  isLogged: false //AuthenticatedFactory.getIsLogged()
            };

            $scope.data.loginValue = LoginForModalFactory.login;

            $scope.sendChallenge = function() {
                players.whitePlayer = $scope.data.loginValue;
                players.blackPlayer = $scope.data.oponnent;
                CrudService.post(players);
            };


            // $scope.$watchGroup('[data.isAuthenticated, data.isLogged]', function(newValue, oldValue) {
            //     if (newValue[0] !== oldValue[0]) {
            //         AuthenticatedFactory.setIsAuthenticated(newValue[0]);
            //     }
            //     if (newValue[1] !== oldValue[1]) {
            //         AuthenticatedFactory.setIsLogged(newValue[1]);
            //     }
            // });

            // $scope.$watch('data.isLogged', function(newValue, oldValue) {
            //     if (newValue !== oldValue) AuthenticatedFactory.setIsLogged(newValue);
            // });

            $scope.login = function() {

                PlayersFactory.getPlayer($scope.data.fields.login).success(function(response) {
                    $scope.data.player = response;
                    $scope.data.isLogged = true;

                }).error(function() {
                    alert('Login or password incorrect!');
                    $scope.data.isLogged = false;

                }).then(function() {
                    if ($scope.data.player.login === $scope.data.loginForm.login.$modelValue &&
                        $scope.data.player.password === $scope.data.loginForm.password.$modelValue) {
                        alert('Logged in!');

                        $modalInstance.close();
                        LoginForModalFactory.login = $scope.data.player.login;
                        $scope.data.isAuthenticated = true;
                        // AuthenticatedFactory.setIsAuthenticated(true);
                        //alert('Is logged: ' + $scope.data.isLogged + '\nIs auth: ' + $scope.data.isAuthenticated);
                    } else {
                        alert('Login or password incorrect!');
                        $scope.data.isAuthenticated = false;
                    }
                });
            };
        }
    ]);