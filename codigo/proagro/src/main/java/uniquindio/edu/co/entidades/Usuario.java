package uniquindio.edu.co.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

import uniquindio.edu.co.util.RolEnum;

@Entity
@XmlRootElement
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({ @NamedQuery(name = Usuario.CONSULTAR_USUARIO_EMAIL, query = "select user FROM Usuario user where user.email = ?1") })
public class Usuario {
	@Id
	private String identificacion;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@Email(message = "Invalid format")
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolEnum rol;
	private String telefono;
	@NotNull
	private String login;
	@NotNull
	private String password;

	public static final String CONSULTAR_USUARIO_EMAIL = "consultar_usuario_email";

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RolEnum getRol() {
		return rol;
	}

	public void setRol(RolEnum rol) {
		this.rol = rol;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
