package uniquindio.edu.co.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	public Long numeroCortesPorSiembra(Long id) {
		Query query =em.createNamedQuery(CorteLote.NUMERO_CORTES_POR_SIEMBRA);
		query.setParameter(1,  id);
		return (Long) query.getSingleResult();
	}
	
	public Date MayorFechaCortesPorSiembra(Long id) {
		Query query =em.createNamedQuery(CorteLote.FECHA_FIN_CORTES_POR_SIEMBRA);
		query.setParameter(1,  id);
		return (Date) query.getSingleResult();
	}

}
