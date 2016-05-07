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
			$scope.corte = {
					id:"",
					edad:"",
					fechaInicio:new Date(),
					fechaFin:new Date(),
					totalCana:0,
					totalPanela:0,
					rendimiento:0,
					siembra:{}
				
			};
			$scope.lotes = "";
			$scope.siembras = "";
			
			$scope.init = function() {
				$scope.id = $routeParams.id;
				$scope.editando = false;
				if ($scope.id == "new") {
					$scope.user = "";
					CorteService.listaSiembrasActuales(function(response) {
						$scope.siembras = response;
					}, function() {
						$scope.desplegarError();
					});

					CorteService.listarVariedades(function(response) {
						$scope.variedades = response;
					}, function() {
						$scope.desplegarError();
					});
				} else {
					$scope.editando = true;
					CorteService.obtenerSiembra($scope.id,
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
				CorteService.crearCorte(JSON.stringify($scope.siembra),
						function(response) {
							$scope.validarRespuesta(response);
							$location.path("/cortes");
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
							$location.path("/cortes");
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
			// DATE
			$scope.today = function() {
				$scope.corte.fechaInicio = new Date();
			};
			$scope.today();

			$scope.clear = function() {
				$scope.siembra.fechaInicio = null;
			};

			$scope.dateOptions = {
				dateDisabled : disabled,
				formatYear : 'yy',
				maxDate : new Date(2020, 5, 22),
				minDate : new Date(),
				startingDay : 1
			};

			// Disable weekend selection
			function disabled(data) {
				var date = data.date, mode = data.mode;
				return mode === 'day'
						&& (date.getDay() === 0 || date.getDay() === 6);
			}

			$scope.open = function() {
				$scope.popup1.opened = true;
			};

			$scope.setDate = function(year, month, day) {
				$scope.siembra.fechaInicio = new Date(year, month, day);
			};

			$scope.format = 'yyyy/MM/dd';

			$scope.popup1 = {
				opened : false
			};

			var tomorrow = new Date();
			tomorrow.setDate(tomorrow.getDate() + 1);
			var afterTomorrow = new Date();
			afterTomorrow.setDate(tomorrow.getDate() + 1);
			$scope.events = [ {
				date : tomorrow,
				status : 'full'
			}, {
				date : afterTomorrow,
				status : 'partially'
			} ];

			function getDayClass(data) {
				var date = data.date, mode = data.mode;
				if (mode === 'day') {
					var dayToCheck = new Date(date)
							.setHours(0, 0, 0, 0);

					for ( var i = 0; i < $scope.events.length; i++) {
						var currentDay = new Date($scope.events[i].date)
								.setHours(0, 0, 0, 0);

						if (dayToCheck === currentDay) {
							return $scope.events[i].status;
						}
					}
				}

				return '';
			}

		});