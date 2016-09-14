angular.module('app.component2', ['ngRoute', 'app.component2.templates', 'ui.bootstrap'])
    .config(function($routeProvider) {
        'use strict';
        $routeProvider.when('/component-2/dialog-b', {
            templateUrl: 'component-2/dialog-b/dialog-b.html',
            controller: 'ManageChallengesController'
        });
    });
