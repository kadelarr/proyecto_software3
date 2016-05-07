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
package uniquindio.edu.co.negocio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.ConstraintViolationException;

import uniquindio.edu.co.dao.SiembraDAO;
import uniquindio.edu.co.entidades.Lote;
import uniquindio.edu.co.entidades.SiembraLote;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class SiembraEJB extends EJBGenerico<SiembraLote> {

	private SiembraDAO siembraDAO;

	@PostConstruct
	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		super.inicializar();
		siembraDAO = new SiembraDAO(em);
	}

	@Override
	public void crear(SiembraLote siembra) {
		if (obtenerSiembraSinCorte(siembra.getLote()).isEmpty()) {
			if (validarFechaCreacionSiembra(siembra)) {
				dao.crear(siembra);
			} else {
				throw new IllegalArgumentException(
						"Ya existe una siembra con la fecha de siembra dada. Valide que el corte de haya realizado antes de la fecha ingresada.",
						null);
			}
		} else {
			throw new IllegalArgumentException(
					"A la siembra anterior del lote : "
							+ siembra.getLote().getNumero()
							+ " aún no se le ha registrado un corte.", null);
		}

	}

	public void editarSiembra(SiembraLote siembra) {
		if (siembraDAO.validarFechaActualizacionSiembra(siembra)) {
			dao.editar(siembra);
		} else {
			throw new ConstraintViolationException(
					"Ya existe un corte con la fecha de siembra dada.", null);
		}

	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public SiembraLote buscar(Object id) {
		return dao.buscar(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SiembraLote> listarTodos() {
		List<SiembraLote> siembras= dao.listarTodos();
		for (SiembraLote siembraLote : siembras) {
			siembraLote.setCortes(null);
			siembraLote.setContro(null);
			siembraLote.setFertilizaciones(null);
		}
		return dao.listarTodos();
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SiembraLote> obtenerSiembraSinCorte(Lote lote) {
		return siembraDAO.obtenerSiembrasSinCorte(lote);
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean validarFechaActualizacionSiembra(SiembraLote siembraLote) {
		return siembraDAO.validarFechaActualizacionSiembra(siembraLote);
	}
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean validarFechaCreacionSiembra(SiembraLote siembraLote) {
		return siembraDAO.validarFechaCreacionSiembra(siembraLote);
	}
	
	public List<SiembraLote> listarSiembraActuales(){
		return siembraDAO.listarSiembrasActuales();
	}
	
	

}
