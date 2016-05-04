package uniquindio.edu.co.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "lote")
public class Lote {
	@Id
	@Column(name="id_lote")
	private String numero;
	@NotNull
	private Double area;
	@OneToMany(mappedBy="lote", fetch=FetchType.LAZY)
	private List<SiembraLote> siembras;
	public Lote() {
	}
	
	/**
	 * Constructor de la clase 
	 * 
	 * @param numero
	 * @param area
	 */
	public Lote(String numero, Double area) {
		super();
		this.numero = numero;
		this.area = area;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

}
