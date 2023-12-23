package main;
import jakarta.persistence.*;

@Entity
public class Alumnos implements java.io.Serializable{

	@Id
	private int num_alumno;
	@ManyToOne
	@JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso")
	private int cod_curso;
	private String nombre;
	private String direccion;
	private String telefono;
	private Double notaMedia;
	
	public int getNum_alumno() {
		return num_alumno;
	}
	public void setNum_alumno(int num_alumno) {
		this.num_alumno = num_alumno;
	}
	public int getCod_curso() {
		return cod_curso;
	}
	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Double getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(Double notaMedia) {
		this.notaMedia = notaMedia;
	}
	
}
