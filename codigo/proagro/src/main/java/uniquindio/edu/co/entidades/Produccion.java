package uniquindio.edu.co.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "produccion")
public class Produccion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_produccion")
	private Long id;
	@Column(name = "peso_cana")
	private Double pesoCana;
	@Column(name = "peso_panela")
	private Double pesoPanela;
	@Column(name="pct_extraccion")
	private Double pcExtraccion;
	
	@ManyToOne
	@JoinColumn(name="id_corte", nullable=false)
	private CorteLote corte;
	
	public Double getPesoCana() {
		return pesoCana;
	}
	public void setPesoCana(Double pesoCana) {
		this.pesoCana = pesoCana;
	}
	public Double getPesoPanela() {
		return pesoPanela;
	}
	public void setPesoPanela(Double pesoPanela) {
		this.pesoPanela = pesoPanela;
	}
	public Double getPcExtraccion() {
		return pcExtraccion;
	}
	public void setPcExtraccion(Double pcExtraccion) {
		this.pcExtraccion = pcExtraccion;
	}
	public CorteLote getCorte() {
		return corte;
	}
	public void setCorte(CorteLote corte) {
		this.corte = corte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
