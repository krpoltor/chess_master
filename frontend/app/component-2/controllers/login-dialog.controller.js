angular.module('app.component2')
    .controller('LoginDialogController',['$scope', '$modalInstance', 'PlayersFactory', 'LoginForModalFactory',
    function($scope, $modalInstance, PlayersFactory, LoginForModalFactory) {
        'use strict';

        $scope.loginValue = LoginForModalFactory.login;


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
                    // AuthenticatedModalService.setAuthenticated(true);
                    // AuthenticatedModalService.setLogin($scope.data.fields.login);
                      LoginForModalFactory.login = $scope.data.player.login;
                      alert('');

                } else {
                    alert('Login or password incorrect!');
                    AuthenticatedModalService.setAuthenticated(false);
                }

            });
        };


    }]);
