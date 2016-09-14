angular.module('app.component2')
    .controller('LoginDialogController', ['$scope', '$modalInstance', 'PlayersFactory', 'LoginForModalService', 'AuthenticatedService',
    function($scope, $modalInstance, PlayersFactory, LoginForModalService, AuthenticatedService) {
        'use strict';

        $scope.data.loginValue = LoginForModalService.login;
        $scope.data.authenticated = AuthenticatedService.authenticated;

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
                    LoginForModalService.login = $scope.data.player.login;

                } else {
                    alert('Login or password incorrect!');
                    // LoginForModalService.login(false);
                }
            });
        };
    }]);
