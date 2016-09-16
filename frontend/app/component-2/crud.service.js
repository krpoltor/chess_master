angular.module('app.component2')
    .service('CrudService', ['$http','$route', function($http,$route) {
        'use strict';

        this.post = function(players) {
            $http({
                method: 'POST',
                url: '/services/challenge',
                data: players
            }).then(function successCallback(response) {
                // alert('SUCCEED ' + response);
                // $route.reload();
            }, function errorCallback(response) {
                // alert('FAILED ' + response);
                if (response.status == 409) {
                    alert('Cannot challenge this player more then once at the same time!');
                }
            });
        };

        this.put = function(challenge) {
            $http({
                method: 'PUT',
                url: '/services/challenges/' + challenge.id,
                data: challenge
            }).then(function successCallback(response) {
                alert('SUCCEED ' + response.status);
            }, function errorCallback(response) {
                alert('FAILED ' + response.status);
            });
        };

    }]);
