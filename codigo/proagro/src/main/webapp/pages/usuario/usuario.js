angular.module('Usuario').service('UsuarioService', function($http) {

	this.crearusuario = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/crearUsuario',
			data : data
		}).success(function(response) {
			callback(response);
			console.log("sucessfull " + JSON.stringify(response));
		}).error(function() {
			callerror();
			console.log("error");
		});
	};

	this.login = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/login',
			data : data,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};

	this.logout = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/logout',
			data : data,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};

	this.editarUsuario = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/editarUsuario',
			data : data
		}).success(function(response) {
			callback(response);
			console.log("sucessfull " + JSON.stringify(response));
		}).error(function() {
			callerror();
			console.log("error");
		});
	};

	this.obtenerUsuario = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/obtenerUsuario',
			data : data
		}).success(function(response) {
			callback(response);
			console.log("sucessfull " + JSON.stringify(response));
		}).error(function() {
			callerror();
			console.log("error");
		});
	};

	this.listarUsuarios = function(callback, callerror) {
		$http({
			method : 'POST',
			url : 'http://uckkf894d257.carmenhoyos9.koding.io:8080/proagro/rest/usuario/listarUsuarios'
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};
}

).controller(
		'UsuarioController',
		function($scope, $location,$rootScope, UsuarioService, MessageService,
				$routeParams) {
			$scope.user = "";
			$scope.usuario = {
				email : '',
				password : ''
			}

			$scope.inicializar = function() {
				$scope.id = $routeParams.id;
				$scope.editando = false;
				if ($scope.id == "new") {
					$scope.user = "";
				} else {
					$scope.editando = true;
					UsuarioService.obtenerUsuario($scope.id,
							function(response) {
								if (response.code == 200) {
									$scope.user = response.object;

								} else {
									MessageService
											.setMessages(response.mensaje);
								}
							}, function() {
								$scope.desplegarError();
							});
				}

			};

			$scope.inicializarList = function() {
				UsuarioService.listarUsuarios(function(response) {

					$scope.usuarios = response;

				}, function() {
					$scope.desplegarError();
				});
			};

			$scope.realizarOperacion = function() {
				if ($scope.editando) {
					$scope.editarUsuario();
				} else {
					$scope.crearusuario();
				}
			};
			$scope.crearusuario = function() {
				console.log(JSON.stringify($scope.user));
				UsuarioService.crearusuario(JSON.stringify($scope.user),
						function(response) {
							$scope.validarRespuesta(response);
							$scope.user = "";
							$location.path("login");
						}, function() {
							$scope.desplegarError();
						});
			};

			$scope.editarUsuario = function() {
				console.log(JSON.stringify($scope.user));
				UsuarioService.editarUsuario(JSON.stringify($scope.user),
						function(response) {
							$scope.validarRespuesta(response);
							$scope.user = "";
						}, function() {
							$scope.desplegarError();
						});
			};
			
			$scope.enviarEditar = function(identificador) {
				$location.path("/usuario/"+identificador);
			};

			$scope.validarRespuesta = function(response) {
				console.log(JSON.stringify(response));
				if (response.code === 'OK') {
					MessageService.setMessages(response.mensaje);
				} else {
					MessageService.setMessages(response.mensaje + " Code: "
							+ response.code);
				}
			};

			$scope.login = function() {
				console.log($scope.usuario);
				var usurios = "email=" + $scope.usuario.email + "&password="
						+ $scope.usuario.password;
				UsuarioService.login(usurios, function(response) {
					$scope.validarRespuesta(response);
					if (response.code == 200) {
						localStorage.isLogin = $scope.usuario.email;
						$scope.login = {};
						$location.path("inicio");
					}
				}, function() {
					$scope.desplegarError();
				});
			};

		});