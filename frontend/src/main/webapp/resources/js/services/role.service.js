(function(){

    var app = angular.module('role-service', [])
                     .factory('RoleService', function($http){

        return{

            getRoles: function(){
                return $http.get(ROOT_CONTEXT + '/roles');
            },

            getRoleInfo: function(roleName){
                return $http.get(ROOT_CONTEXT + '/roles/roleInfo', {params: {roleName: roleName}});
            }

        }

    });

})();