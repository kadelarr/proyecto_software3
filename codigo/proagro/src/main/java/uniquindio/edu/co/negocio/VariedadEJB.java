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
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import uniquindio.edu.co.dao.UsuarioDAO;
import uniquindio.edu.co.entidades.Usuario;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class VariedadEJB extends EJBGenerico<Usuario> {

	private UsuarioDAO usuarioDAO;
	@PostConstruct
	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		super.inicializar();
		usuarioDAO = new UsuarioDAO(em);
	}

	@Override
	public void crear(Usuario usuario) {
		if (buscarPorEmail(usuario.getEmail())==null) {
			dao.crear(usuario);
		} else {
			throw new ConstraintViolationException("Email duplicado", null);
		}

	}

	@Override
	public Usuario buscar(Object id) {
		return dao.buscar(id);
	}

	@Override
	public List<Usuario> listarTodos() {
		return dao.listarTodos();
	}

	public Usuario buscarPorEmail(String email) {
		return usuarioDAO.buscarPorEmail(email);
	}
	
	public Boolean login(String email, String password){
		return usuarioDAO.login(email, password);
	}
	
	public Boolean logout(String email){
		return usuarioDAO.logout(email);
	}

}
