angular.module('app.component2')
    .controller('ManageChallengesController', function($scope, PlayersFactory) {
        'use strict';

        $scope.data = {
            player: [],
            loginForm: []
        };

        $scope.login = function() {

            PlayersFactory.getPlayer($scope.data.fields.login).success(function(response) {
                $scope.data.player = response;

            }).error(function() {
                alert('Login or password incorrect!');

            }).then(function() {
                if ($scope.data.player.login === $scope.data.loginForm.login.$modelValue &&
                    $scope.data.player.password === $scope.data.loginForm.password.$modelValue) {
                    alert('Logged in!');

                } else {
                    alert('Login or password incorrect!');
                }

            });
        };
    });
