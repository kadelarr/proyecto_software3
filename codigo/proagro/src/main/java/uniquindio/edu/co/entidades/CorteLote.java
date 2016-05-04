package uniquindio.edu.co.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "corte_lote")
@NamedQueries({

@NamedQuery(name = CorteLote.OBTENER_ULTIMO_CORTE, query = "Select corteLote FROM CorteLote corteLote WHERE corteLote.id= (select MAX(corte.id) FROM CorteLote corte  WHERE corte.siembra.lote=?1)")
 })
public class CorteLote {

	public static final String OBTENER_ULTIMO_CORTE = "obtenerIdUltimoCorte";
	public static final String OBTENER_ANTERIOR_CORTE = "obtenerAnteriorCorte";
	@Id
	@Column(name = "id_corte")
	private Long id;
	@NotNull
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	@NotNull
	@Column(name = "fecha_fin")
	private Date fechaFin;
	private Long edad;
	@NotNull
	@Column(name = "numero_corte")
	private Long numeroCorte;
	@Column(name = "total_cana")
	private Double totalCana;
	@Column(name = "total_panela")
	private Double totalPanela;

	private Double rendimiento;

	@ManyToOne
	@JoinColumn(name="id_siembra",nullable=false)
	private SiembraLote siembra;

	@OneToMany(mappedBy = "corte")
	private List<Produccion> produccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public Long getNumeroCorte() {
		return numeroCorte;
	}

	public void setNumeroCorte(Long numeroCorte) {
		this.numeroCorte = numeroCorte;
	}

	public Double getTotalCana() {
		return totalCana;
	}

	public void setTotalCana(Double totalCana) {
		this.totalCana = totalCana;
	}

	public Double getTotalPanela() {
		return totalPanela;
	}

	public void setTotalPanela(Double totalPanela) {
		this.totalPanela = totalPanela;
	}

	public Double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(Double rendimiento) {
		this.rendimiento = rendimiento;
	}
}
