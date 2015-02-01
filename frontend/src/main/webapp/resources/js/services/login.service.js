(function(){

    var app = angular.module('login-service', [])
                     .factory('LoginService', function($http){

        return{

            getAppUser: function(user){
                var config = { headers : {'Content-Type': 'application/x-www-form-urlencoded'} };
                return $http.post(ROOT_CONTEXT + '/login', $.param(user), config);
            }

        }

    });

})();