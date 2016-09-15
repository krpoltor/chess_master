angular.module('app.component2')
    .factory('AuthenticatedFactory', function() {
        'use strict';

        var data = {
            isAuthenticated: false,
            isLogged: false
        };

        return {
            getIsAuthenticated: function() {
                return data.isAuthenticated;
            },
            setIsAuthenticated: function(state) {
                data.isAuthenticated = state;
            },
            getIsLogged: function() {
                return data.isLogged;
            },
            setIsLogged: function(state) {
                data.isLogged = state;
            }
        };

    });
