/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquindio.edu.co.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * DAO Generico del que heredaran todos los otros DAOs.
 * 
 * @author Camilo Andres Ferrer Bustos <caferrerb@gmail.com>
 */
public class DAO<T> {

	/**
	 * Tipo de objeto que maneja este DAO.
	 */
	private Class<T> type;
	/**
	 * Entitymanager.
	 */
	protected EntityManager em;
	

	/**
	 * Constructor.
	 * 
	 * @param em
	 */
	public DAO(EntityManager em) {
		this.em = em;
	}

	/**
	 * MEtodoo para buscar por llave primaria.
	 * 
	 * @param <T>, tipo de dato a buscar
	 * @param pk
	 *            , llave primaria de la entidad.
	 * @return
	 */
	public T buscar(Object pk) {
		return em.find(getType(), pk);
	}

	/**
	 * Metodo generico para editar una entidad.
	 * 
	 * @param entidad
	 *            , la entidad a editar.
	 */
	public void editar(T entidad) {
		em.merge(entidad);
	}

	/**
	 * metodo para eliminar una entidad.
	 * 
	 * @param entidad
	 *            , la entidad.
	 */
	public void eliminar(T entidad) {
		em.remove(entidad);
	}

	/**
	 * Metodo para ejecutar una NamedQuery.
	 * 
	 * @param nombreNamedQuery
	 *            , el nombre de la namedQuery
	 * @param params
	 *            , la lista de parametros.
	 * @return, la lista de resultado.
	 */
	public <T> List<T> ejecutarNamedQuery(String nombreNamedQuery,
			Object... params) {
		Query q = em.createNamedQuery(nombreNamedQuery);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]);
		}
		return q.getResultList();
	}

	/**
	 * Metodo para listar todos los objetos de la entidad.
	 * 
	 * @return la lista de objetos.
	 */
	public List<T> listarTodos() {
		Query q = em.createQuery("SELECT t FROM " + getType().getSimpleName()
				+ " t");
		return q.getResultList();
	}

	/**
	 * metodo para crear una entidad.
	 * 
	 * @param entidad
	 *            , la entidad.
	 */
	public void crear(T entidad) {

		em.persist(entidad);
	}

	/**
	 * Utilitario para saber el tipo de EJB que es esta instancia.
	 * 
	 * @return el class de este EJB:
	 * @throws Exception
	 */
	public Class<T> getType() {
		if (type == null) {
			ParameterizedType superclass = (ParameterizedType) getClass()
					.getGenericSuperclass();

			return (Class<T>) superclass.getActualTypeArguments()[0];
		} else {
			return type;
		}
	}
	/**
	 * Tipo de este DAO.
	 * @param type
	 */
	public void setType(Class<T> type) {
		this.type = type;
	}

}