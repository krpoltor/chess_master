angular.module('app.component2')
    .controller('LoginDialogController', ['$scope', '$modalInstance', 'PlayersFactory', 'LoginForModalFactory', 'AuthenticatedFactory',
        function($scope, $modalInstance, PlayersFactory, LoginForModalFactory, AuthenticatedFactory) {
            'use strict';

            $scope.loginValue = LoginForModalFactory.login;

            $scope.isAuthenticated = false;

            $scope.$watch('isAuthenticated', function(newValue, oldValue) {
                if (newValue !== oldValue) AuthenticatedFactory.setAuthenticated(newValue);
            });

            $scope.login = function() {

                PlayersFactory.getPlayer($scope.data.fields.login).success(function(response) {
                    $scope.data.player = response;

                }).error(function() {
                    alert('Login or password incorrect!');

                }).then(function() {
                    if ($scope.data.player.login === $scope.data.loginForm.login.$modelValue &&
                        $scope.data.player.password === $scope.data.loginForm.password.$modelValue) {
                        alert('Logged in!');

                        $modalInstance.close();
                        LoginForModalFactory.login = $scope.data.player.login;
                        $scope.isAuthenticated = true;

                    } else {
                        alert('Login or password incorrect!');
                        $scope.isAuthenticated = false;
                    }
                });
            };
        }
    ]);
