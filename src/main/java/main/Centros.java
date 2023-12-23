package main;
import jakarta.persistence.*;

@Entity
public class Centros implements java.io.Serializable{

	@Id
	private int cod_centro;
	private String nombre;
	private String localidad;
	private String tlf;
	private int num_alumnos;
	private int num_cursos;
	private int presupuesto;
	
	private int getCod_centro() {
		return cod_centro;
	}
	private void setCod_centro(int cod_centro) {
		this.cod_centro = cod_centro;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String getLocalidad() {
		return localidad;
	}
	private void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	private String getTlf() {
		return tlf;
	}
	private void setTlf(String tlf) {
		this.tlf = tlf;
	}
	private int getNum_alumnos() {
		return num_alumnos;
	}
	private void setNum_alumnos(int num_alumnos) {
		this.num_alumnos = num_alumnos;
	}
	private int getPresupuesto() {
		return presupuesto;
	}
	private void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	private int getNum_cursos() {
		return num_cursos;
	}
	private void setNum_cursos(int num_cursos) {
		this.num_cursos = num_cursos;
	}
}
