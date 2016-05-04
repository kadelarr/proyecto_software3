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

	this.listarVariedades = function(callback, callerror) {
		$http({
			method : 'POST',
			url : urlBase + 'rest/siembra/listaVariedades'
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
			$scope.siembra = {
					lote:{},
					variedad:{},
					fecha: new Date()
			};
			$scope.lotes = "";
			$scope.variedades = "";
			
			$scope.init = function() {
				$scope.id = $routeParams.id;
				$scope.editando = false;
				if ($scope.id == "new") {
					$scope.user = "";
					SiembraService.listarLotes(function(response) {
						$scope.lotes = response;
					}, function() {
						$scope.desplegarError();
					});

					SiembraService.listarVariedades(function(response) {
						$scope.variedades = response;
					}, function() {
						$scope.desplegarError();
					});
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
					$scope.editarSiembra();
				} else {
					$scope.crearSiembra();
				}
			};
			$scope.crearSiembra = function() {
				SiembraService.crearSiembra(JSON.stringify($scope.siembra),
						function(response) {
							$scope.validarRespuesta(response);
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
				$location.path("/siembra/new");
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
				$location.path("/siembras");
			};
			// DATE
			$scope.today = function() {
				$scope.siembra.fecha = new Date();
			};
			$scope.today();

			$scope.clear = function() {
				$scope.siembra.fecha = null;
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
				$scope.siembra.fecha = new Date(year, month, day);
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