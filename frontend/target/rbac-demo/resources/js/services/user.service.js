(function(){

    var app = angular.module('user-service', [])
                     .factory('UserService', function($http){

        return{

            logoutUser: function(){
                return $http.post(ROOT_CONTEXT + '/logout');
            }

        }

    });

})();