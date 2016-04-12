package uniquindio.edu.co.entidades;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
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
	@Column(name="id_siembra")
	private Long id;
	@NotNull
	private String nombre;
	@NotNull
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_variedad",  nullable = false)
	private Variedad variedad;
	@ManyToOne
	@JoinColumn(name = "id_lote",  nullable = false)
	private Lote lote;

	@OneToMany(mappedBy = "siembra")
	private List<Fertilizacion> fertilizaciones;
	
	@OneToMany(mappedBy = "siembra")
	private List<ControlQuimico> contro;
	
	@OneToOne
	@JoinColumn(name = "id_corte", nullable = false)
	private CorteLote corte;

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
