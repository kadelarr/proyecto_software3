package uniquindio.edu.co.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "fertilizacion")
public class Fertilizacion {
	@Id
	@Column(name="id_fertilizacion")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	private Date fecha;
	@NotNull
	private Double UREA;
	@NotNull
	private Double DAP;
	@NotNull
	private Double KCL;
	@ManyToOne
	@JoinColumn(name="id_siembra", nullable=false)
	private SiembraLote siembra;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getUREA() {
		return UREA;
	}
	public void setUREA(Double uREA) {
		UREA = uREA;
	}
	public Double getDAP() {
		return DAP;
	}
	public void setDAP(Double dAP) {
		DAP = dAP;
	}
	public Double getKCL() {
		return KCL;
	}
	public void setKCL(Double kCL) {
		KCL = kCL;
	}
	public SiembraLote getSiembra() {
		return siembra;
	}
	public void setSiembra(SiembraLote siembra) {
		this.siembra = siembra;
	}
}
