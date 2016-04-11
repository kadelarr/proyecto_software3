package uniquindio.edu.co.negocio;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uniquindio.edu.co.dao.DAO;


/**
 * EJB generico.
 * @author Camilo Andres Ferrer Bustos
 *
 * @param <T>
 */
public class EJBGenerico<T> {
	
	/**
	 * Entity Manager.
	 */
	@PersistenceContext
	protected EntityManager em;
	
	/**
	 * DAO generico.
	 */
	protected DAO<T> dao;
	
	/**
	 * Inicializar el EJB.
	 */
	@PostConstruct
	public void inicializar(){
		dao=new DAO<T>(em);
		dao.setType(getType());
	}
	
	
	/**
	 * Metodo para crear la entidad.
	 * @param entidad
	 */
	public void crear(T entidad){
		dao.crear(entidad);
	}
	
	/**
	 * Metodo para actualizar la entidad.
	 * @param entidad
	 */
	public void actualizar(T entidad){
		dao.editar(entidad);
	}
	
	/**
	 * Metodo para buscar  la entidad.
	 * @param llave primaria
	 * @return la entidad
	 */
	public T buscar(Object pk){
		return dao.buscar(pk);
	}
	
	
	/**
	 * Metodo para eliminar la entidad.
	 * @param entidad
	 */
	public void eliminar(T entidad){
		dao.eliminar(entidad);
	}
	
	/**
	 * Metodo para listar todos los objetos de la entidad.
	 * @return la lista de objetos.
	 */
	public List<T> listarTodos(){
		return dao.listarTodos();
	}

	 /**
     * Utilitario para saber el tipo de EJB que es esta instancia.
     *
     * @return el class de este EJB:
     * @throws Exception
     */
    public Class<T> getType() {
        ParameterizedType superclass = (ParameterizedType) getClass()
                .getGenericSuperclass();

        return (Class<T>) superclass.getActualTypeArguments()[0];
    }
}
