angular.module('Siembra').service('CorteService', function($http) {

	this.crearCorte = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/corte/crearCorte',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.editarCorte = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/corte/editarCorte',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.obtenerCorte = function(data, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/corte/obtenerCorte',
			data : data
		}).success(function(response) {
			callback(response);

		}).error(function() {
			callerror();

		});
	};

	this.listaCortesPorLote = function(numero, callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/corte/listaCortesPorLote',
			data: numero
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};

	this.listarLotes = function(callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/listaLotes'
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};
	
	this.listaSiembrasActuales = function(callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/listaSiembrasActuales'
		}).success(function(response) {
			callback(response);
		}).error(function() {
			callerror();
		});
	};
	
}

).controller(
		'CorteController',
		function($scope, $location, $rootScope, CorteService, MessageService,
				$routeParams) {
			$scope.lote="";
			$scope.titulo;
			$scope.corte = {
					id:"",
					edad:"",
					fechaInicio:new Date(),
					fechaFin:'',
					totalCana:'',
					totalPanela:'',
					rendimiento:'',
					siembra:{}
				
			};
			$scope.lotes = "";
			$scope.siembras = "";
			
			$scope.init = function() {
				$scope.id = $routeParams.id;
				$scope.editando = false;
				CorteService.listaSiembrasActuales(function(response) {
					$scope.siembras = response;
				}, function() {
					$scope.desplegarError();
				});
				if ($scope.id == "new") {
					$scope.titulo="CREAR CORTE";

				} else {
					$scope.titulo="EDITAR CORTE";
					$scope.editando = true;
					CorteService.obtenerCorte($scope.id,
							function(response) {
								if (response.code == 200) {
									$scope.corte = response.object;
									$scope.corte.fechaInicio= new Date(response.object.fechaInicio);
									$scope.corte.fechaFin= new Date(response.object.fechaFin);

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
				CorteService.listarLotes(function(response) {
					$scope.lotes = response;
				}, function() {
					$scope.desplegarError();
				});
				
			};
			
			$scope.listaCortesPorLote =function (){
				if($scope.corte.siembra.lote.numero){
					CorteService.listaCortesPorLote($scope.corte.siembra.lote.numero,function(response) {
						$scope.cortes = response;
					}, function() {
						$scope.desplegarError();
					});
				}
			};

			$scope.realizarOperacion = function() {
				if ($scope.editando) {
					$scope.editarCorte();
				} else {
					$scope.crearCorte();
				}
			};
			$scope.crearCorte = function() {
				CorteService.crearCorte(JSON.stringify($scope.corte),
						function(response) {
							$scope.validarRespuesta(response);
							if(response.code==200){
								$location.path("/cortes");
							}
						}, function() {
							$scope.desplegarError();
						});
			};

			$scope.editarCorte = function() {
				console.log(JSON.stringify($scope.siembra));
				CorteService.crearCorte(JSON.stringify($scope.siembra),
						function(response) {
							$scope.validarRespuesta(response);
							$scope.user = "";
							if(response.code==200){
								$location.path("/cortes");
							}
						}, function() {
							$scope.desplegarError();
						});
			};

			$scope.enviarEditar = function(identificador) {
				$location.path("/corte/" + identificador);
			};

			$scope.enviarCrear = function() {
				$location.path("/corte/new");
			};

			$scope.validarRespuesta = function(response) {
				console.log("respuesta"+response);
				if (response.code === 200) {
					MessageService.setMessages(response.mensaje);
				} else {
					console.log(response);
					MessageService.setMessages(response.mensaje + " Code: "
							+ response.code);
				}
			};

			$scope.reset = function() {
				$location.path("/cortes");
			};
			

		});