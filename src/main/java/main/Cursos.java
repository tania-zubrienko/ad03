package main;
import jakarta.persistence.*;

@Entity
public class Cursos implements java.io.Serializable {

	@Id
	private int cod_curso;
	@ManyToOne
	@JoinColumn(name = "cod_centro", referencedColumnName = "cod_centro")
	private int cod_centro;
	private String denominaion;
	private int coste_matricula;
	private int num_alumnos;
	

	private int getCod_centro() {
		return cod_centro;
	}
	private void setCod_centro(int cod_centro) {
		this.cod_centro = cod_centro;
	}
	private String getDenominaion() {
		return denominaion;
	}
	private void setDenominaion(String denominaion) {
		this.denominaion = denominaion;
	}
	private int getCoste_matricula() {
		return coste_matricula;
	}
	private void setCoste_matricula(int coste_matricula) {
		this.coste_matricula = coste_matricula;
	}
	private int getNum_alumnos() {
		return num_alumnos;
	}
	private void setNum_alumnos(int num_alumnos) {
		this.num_alumnos = num_alumnos;
	}
	private int getCod_curso() {
		return cod_curso;
	}
	private void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	
	
}
