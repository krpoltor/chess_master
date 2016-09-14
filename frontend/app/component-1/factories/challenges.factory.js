angular.module('app.component1')
    .factory('ChallengesFactory', ['$http', function($http) {
        'use strict';

        var factory = {};

        factory.getChallenges = function() {
            return $http.get('http://localhost:8090/services/challenges');
        };

        factory.getPlayerChallenges = function(login) {
            return $http.get('http://localhost:8090/services/challenges/byLogin/' + login);
        };

        return factory;

    }]);
