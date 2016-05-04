package uniquindio.edu.co.dao;

import java.util.List;

import javax.persistence.EntityManager;

import uniquindio.edu.co.entidades.CorteLote;
import uniquindio.edu.co.entidades.Lote;
import uniquindio.edu.co.entidades.SiembraLote;

public class SiembraDAO extends DAO<SiembraLote> {

	/**
	 * Constructor de la clase.
	 * 
	 * @param em
	 *            , entity manager.
	 */
	public SiembraDAO(EntityManager em) {
		super(em);
	}

	public List<SiembraLote> obtenerSiembrasSinCorte(Lote lote) {
		return ejecutarNamedQuery(SiembraLote.OBTENER_SIEMBRA_SIN_CORTE,
				lote.getNumero());
	}

	public CorteLote obtenerUltimoCorte(Lote lote) {
		List<CorteLote> ultimoCorte = ejecutarNamedQuery(
				CorteLote.OBTENER_ULTIMO_CORTE, lote);
		if (ultimoCorte.isEmpty()) {
			return null;
		}
		return ultimoCorte.get(0);
	}

	public boolean validarFechaActualizacionSiembra(SiembraLote siembra) {
		// CorteLote corte = siembra.getCortes();
		// List<CorteLote> ultimoCorte = ejecutarNamedQuery(
		// SiembraLote.VALIDAR_FECHA_ACTUALIZACION_SIEMBRA,
		// siembra.getLote(),
		// siembra.getId(),
		// siembra.getFecha(),
		// corte != null ? corte.getFechaFin() != null ? corte
		// .getFechaFin() : "" : "");
		// if (ultimoCorte.isEmpty()) {
		// return true;
		// }
		return false;
	}

	public boolean validarFechaCreacionSiembra(SiembraLote siembra) {
		List<CorteLote> ultimoCorte = ejecutarNamedQuery(
				SiembraLote.VALIDAR_FECHA_CREACION_SIEMBRA, siembra.getLote()
						.getNumero(), siembra.getFecha());
		if (ultimoCorte.isEmpty()) {
			return true;
		}
		return false;
	}

}
