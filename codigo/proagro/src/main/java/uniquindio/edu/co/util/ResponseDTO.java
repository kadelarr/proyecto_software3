package uniquindio.edu.co.util;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;



public class ResponseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String mensaje;
	private Object object;
	public ResponseDTO() {
		setCode(Status.OK.getStatusCode());
		mensaje="Operación realizada con exito";
	}
	public ResponseDTO(Integer code, String mensaje) {
		this();
		this.setCode(code);
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}
