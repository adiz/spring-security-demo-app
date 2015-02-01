(function(){

    var app = angular.module('user-controller', ['user-service', 'role-service']);

    app.controller('UserController', function($scope, UserService, RoleService){

        $scope.roles = [];
        $scope.validRole = [];
        $scope.hackedRole = [];
        $scope.retrievedRole = [];
        $scope.submitted = [];

        RoleService.getRoles()
            .success(function(data){
                $scope.roles = data;
            });

        $scope.getRoleInfo = function(roleName){
            RoleService.getRoleInfo(roleName)
                .success(function(data){
                   $scope.validRole[roleName] = true;
                   $scope.retrievedRole[roleName] = {'info':''};
                   $scope.retrievedRole[roleName].info = data;
                   $scope.submitted[roleName] = true;
                })
                .error(function(data){
                    $scope.hackedRole[roleName] = true;
                    $scope.retrievedRole[roleName] = {'messageForScam':''};
                    $scope.retrievedRole[roleName].messageForScam = data;
                    $scope.submitted[roleName] = true;
                });
        }

    });

})();