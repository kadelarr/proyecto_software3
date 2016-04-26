package uniquindio.edu.co.dao;

import javax.persistence.EntityManager;

import uniquindio.edu.co.entidades.Usuario;

import java.util.List;


public class UsuarioDAO extends DAO<Usuario> {

	/**
	 * Constructor de la clase.
	 * 
	 * @param em
	 *            , entity manager.
	 */
	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	/**
	 * Metodo para buscar una usuario por email.
	 * 
	 * @param email
	 *            , email del usuario que se busca.
	 * @return, la lista de personas con un email.
	 */
	public Usuario buscarPorEmail(String email) {
		List<Usuario> usuarios = ejecutarNamedQuery(
				Usuario.CONSULTAR_USUARIO_EMAIL, email);
		if (usuarios.isEmpty()) {
			return null;
		} else {
			return usuarios.get(0);
		}
	}

	public boolean login(String email, String password) {

		Usuario usuario = buscarPorEmail(email);
		if (usuario != null && usuario.getPassword().equals(password)) {
			usuario.setSesionIniciada(Boolean.TRUE);
			editar(usuario);
			return Boolean.TRUE;
		}

		return Boolean.FALSE;

	}

	public boolean logout(String email) {
		if (email != null && !email.isEmpty()) {
			Usuario usuario = buscarPorEmail(email);
			if (usuario != null) {
				usuario.setSesionIniciada(Boolean.FALSE);
				editar(usuario);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;

	}

}
