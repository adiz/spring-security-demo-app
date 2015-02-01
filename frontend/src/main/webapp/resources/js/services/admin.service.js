(function(){

    var app = angular.module('admin-service', [])
                     .factory('AdminService', function($http){

        return{

            getUsers: function(){
                return $http.get(ROOT_CONTEXT + '/admin/users');
            },

            registerAppUser: function(user){
                return $http.post(ROOT_CONTEXT + '/admin/register', user);
            },

            registerRole: function(role){
                return $http.post(ROOT_CONTEXT + '/admin/role', role);
            }

        }

    });

})();