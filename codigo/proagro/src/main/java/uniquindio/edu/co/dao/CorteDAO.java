package uniquindio.edu.co.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import uniquindio.edu.co.entidades.CorteLote;

public class CorteDAO extends DAO<CorteLote>{
	public CorteDAO(EntityManager em) {
		super(em);
	}

	public List<CorteLote> listarCortesPorLote(String lote){
		return ejecutarNamedQuery(CorteLote.LISTADO_DE_CORTES, lote);
	}
	
	public List<CorteLote> listarCortesSinFechaFin(String lote){
		return ejecutarNamedQuery(CorteLote.LISTAR_CORTE_SIN_FECHA_FIN, lote);
	}
	
	public List<CorteLote> validarCreacionCorte(String numero,Date fechaInicio,Date fechaFin) {
		return ejecutarNamedQuery(CorteLote.VALIDAR_CREACION_CORTE,numero,fechaInicio,fechaFin);
	}

}
