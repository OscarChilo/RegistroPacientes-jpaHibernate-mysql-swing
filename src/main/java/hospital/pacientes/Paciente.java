package hospital.pacientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Paciente {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPaciente;
	
    @Column(name="DNI")
	private String dni;
    
    @Column(name="NAMES")
	private String nombres;
    
    @Column(name="SURNAMES")
	public String apellidos;
    
    @Column(name="NUMBERPHONE")
	private String nroCel;
    
    @Column(name="GENDER")
	private String genero="";

    
	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNroCel() {
		return nroCel;
	}

	public void setNroCel(String nroCel) {
		this.nroCel = nroCel;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	

	

}
