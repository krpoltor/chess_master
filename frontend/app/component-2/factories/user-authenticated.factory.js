angular.module('app.component2')
    .factory('AuthenticatedFactory', function() {
        'use strict';

        var data = {
            isAuthenticated: ''
        };

        return {
            getAuthenticated: function() {
                return data.isAuthenticated;
            },
            setAuthenticated: function(firstName) {
                data.isAuthenticated = firstName;
            }
        };

    });
