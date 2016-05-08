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

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uniquindio.edu.co.entidades.Lote;
import uniquindio.edu.co.entidades.SiembraLote;
import uniquindio.edu.co.entidades.Variedad;
import uniquindio.edu.co.negocio.LoteEJB;
import uniquindio.edu.co.negocio.SiembraEJB;
import uniquindio.edu.co.negocio.VariedadEJB;
import uniquindio.edu.co.util.ResponseDTO;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/siembra")
@RequestScoped
@Stateful
public class SiembraService {

	@Inject
	private SiembraEJB siembraEJB;

	@Inject
	private LoteEJB loteEJB;

	@Inject
	private VariedadEJB variedadEJB;

	@POST
	@Path("/listarSiembra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SiembraLote> listarSiembraLotes() {
		return siembraEJB.listarTodos();
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Path("/crearSiembra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO crearSiembra(SiembraLote siembraLote) {

		ResponseDTO builder = new ResponseDTO();

		try {
			if (siembraLote != null && siembraLote.getFecha() != null
					&& siembraLote.getLote() != null
					&& siembraLote.getVariedad() != null) {
				siembraEJB.crear(siembraLote);
			} else {
				throw new ValidationException(
						"Ingrese datos de manera inadecuada");
			}
			// Create an "ok" response

		} catch (EJBTransactionRolledbackException ce) {
			// Handle bean validation issues
			builder.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			builder.setMensaje(ce.getMessage());
		} catch (ValidationException e) {
			builder.setCode(Response.Status.CONFLICT.getStatusCode());
			builder.setMensaje(e.getMessage());
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
	@Path("/editarSiembraLote")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO editarSiembraLote(SiembraLote siembraLote) {
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (siembraLote != null && siembraLote.getFecha() != null
					&& siembraLote.getLote() != null
					&& siembraLote.getVariedad() != null) {
				SiembraLote siembra = siembraEJB.buscar(siembraLote.getId());
				if (siembra != null) {
//					siembra.setCortes(siembraLote.getCortes());
					siembra.setFecha(siembraLote.getFecha());
					siembra.setVariedad(siembraLote.getVariedad());
					siembra.setLote(siembraLote.getLote());

					siembraEJB.actualizar(siembra);
				} else {
					throw new WebApplicationException();
				}
			} else {
				throw new ValidationException(
						"Ingrese datos de manera adecuada");
			}
			// Create an "ok" response

		} catch (WebApplicationException ce) {
			// Handle bean validation issues
			builder.setCode(Response.Status.NOT_FOUND.getStatusCode());
			builder.setMensaje("No se encontro una siembra con el id para actualizar.");
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
	@Path("/obtenerSiembra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO obtenerSiembra(String id) {
		ResponseDTO builder = new ResponseDTO();

		try {
			// Validates member using bean validation
			if (id != null && !id.isEmpty()) {
				SiembraLote siembra = siembraEJB.buscar(Long.valueOf(id));
				
				if (siembra != null) {
					siembra.setContro(null);
					siembra.setCortes(null);
					siembra.setFertilizaciones(null);
					builder.setObject((Object) siembra);
				} else
					throw new WebApplicationException();

			} else {
				throw new ValidationException("Se envio el identificador vacio");
			}
			// Create an "ok" response
		} catch (WebApplicationException ce) {
			// Handle bean validation issues
			builder.setCode(Response.Status.NOT_FOUND.getStatusCode());
			builder.setMensaje("No se encontro una siembra con el id: " + id);
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
	@Path("/listaLotes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lote> listaLotes() {
		return loteEJB.listarTodos();
	}

	
	@POST
	@Path("/listaSiembrasActuales")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SiembraLote> listaSiembrasActuales() {
		return siembraEJB.listarSiembraActuales();
	}
	
	@POST
	@Path("/listaVariedades")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Variedad> listaVariedades() {
		return variedadEJB.listarTodos();
	}
}
