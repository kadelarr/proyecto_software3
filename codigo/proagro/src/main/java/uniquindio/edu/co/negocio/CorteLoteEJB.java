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

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import uniquindio.edu.co.dao.CorteDAO;
import uniquindio.edu.co.dao.SiembraDAO;
import uniquindio.edu.co.entidades.CorteLote;
import uniquindio.edu.co.entidades.SiembraLote;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class CorteLoteEJB extends EJBGenerico<CorteLote> {
	private CorteDAO corteDao;
	private SiembraDAO siembraDao;

	@Override
	@PostConstruct
	public void inicializar() {
		super.inicializar();
		siembraDao = new SiembraDAO(em);
		corteDao = new CorteDAO(em);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CorteLote> listarCortePorLote(String lote) {
		return corteDao.listarCortesPorLote(lote);
	}

	public void crearCorte(CorteLote corte) {
		if (listarCortesSinFechaFin(corte.getSiembra().getLote().getNumero())
				.isEmpty()) {
			if (validarCreacionCorte(corte.getSiembra().getLote().getNumero(),
					corte.getFechaInicio(), corte.getFechaFin())) {
				SiembraLote siembra = siembraDao.buscar(corte.getSiembra().getId());
				corte.setSiembra(siembra);
				corteDao.crear(corte);

			}else {
				throw new IllegalStateException("Se detectarón conflictos entre las fechas que esta intentando crear el corte. Por favor valide y vuelva a intentarlo.");
			}
		} else {
			throw new IllegalStateException("Existen cortes para la siembra seleccionada que aun no se le a asignado fecha fin de corte");
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private boolean validarCreacionCorte(String numero, Date fechaInicio,
			Date fechaFin) {

		return corteDao.validarCreacionCorte(numero, fechaInicio, fechaFin)
				.isEmpty();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<CorteLote> listarCortesSinFechaFin(String numero) {

		return corteDao.listarCortesSinFechaFin(numero);
	}

}
