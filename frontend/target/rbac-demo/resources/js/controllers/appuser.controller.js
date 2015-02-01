(function(){

    var app = angular.module('appuser-controller', ['appuser-service','ngCookies']);

    app.controller('AppUserController', function($scope, $rootScope, $window, $location, $cookies, AppUserService){

        $scope.user = {'username':$cookies.currentUsername, 'role':$cookies.currentRole};

        if ($scope.user.role=='Admin')
            $location.path('/admin');
        else
            $location.path('/user/'+$scope.user.role);

        $scope.logout = function(){
            AppUserService.logoutUser().success(function(){
                $window.location.replace(ROOT_CONTEXT + '/login');
            });
        }

    });

})();