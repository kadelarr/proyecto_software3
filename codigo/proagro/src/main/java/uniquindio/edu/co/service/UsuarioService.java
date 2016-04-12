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
package uniquindio.edu.co.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uniquindio.edu.co.entidades.Usuario;
import uniquindio.edu.co.negocio.UsuarioEJB;
import uniquindio.edu.co.util.ResponseDTO;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/usuario")
@RequestScoped
@Stateful
public class UsuarioService {


	@Inject
	private UsuarioEJB usuarioEJB;

	@POST
	@Path("/listarUsuarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuarios() {
		return usuarioEJB.listarTodos();
	}

	// @GET
	// @Path("/{id:[0-9][0-9]*}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Member lookupMemberById(@PathParam("id") long id) {
	// Member member = repository.findById(id);
	// if (member == null) {
	// throw new WebApplicationException(Response.Status.NOT_FOUND);
	// }
	// return member;
	// }

	/**
	 * Creates a new member from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Path("/crearUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO crearUsuario(Usuario usuario) {

		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (usuario != null && usuario.getEmail() != null
					&& !usuario.getEmail().isEmpty()) {
				usuarioEJB.crear(usuario);
			} else {
				throw new ValidationException("Unique Email Violation");
			}
			// Create an "ok" response

		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error en integridad datos");
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("email", "Ya existe un correo registrado");
			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje("Ya existe un correo registrado");
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error realizando la operación");
		}

		return builder;
	}

	@POST
	@Path("/editarUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO editarUsuario(Usuario usuario) {
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (usuario != null && usuario.getEmail() != null
					&& !usuario.getEmail().isEmpty()) {
				Usuario user = usuarioEJB.buscar(usuario.getIdentificacion());
				user.setApellido(usuario.getApellido());
				user.setEmail(usuario.getEmail());
				user.setNombre(usuario.getNombre());
				user.setPassword(usuario.getPassword());
				user.setRol(usuario.getRol());
				user.setTelefono(usuario.getTelefono());
				usuarioEJB.actualizar(user);
			} else {
				throw new ValidationException("Unique Email Violation");
			}
			// Create an "ok" response

		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error en integridad datos");
		} catch (ValidationException e) {
			// Handle the unique constrain violation

			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje("Ya existe un correo registrado");
		} catch (Exception e) {
			// Handle generic exceptions
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error realizando la operación");
		}
		return builder;
	}

	@POST
	@Path("/obtenerUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO obtenerUsuario(String id) {
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (id != null && !id.isEmpty()) {
				Usuario user = usuarioEJB.buscar(id);
				builder.setObject((Object) user);

			} else {
				throw new ValidationException("Se envio el identificador vacio");
			}
			// Create an "ok" response
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje(e.getMessage());
		} catch (Exception e) {
			// Handle generic exceptions
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error realizando la operación");
		}
		return builder;
	}
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO login(@FormParam("email") String email,
			@FormParam("password") String password) {
		
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (email != null && password != null && !email.isEmpty()
					&& !password.isEmpty()) {

				Boolean login = usuarioEJB.login(email, password);
				builder.setObject((Object) login);
				if(!login){
					builder.setMensaje("EL usuario no se encuentra registrado o no coincide la contraseña");
					builder.setCode(Response.Status.UNAUTHORIZED.getStatusCode());
				}

			} else {
				throw new ValidationException("Se enviaron datos vacios  "+email+" "+password);
			}
			// Create an "ok" response
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje(e.getMessage());
		} catch (Exception e) {
			// Handle generic exceptions
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error realizando la operación");
		}
		return builder;
	}
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO logout(@FormParam("email") String email) {
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (email != null && !email.isEmpty()) {

				builder.setObject((Object) usuarioEJB.logout(email));

			} else {
				throw new ValidationException("Se enviaron datos vacios");
			}
			// Create an "ok" response
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje(e.getMessage());
		} catch (Exception e) {
			// Handle generic exceptions
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje("Error realizando la operación");
		}
		return builder;
	}
}
