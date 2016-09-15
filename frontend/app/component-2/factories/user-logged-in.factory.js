angular.module('app.component2')
    .factory('UserLoggedInFactory', function() {
        'use strict';

        var data = {
            isLoggedIn: false
        };

        return {
            getIsLoggedIn: function() {
                return data.isLoggedIn;
            },
            setIsLoggedIn: function(state) {
                data.isLoggedIn = state;
            }
        };

    });
