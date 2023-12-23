package main;

import jakarta.persistence.*;

@Entity
public class Evaluaciones  implements java.io.Serializable {
	
	@Id
	private int cod_avaluacion;
	@Id
	@ManyToOne
	@JoinColumn(name = "num_alumno", referencedColumnName = "num_alumno")
	private int num_alumno;
	@Id
	@JoinColumn(name = "cod_asignatura", referencedColumnName = "cod_asignatura")
	private int cod_asignatura;
	private int nota;
	
	private int getCod_avaluacion() {
		return cod_avaluacion;
	}
	private void setCod_avaluacion(int cod_avaluacion) {
		this.cod_avaluacion = cod_avaluacion;
	}
	private int getNum_alumno() {
		return num_alumno;
	}
	private void setNum_alumno(int num_alumno) {
		this.num_alumno = num_alumno;
	}
	private int getCod_asignatura() {
		return cod_asignatura;
	}
	private void setCod_asignatura(int cod_asignatura) {
		this.cod_asignatura = cod_asignatura;
	}
	private int getNota() {
		return nota;
	}
	private void setNota(int nota) {
		this.nota = nota;
	}
}
