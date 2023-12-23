package main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Ejercicio1 {
	
	public static void getAlumnos(Session s) {
		System.out.println("INICIANDO CONSULTA");
		
		Query<Alumnos> q = s.createQuery("from Alumnos", Alumnos.class);
		List<Alumnos> lista = q.list();
		
		lista.forEach(alumno -> System.out.print(alumno.getNombre()));
	}
}
