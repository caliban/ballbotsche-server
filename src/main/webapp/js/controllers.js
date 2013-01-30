/* 
 * Copyright 2013 caliban.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function RouteCtrl($route) {

    var self = this;

    $route.when('/register', {template:'tpl/register.html'});
    $route.when('/index', {template:'index.html'});

    $route.otherwise({redirectTo:'/index'});

    $route.onChange(function () {
        self.params = $route.current.params;
    });

    $route.parent(this);

}
function LoginController($resource) {
  $http({method: 'GET', url: '/someUrl'}).
  success(function(data, status, headers, config) {
    // this callback will be called asynchronously
    // when the response is available
  }).
  error(function(data, status, headers, config) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });
  );
}
 
BuzzController.prototype = {
  fetch: function() {
    this.activities = this.Activity.get({userId:this.userId});
  },
  expandReplies: function(activity) {
    activity.replies = this.Activity.replies({userId:this.userId, activityId:activity.id});
  }
};
BuzzController.$inject = ['$resource'];