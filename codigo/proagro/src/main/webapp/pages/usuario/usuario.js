angular.module('Usuario')
.service('UsuarioService',  function($http) {

   
   this.crearusuario = function(data,callback,callerror){
        $http(
          { method: 'POST',
            url: 'http://localhost:8082/proagro/rest/usuario/crearUsuario',
            data: data }).
          success(function (response) {
                callback(response);
                console.log("sucessfull "+JSON.stringify(response));
          }).
          error(function () {
            callerror();
            console.log("error");
          });
      };

      this.editarUsuario = function(data,callback,callerror){
        $http(
          { method: 'POST',
            url: 'http://localhost:8082/proagro/rest/usuario/editarUsuario',
            data: data }).
          success(function (response) {
                callback(response);
                console.log("sucessfull "+JSON.stringify(response));
          }).
          error(function () {
            callerror();
            console.log("error");
          });
      };

       this.obtenerUsuario = function(data,callback,callerror){
        $http(
          { method: 'POST',
            url: 'http://localhost:8082/proagro/rest/usuario/obtenerUsuario',
            data: data }).
          success(function (response) {
                callback(response);
                console.log("sucessfull "+JSON.stringify(response));
          }).
          error(function () {
            callerror();
            console.log("error");
          });
      };
    }
  
)
.controller('UsuarioController', function($scope,$location, UsuarioService,MessageService,$routeParams) {
    $scope.user="";

    $scope.inicializar = function(){
      $scope.id = $routeParams.id;
      $scope.editando=false;
      if($scope.id == "new"){
        $scope.user="";
      } else {
          $scope.editando=true;
          UsuarioService.obtenerUsuario($scope.id,
            function(response){
              if(response.code == 'OK'){
                 $scope.user=response.object;

              }else {
                MessageService.setMessages(response.mensaje);
              }
            },function(){
              $scope.desplegarError();
            });
      }
      
      console.log(id);
    };

    $scope.realizarOperacion = function(){
      if($scope.editando){
        $scope.editarUsuario();
      } else {
        $scope.crearusuario();
      }
    };
    $scope.crearusuario =function(){
      console.log(JSON.stringify($scope.user));
      UsuarioService.crearusuario(JSON.stringify($scope.user),function(response){
        $scope.validarRespuesta(response);
        $scope.user="";
        $location.path("login");
      },function(){
        $scope.desplegarError();
      });
    };


    $scope.editarUsuario =function(){
      console.log(JSON.stringify($scope.user));
        UsuarioService.editarUsuario(JSON.stringify($scope.user),function(response){
        $scope.validarRespuesta(response);
        $scope.user="";
      },function(){
        $scope.desplegarError();
      });
    };

    $scope.validarRespuesta = function (response){
      console.log(JSON.stringify(response));
      if(response.code === 'OK'){
        MessageService.setMessages(response.mensaje);
      }else {
        MessageService.setMessages(response.mensaje+" Code: "+response.code);
      }
    }

    $scope.desplegarError = function (){
        MessageService.setMessages("Error al presentar la operación.");
  
    }
});