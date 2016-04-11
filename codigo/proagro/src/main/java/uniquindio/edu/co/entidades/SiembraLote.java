package uniquindio.edu.co.entidades;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "siembra_lote")
public class SiembraLote {
	@Id
	private Long id;
	@NotNull
	private String nombre;
	@NotNull
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_variedad", referencedColumnName = "id", nullable = false)
	private Variedad variedad;
	@ManyToOne
	@JoinColumn(name = "id_lote", referencedColumnName = "numero", nullable = false)
	private Lote lote;
	@OneToOne
	@JoinColumn(name = "id_corte", referencedColumnName = "id", nullable = false)
	private CorteLote corte;
	@OneToMany(mappedBy = "siembra")
	private List<Fertilizacion> fertilizaciones;
	@OneToMany(mappedBy = "siembra")
	private List<ControlQuimico> contro;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
