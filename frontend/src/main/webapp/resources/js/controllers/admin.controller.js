(function(){

    var app = angular.module('admin-controller', ['admin-service', 'role-service', 'ngSanitize']);

    app.controller('AdminController', function($scope, AdminService, RoleService){

        $scope.users = [];
        $scope.roles = [];
        $scope.userIcon = 'plus';
        $scope.roleIcon = 'plus';

        $scope.listError = undefined;
        $scope.showUserForm = false;
        $scope.showRoleForm = false;
        $scope.registerError = undefined;
        $scope.registerSuccess = undefined;

        $scope.addingUser = {};
        $scope.addingRole = {};

        AdminService.getUsers()
            .success(function(data){
                $scope.users = data;
            })
            .error(function(data){
                $scope.listError = data;
            });

        RoleService.getRoles()
            .success(function(data){
                $scope.roles = data;
                $scope.addingUser.role = $scope.roles[0];
            });

        $scope.manageUserFormView = function(){

            $scope.showUserForm = !$scope.showUserForm;
            $scope.registerError = undefined;
            $scope.registerSuccess = undefined;

            if ($scope.showUserForm==true){
                $scope.userIcon = 'minus';
                $scope.showRoleForm = false;
                $scope.roleIcon = 'plus';
                setTimeout(function(){ window.scrollTo(0,document.body.scrollHeight); }, 200);
            }
            else
                $scope.userIcon = 'plus';

        }

        $scope.manageRoleFormView = function(){

            $scope.showRoleForm = !$scope.showRoleForm;
            $scope.registerError = undefined;
            $scope.registerSuccess = undefined;

            if ($scope.showRoleForm==true){
                $scope.roleIcon = 'minus';
                $scope.showUserForm = false;
                $scope.userIcon = 'plus';
                setTimeout(function(){ window.scrollTo(0,document.body.scrollHeight); }, 200);
            }
            else
                $scope.roleIcon = 'plus';

        }

        $scope.registerUser = function(){
            AdminService.registerAppUser($scope.addingUser)
                    .success(function(newUser){
                        $scope.registerError = undefined;
                        $scope.showUserForm = false;
                        $scope.userIcon = 'plus';
                        $scope.addingUser = {}; $scope.addingUser.role = $scope.roles[0];
                        $scope.users.push(newUser);
                        $scope.registerSuccess = 'User successfully registered!';
                    })
                    .error(function(data){
                        $scope.registerError = data;
                    });

        };

        $scope.registerRole = function(){
            AdminService.registerRole($scope.addingRole)
                    .success(function(newRole){
                        $scope.registerError = undefined;
                        $scope.showRoleForm = false;
                        $scope.roleIcon = 'plus';
                        $scope.addingRole = {};
                        $scope.roles.push(newRole);
                        $scope.registerSuccess = 'Role successfully registered!';
                    })
                    .error(function(data){
                        $scope.registerError = data;
                    });

        };

    });

})();