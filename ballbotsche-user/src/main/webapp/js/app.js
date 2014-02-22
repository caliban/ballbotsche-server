'use strict';
/* http://docs.angularjs.org/#!angular.service */

// Declare app level module which depends on filters, and services
angular.module('myApp', [ 'myApp.filters']).
  config(['$routeProvider', function($routeProvider) {

    $routeProvider.when('/login', {templateUrl: 'partials/login.html',   controller: LoginCtrl});
    $routeProvider.when('/register',  {templateUrl: 'partials/register.html', controller: RegisterCtrl});
    $routeProvider.otherwise({redirectTo: '/login'});

    //$locationProvider.html5Mode(true);

    /*$rootScope.$on('$afterRouteChange', function(){
      $window.scrollTo(0,0);
    });*/
  }]);