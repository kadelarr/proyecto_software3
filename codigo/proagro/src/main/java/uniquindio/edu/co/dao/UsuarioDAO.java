package uniquindio.edu.co.dao;

import javax.persistence.EntityManager;

import uniquindio.edu.co.entidades.Usuario;

import java.util.List;


/**
 * DAO de la persona
 * @author Camilo Andres Ferrer Bustos <caferrerb@gmail.com>
 *
 */
public class UsuarioDAO extends DAO<Usuario> {

	/**
	 * Constructor de la clase.
	 * @param em, entity manager.
	 */
	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	/**
	 * Metodo para buscar una usuario por email.
	 * @param email, email del usuario que se busca.
	 * @return, la lista de personas con un email.
	 */
	public List<Usuario> buscarPorEmail(String email){
		return ejecutarNamedQuery(Usuario.CONSULTAR_USUARIO_EMAIL,email);
	}

}
