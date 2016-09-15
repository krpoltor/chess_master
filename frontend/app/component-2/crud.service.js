angular.module('app.component2')
    .service('CrudService', ['$http', function($http) {
        'use strict';

        this.post = function(whitePlayer,blackPlayer) {
            $http({
                method: 'POST',
                url: '/services/challenge/',
                data: whitePlayer,blackPlayer
            }).then(function successCallback(response) {
                alert('SUCCEED ' + response.status + response);
            }, function errorCallback(response) {
                alert('FAILED ' + response.status);
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
