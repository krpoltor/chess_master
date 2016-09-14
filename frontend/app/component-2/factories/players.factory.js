angular.module('app.component2')
    .factory('PlayersFactory', ['$http', function($http) {
        'use strict';

        var factory = {};

        factory.getPlayer = function(playerLogin) {
            return $http.get('http://localhost:8090/services/user/' + playerLogin);
        };

        return factory;

    }]);
