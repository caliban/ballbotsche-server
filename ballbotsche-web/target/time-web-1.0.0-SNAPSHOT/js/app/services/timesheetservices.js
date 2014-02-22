'use strict';

angular.module('timesheet.services', []);

angular.module('timesheet.services')
        .factory('CurrentSheetService', ['$http', '$q', function($http,$q) {
                return{
                    loadSheet: function()
                    {
                        var deferredUserFromApi = $q.defer();
                        $http.get("rest/timestamp").then(
                                function(response) {
                                    deferredUserFromApi.resolve(response.data);
                                }, function(error) {
                            deferredUserFromApi.reject(error);
                        });
                        return deferredUserFromApi.promise;
                    }
                }
            }
        ]);
        