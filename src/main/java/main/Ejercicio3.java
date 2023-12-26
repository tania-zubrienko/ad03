package main;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tarea03.Alumnos;

public class Ejercicio3 {

	public static Object[] getInfoCentro(Session s, BigInteger codCurso) {
		Object[] infoCentro = null;
		String hql = "SELECT cen.nombre, cen.localidad \n"
				+ "FROM Centros cen \n"
				+ "JOIN cen.cursoses c \n"
				+ "WHERE c.codCurso = " + codCurso ;
		
		Query<Object[]> q = s.createQuery(hql, Object[].class);	
		List<Object[]> lista = q.list();		
		for(Object[] centro : lista) {
			infoCentro = centro;
		}
		return infoCentro;
	}
	
	public static List<Object[]> getEvalulacionesAlumno(Session s, BigInteger codAlumno) {
		
		String hql = "SELECT AVG(e.nota) \n"
				+ "	FROM Evaluaciones e \n"
				+ "	WHERE e.id.numAlumno = " + codAlumno + "\n"
				+ "	GROUP BY e.id.codEvaluacion";
		
		Query<Object[]> q = s.createQuery(hql, Object[].class);	
		List<Object[]> lista = q.list();
		
		while(lista.size()<3) {
			lista.add(new Object[]{"0"});
		}
		
		return lista;
	}

public static String alumnoMaxNota(Session s, BigInteger codCurso) {
	
	String hql = "FROM Alumnos a \n"
			+ "WHERE a.cursos.codCurso = " + codCurso
			+ "ORDER BY a.notaMedia DESC";
	
	Query<Alumnos> q = s.createQuery(hql, Alumnos.class);
	List<Alumnos> lista = q.list();
	
	Alumnos a = lista.get(0);
	return (a.getNombre());
	
	
}

}
