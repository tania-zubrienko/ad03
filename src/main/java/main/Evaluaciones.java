package tarea03;
// Generated Dec 25, 2023, 1:15:08 PM by Hibernate Tools 6.3.1.Final

import java.math.BigDecimal;

/**
 * Evaluaciones generated by hbm2java
 */
public class Evaluaciones implements java.io.Serializable {

	private EvaluacionesId id;
	private Asignaturas asignaturas;
	private Alumnos alumnos;
	private BigDecimal nota;

	public Evaluaciones() {
	}

	public Evaluaciones(EvaluacionesId id, Asignaturas asignaturas, Alumnos alumnos, BigDecimal nota) {
		this.id = id;
		this.asignaturas = asignaturas;
		this.alumnos = alumnos;
		this.nota = nota;
	}

	public EvaluacionesId getId() {
		return this.id;
	}

	public void setId(EvaluacionesId id) {
		this.id = id;
	}

	public Asignaturas getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(Asignaturas asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Alumnos getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(Alumnos alumnos) {
		this.alumnos = alumnos;
	}

	public BigDecimal getNota() {
		return this.nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

}
