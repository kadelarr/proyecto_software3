package uniquindio.edu.co.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
public class EntidadPersistencia {
	private Boolean estado;
//	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
}
