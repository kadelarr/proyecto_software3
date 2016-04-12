/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Define top level routes for the app, security related views are declated in the security.js
// Note that this app is a single page app, and each partial is routed to using the URL fragment. For example, to select the 'home' route, the URL is http://localhost:8080/Project/#/home
angular.module('Usuario', []);
var appModule = angular.module('Proagro',
		[ 'Usuario', 'ngRoute', 'ui.bootstrap' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/login', {
				templateUrl : 'pages/usuario/login.html',
				controller : 'UsuarioController'
			// Add a default route
			}).when('/about', {
				templateUrl : 'pages/about/about.html',
				controller : 'appctrl'
			// Add a default route
			}).when('/contact', {
				templateUrl : 'pages/contact/contact.html',
				controller : 'appctrl'
			// Add a default route
			}).when('/inicio', {
				templateUrl : 'pages/inicio/inicio.html',
				controller : 'appctrl'
			// Add a default route
			}).when('/usuario/:id', {
				templateUrl : 'pages/usuario/usuario.html',
				controller : 'UsuarioController'
			// Add a default route
			}).when('/usuarios', {
				templateUrl : 'pages/usuario/listarUsuarios.html',
				controller : 'UsuarioController'
			// Add a default route
			}).when('/mapa', {
				templateUrl : 'pages/mapa/mapa.html',
				controller : 'appctrl'
			// Add a default route
			}).otherwise({
				redirectTo : '/inicio'
			});
		} ]).run(
		function($rootScope, $location) {
			$rootScope.$on("$routeChangeStart", function(event, next, current) {
				
				// no logged user, redirect to /login
				var destino=next.originalPath!==undefined?next.originalPath:current.$$route.originalPath;
				if(destino !== undefined){
					if (localStorage.isLogin !== undefined && localStorage.isLogin.length != 0) {
						$rootScope.isLogin = true;
						if (destino === '/login'
								|| destino === '/about'
								|| destino === '/contact'
								|| destino === '/inicio') {
							$location.path('/mapa');
						}
					} else {
						if (destino === '/inicio') {
							$rootScope.showBanner = true;
						} else {
							$rootScope.showBanner = false;
						}
						$rootScope.isLogin = false;
						if (destino !== '/login'
								&& destino !== '/about'
								&& destino !== '/contact'
								&& destino !== '/inicio') {
							$location.path('/login');
						}
						
					}
				}
			});
		}).factory('MessageService', [ '$rootScope', function($rootScope) {
	$rootScope.messages = [];

	var MessageService = function() {
		this.setMessages = function(messages) {
			console.log(messages);
			$rootScope.messages = messages;
			$rootScope.showMessage = true;
		};

		this.hasMessages = function() {
			return $rootScope.messages && $rootScope.messages.length > 0;
		}

		this.clearMessages = function() {
			$rootScope.messages = [];
			$rootScope.showMessage = false;
		}
	};

	return new MessageService();
} ]).controller('appctrl', function($scope, $location, MessageService,UsuarioService) {

	var img = 'http://localhost/webapp/img/';
	$scope.setInterval = 5000;
	$scope.slides = [

	{
		title : 'Imagen 1',
		image : img + '2.jpg'

	}, {
		title : 'Imagen 2',
		image : img + '4.jpg'
	} ];
	$scope.borrarMensaje = function() {
		MessageService.clearMessages();
	}
	
	
	$scope.logout = function (){
		var usuario="email="+localStorage.isLogin;
		UsuarioService.logout(usuario, function(response){
			localStorage.isLogin='';
			$scope.login={};
			$location.path("inicio");
			$rootScope.showBanner = true;
		},function (){
			$scope.desplegarError();
		});
	};
	
	$scope.desplegarError = function() {
		MessageService.setMessages("Error al presentar la operación.");
	};

});