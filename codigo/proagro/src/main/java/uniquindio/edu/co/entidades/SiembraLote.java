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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "siembra_lote", uniqueConstraints = @UniqueConstraint(columnNames = {
		"id_lote", "fecha" }))
@NamedQueries({
		@NamedQuery(name = SiembraLote.OBTENER_SIEMBRA_SIN_CORTE, query = "select siembra FROM SiembraLote siembra  LEFT JOIN FETCH siembra.cortes corte WHERE siembra.lote.numero=?1 AND ((siembra.cortes is  EMPTY)  OR corte.fechaFin is NULL))"),
		@NamedQuery(name = SiembraLote.VALIDAR_FECHA_ACTUALIZACION_SIEMBRA, query = "select siembra FROM SiembraLote siembra LEFT JOIN FETCH siembra.cortes corte WHERE siembra.lote=?1 and siembra.id=?2 and ((?3 between siembra.fecha and corte.fechaFin) OR (?4 between siembra.fecha and corte.fechaFin))"),
		@NamedQuery(name = SiembraLote.VALIDAR_FECHA_CREACION_SIEMBRA, query = "select siembra FROM SiembraLote siembra LEFT JOIN FETCH siembra.cortes corte  WHERE siembra.lote.numero=?1 and (?2 between siembra.fecha and corte.fechaFin) "),
		@NamedQuery(name = SiembraLote.LISTAR_SIEMBRAS, query = "select new uniquindio.edu.co.entidades.SiembraLote( siembra.id,MAX(siembra.fecha),siembra.variedad,siembra.lote) FROM SiembraLote siembra LEFT JOIN siembra.cortes corte GROUP BY siembra.lote.numero") })
public class SiembraLote {
	public static final String OBTENER_SIEMBRA_SIN_CORTE = "obtenerSiembraSinCorte";

	public static final String VALIDAR_FECHA_ACTUALIZACION_SIEMBRA = "validarFechaActualizacionSiembra";

	public static final String VALIDAR_FECHA_CREACION_SIEMBRA = "validarFechaCreacionSiembra";

	public static final String LISTAR_SIEMBRAS = "ListarSiembras";

	@Id
	@Column(name = "id_siembra")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "id_variedad", nullable = false)
	private Variedad variedad;
	@ManyToOne
	@JoinColumn(name = "id_lote", nullable = false)
	private Lote lote;

	@OneToMany(mappedBy = "siembra",   fetch=FetchType.EAGER)
	private List<CorteLote> cortes;

	@OneToMany(mappedBy = "siembra",  fetch=FetchType.EAGER)
	private List<Fertilizacion> fertilizaciones;

	@OneToMany(mappedBy = "siembra", fetch = FetchType.EAGER)
	private List<ControlQuimico> contro;

	/**
	 * Constructor de la clase 
	 * 
	 */
	public SiembraLote() {
		super();
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param id
	 * @param fecha
	 * @param variedad
	 * @param lote
	 * @param contro
	 */
	public SiembraLote(Long id, Date fecha, Variedad variedad, Lote lote) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.variedad = variedad;
		this.lote = lote;
	}

	public Date getFecha() {
		return fecha;
	}

	public Long getId() {
		return id;
	}

	public Variedad getVariedad() {
		return variedad;
	}

	public Lote getLote() {
		return lote;
	}

	public List<Fertilizacion> getFertilizaciones() {
		return fertilizaciones;
	}

	public List<ControlQuimico> getContro() {
		return contro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVariedad(Variedad variedad) {
		this.variedad = variedad;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public void setFertilizaciones(List<Fertilizacion> fertilizaciones) {
		this.fertilizaciones = fertilizaciones;
	}

	public void setContro(List<ControlQuimico> contro) {
		this.contro = contro;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Permite obtener el atributo cortes.
	 * 
	 * @author Harold Alexander Jimenez <br/>
	 *         Email: alexjf197@gmail.com
	 * @return the cortes
	 */
	public List<CorteLote> getCortes() {
		return cortes;
	}

	/**
	 * Método que permite cambiar el valor del atributo cortes por el parámetro
	 * cortes.
	 * 
	 * @author Harold Alexander Jimenez <br/>
	 *         Email: alexjf197@gmail.com
	 * 
	 * @param cortes
	 *            es el valor a ser establecido en cortes.
	 */
	public void setCortes(List<CorteLote> cortes) {
		this.cortes = cortes;
	}

}
