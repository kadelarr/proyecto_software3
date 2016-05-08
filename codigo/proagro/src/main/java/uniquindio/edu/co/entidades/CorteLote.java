package uniquindio.edu.co.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@NamedQuery(name = CorteLote.OBTENER_ULTIMO_CORTE, query = "Select corteLote FROM CorteLote corteLote WHERE corteLote.id= (select MAX(corte.id) FROM CorteLote corte  WHERE corte.siembra.lote=?1)"),
@NamedQuery(name = CorteLote.LISTADO_DE_CORTES, query = "Select corteLote FROM CorteLote corteLote WHERE corteLote.siembra.lote.numero= ?1 ORDER BY corteLote.fechaInicio DESC"),
@NamedQuery(name = CorteLote.VALIDAR_CREACION_CORTE, query = "Select corteLote FROM CorteLote corteLote WHERE corteLote.siembra.lote.numero= ?1 AND (?2 BETWEEN corteLote.fechaInicio and corteLote.fechaFin OR ?3 between siembra.fecha and corteLote.fechaFin OR corteLote.fechaInicio BETWEEN ?2 AND ?3) ORDER BY corteLote.fechaInicio DESC"),
@NamedQuery(name = CorteLote.LISTAR_CORTE_SIN_FECHA_FIN, query = "Select corteLote FROM CorteLote corteLote WHERE corteLote.siembra.lote.numero= ?1 AND corteLote.fechaFin IS NULL"),
@NamedQuery(name = CorteLote.NUMERO_CORTES_POR_SIEMBRA, query = "Select COUNT(corteLote) FROM CorteLote corteLote WHERE corteLote.siembra.id= ?1"),
@NamedQuery(name = CorteLote.FECHA_FIN_CORTES_POR_SIEMBRA, query = "Select MAX(corteLote.fechaFin) FROM CorteLote corteLote WHERE corteLote.siembra.id= ?1")



 })
public class CorteLote {

	public static final String OBTENER_ULTIMO_CORTE = "obtenerIdUltimoCorte";
	public static final String LISTADO_DE_CORTES = "listado_de_cortes";
	public static final String VALIDAR_CREACION_CORTE = "validar_creacion_corte";
	public static final String LISTAR_CORTE_SIN_FECHA_FIN ="obtener_corte_sin_fecha_fin";	
	public static final String NUMERO_CORTES_POR_SIEMBRA ="numero_cortes_por_siembra";
	public static final String FECHA_FIN_CORTES_POR_SIEMBRA ="fecha_fin_cortes_por_siembra";
	@Id
	@Column(name = "id_corte")
	@GeneratedValue(strategy=GenerationType.AUTO)
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

	@OneToMany(mappedBy = "corte", fetch=FetchType.EAGER)
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

	/**
	 * Permite obtener el atributo siembra.
	 *
	 * @author Harold Alexander Jimenez <br/>
	 * 		   Email: alexjf197@gmail.com 
	 * @return the siembra
	 */
	public SiembraLote getSiembra() {
		return siembra;
	}

	/**
	 * Método que permite cambiar el valor del atributo siembra por el parámetro siembra.
	 * 
	 * @author Harold Alexander Jimenez <br/>
	 * 		   Email: alexjf197@gmail.com 
	
	 * @param siembra es el valor a ser establecido en  siembra.
	 */
	public void setSiembra(SiembraLote siembra) {
		this.siembra = siembra;
	}

	/**
	 * Permite obtener el atributo produccion.
	 *
	 * @author Harold Alexander Jimenez <br/>
	 * 		   Email: alexjf197@gmail.com 
	 * @return the produccion
	 */
	public List<Produccion> getProduccion() {
		return produccion;
	}

	/**
	 * Método que permite cambiar el valor del atributo produccion por el parámetro produccion.
	 * 
	 * @author Harold Alexander Jimenez <br/>
	 * 		   Email: alexjf197@gmail.com 
	
	 * @param produccion es el valor a ser establecido en  produccion.
	 */
	public void setProduccion(List<Produccion> produccion) {
		this.produccion = produccion;
	}
}
