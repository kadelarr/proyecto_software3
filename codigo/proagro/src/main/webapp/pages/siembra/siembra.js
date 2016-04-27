angular.module('Siembra').service('SiembraService', function($http) {

	this.crearSiembra = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/crearSiembra',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.editarSiembra = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/editarSiembra',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.obtenerSiembra = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/obtenerSiembra',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.listarSiembra = function(callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/listarSiembra'
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};
}

).controller(
		'SiembraController',
		function($scope, $location, $rootScope, SiembraService, MessageService,
				$routeParams) {
			$scope.siembra = "";
			$scope.lotes = "";
			$scope.variedades = "";

			$scope.init = function() {
				$scope.id = $routeParams.id;
				$scope.editando = false;
				if ($scope.id == "new") {
					$scope.user = "";
				} else {
					$scope.editando = true;
					SiembraService.obtenerSiembra($scope.id,
							function(response) {
								if (response.code == 200) {
									$scope.siembra = response.object;

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
				SiembraService.listarSiembra(function(response) {

					$scope.siembras = response;

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
			$scope.crearSiembra = function() {
				console.log(JSON.stringify($scope.siembra));
				SiembraService.crearSiembra(JSON.stringify($scope.siembra),
						function(response) {
							$scope.validarRespuesta(response);
							$scope.user = "";
							$location.path("/siembras");
						}, function() {
							$scope.desplegarError();
						});
			};

			$scope.editarSiembra = function() {
				console.log(JSON.stringify($scope.siembra));
				SiembraService.crearSiembra(JSON.stringify($scope.siembra),
						function(response) {
							$scope.validarRespuesta(response);
							$scope.user = "";
							$location.path("/siembras");
						}, function() {
							$scope.desplegarError();
						});
			};

			$scope.enviarEditar = function(identificador) {
				$location.path("/siembra/" + identificador);
			};
			
			$scope.enviarCrear = function() {
				$location.path("/siembra/new" );
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

			$scope.reset = function() {
				$location.path("/siembras");
			};

		});