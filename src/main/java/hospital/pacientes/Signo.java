package hospital.pacientes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vital_signs")
public class Signo {

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date string) {
		this.fecha = string;
	}

	@Id
	@Column(name="ID_VS")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSigno;
	
	@Column(name="ID")
	private Integer idPaciente;
	
	@Column(name="SURNAMES")
	private String apellidos;
	
	@Column(name="SATURATION")
	private double saturacion;
	
	@Column(name="TEMPERATURE")
	private int temperatura;
	
	@Column(name="DATE")
	private Date fecha;

	public Integer getIdSigno() {
		return idSigno;
	}

	public void setIdSigno(Integer idSigno) {
		this.idSigno = idSigno;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSaturacion() {
		return saturacion;
	}

	public void setSaturacion(double saturacion) {
		this.saturacion = saturacion;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	


}
