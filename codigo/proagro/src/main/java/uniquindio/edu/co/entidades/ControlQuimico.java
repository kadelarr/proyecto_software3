package uniquindio.edu.co.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "control_quimico")
public class ControlQuimico {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date fecha;
	private String estado;
	@NotNull
	private Double GASAPAX;
	@NotNull
	private Double HORMEX;
	@NotNull
	private Double AMINA;
	@NotNull
	private Double CALLISTO;
	@NotNull
	private Double IMEX;
	@ManyToOne
	@JoinColumn(name="id_siembra",referencedColumnName="id", nullable=false)
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getGASAPAX() {
		return GASAPAX;
	}
	public void setGASAPAX(Double gASAPAX) {
		GASAPAX = gASAPAX;
	}
	public Double getHORMEX() {
		return HORMEX;
	}
	public void setHORMEX(Double hORMEX) {
		HORMEX = hORMEX;
	}
	public Double getAMINA() {
		return AMINA;
	}
	public void setAMINA(Double aMINA) {
		AMINA = aMINA;
	}
	public Double getCALLISTO() {
		return CALLISTO;
	}
	public void setCALLISTO(Double cALLISTO) {
		CALLISTO = cALLISTO;
	}
	public Double getIMEX() {
		return IMEX;
	}
	public void setIMEX(Double iMEX) {
		IMEX = iMEX;
	}
	public SiembraLote getSiembra() {
		return siembra;
	}
	public void setSiembra(SiembraLote siembra) {
		this.siembra = siembra;
	}
	

}
