package tarea03;
// Generated Dec 25, 2023, 1:15:08 PM by Hibernate Tools 6.3.1.Final

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Alumnos generated by hbm2java
 */
public class Alumnos implements java.io.Serializable {

	private BigInteger numAlumno;
	private Cursos cursos;
	private String nombre;
	private String direccion;
	private String tlf;
	private BigDecimal notaMedia;
	private Set evaluacioneses = new HashSet(0);

	public Alumnos() {
	}

	public Alumnos(BigInteger numAlumno, Cursos cursos, String nombre, String direccion, String tlf,
			BigDecimal notaMedia, Set evaluacioneses) {
		this.numAlumno = numAlumno;
		this.cursos = cursos;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlf = tlf;
		this.notaMedia = notaMedia;
		this.evaluacioneses = evaluacioneses;
	}

	public BigInteger getNumAlumno() {
		return this.numAlumno;
	}

	public void setNumAlumno(BigInteger numAlumno) {
		this.numAlumno = numAlumno;
	}

	public Cursos getCursos() {
		return this.cursos;
	}

	public void setCursos(Cursos cursos) {
		this.cursos = cursos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public BigDecimal getNotaMedia() {
		return this.notaMedia;
	}

	public void setNotaMedia(BigDecimal notaMedia) {
		this.notaMedia = notaMedia;
	}

	public Set getEvaluacioneses() {
		return this.evaluacioneses;
	}

	public void setEvaluacioneses(Set evaluacioneses) {
		this.evaluacioneses = evaluacioneses;
	}
	@Override
	public String toString() {
		return "ID: " + this.numAlumno +
				"\n" + "Nombre: " + this.nombre +
				"\n" +"Curso: " + this.cursos.getDenominacion() + 
				"\n" +"Nota media " + this.notaMedia + 
				"\n" +"Num de evaluaciones " + this.evaluacioneses.size();
				
 	}

}
