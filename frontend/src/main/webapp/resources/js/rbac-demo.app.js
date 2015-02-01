(function(){

    /*-- main app --*/
    angular.module('rbac-demo', ['appuser-controller','admin-controller','user-controller','ngRoute'])
    .config(function($routeProvider, $locationProvider){

            $routeProvider
                .when('/admin',{
                    templateUrl: 'views/admin.html',
                    controller: 'AdminController'
                })
                .when('/user/:role',{
                    templateUrl: 'views/user.html',
                    controller: 'UserController'
                })
                .otherwise({
                    redirectTo: '/'
                });

            $locationProvider.hashPrefix('!');

    });

})();