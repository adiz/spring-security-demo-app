(function(){

    var app = angular.module('login-controller', ['login-service','ngCookies']);

    app.controller('LoginController', function($scope, $rootScope, $window, $cookies, LoginService){

        $scope.user = {'username':'','password':''};

        $scope.loginUser = function(){
            LoginService.getAppUser($scope.user)
                    .success(function(role){
                        $cookies.currentUsername = $scope.user.username;
                        $cookies.currentRole = role;
                        $window.location.replace(ROOT_CONTEXT);
                    })
                    .error(function(data){
                        $scope.loginError = data;
                    });

        };

    });

})();