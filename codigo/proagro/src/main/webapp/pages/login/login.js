angular.module('Login').controller('LoginController', function($scope) {
    $scope.email = "John";
    $scope.password = "Doe";
    $scope.calcularNombre =function(){
    	$scope.fullName = $scope.email+ " " + $scope.password;
    };
});