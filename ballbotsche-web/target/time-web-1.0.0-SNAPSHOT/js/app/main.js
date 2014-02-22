'use strict';
/* Configuration of other Modules */
angular.module('LocalStorageModule')
        .value('prefix', 'time')
        ;

angular.module('services.navigation.config')
        .value('NAVIGATION_CONFIG', {
            loginState: 'login',
            errorState: 'error'
        })
        ;

angular.module('time', [
    'ui.router',
    'ngCookies',
    'LocalStorageModule',
    'services.authentication',
    'services.navigation',
    'services.navigation.config',
    'timesheet.services']
        )
        .config(function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/dashboard");

            $stateProvider
                    .state('login', {
                        url: "/login",
                        templateUrl: "partials/login.html",
                        controller: 'LoginCtrl'
                    }).state('dashboard', {
                url: '/dashboard',
                templateUrl: 'partials/dashboard.html',
                controller: 'DashboardCtrl',
                resolve: {
                     timesheet: function(CurrentSheetService, $q)
                    {
                        return CurrentSheetService.loadSheet();
                        
                    },
                    currentUser: function(AuthService) {
                        return AuthService.getCurrentUser();
                    }
                   
                }
            })
        });