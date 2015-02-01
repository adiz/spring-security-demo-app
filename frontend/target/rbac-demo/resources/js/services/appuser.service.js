(function(){

    var app = angular.module('appuser-service', [])
                     .factory('AppUserService', function($http){

        return{

            logoutUser: function(){
                return $http.post(ROOT_CONTEXT + '/logout');
            }

        }

    });

})();