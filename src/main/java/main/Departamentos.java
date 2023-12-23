package main;
import jakarta.persistence.*;
@Entity
public class Departamentos implements java.io.Serializable {
	@Id
	private int cod_depar;
	private String nombre_depar;
	private int num_asig;
	
	private int getCod_depar() {
		return cod_depar;
	}
	private void setCod_depar(int cod_depar) {
		this.cod_depar = cod_depar;
	}
	private String getNombre_depar() {
		return nombre_depar;
	}
	private void setNombre_depar(String nombre_depar) {
		this.nombre_depar = nombre_depar;
	}
	private int getNum_asig() {
		return num_asig;
	}
	private void setNum_asig(int num_asig) {
		this.num_asig = num_asig;
	}
}
