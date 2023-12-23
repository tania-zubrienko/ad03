package main;
import jakarta.persistence.*;
@Entity
public class Asignaturas implements java.io.Serializable {
	@Id
	private int cod_asig;
	private String nombre;
    @ManyToOne
	@JoinColumn(name="cod_depar", referencedColumnName="cod_depar")
	private int cod_depar;
    @ManyToOne
	@JoinColumn(name="cod_curso", referencedColumnName="cod_curso")
	private int cod_curso;
}
