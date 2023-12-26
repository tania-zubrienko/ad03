package main;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tarea03.Alumnos;
import tarea03.Cursos;

public class Ejercicio4 {
	
	public static List<Cursos> getCursosCentro(Session s, BigInteger codigoCurso) {
		
		String hql = "select c.cursoses \n"
					+ "from Centros c \n"
					+ "where c.codCentro= " + codigoCurso;
		
		Query<Cursos> q = s.createQuery(hql, Cursos.class);
		List<Cursos> lista = q.list();
		
		/*
		for(Cursos c: lista) {
			System.out.println(c.getDenominacion());
		}*/
		
		return lista;
		
		
		
	}

	public static Object getNumAlumnos(Session s, BigInteger codAsig) {
		
		String hql = "SELECT count(e.alumnos)\r\n"
				+ "from Evaluaciones e\r\n"
				+ "WHERE e.id.codAsig = " + codAsig;
		
		Object numAlumnos = s.createQuery(hql, Object.class).uniqueResult();
		return numAlumnos;
		
	}
	
	public static Object getMediaEvaluacion(Session s, BigInteger codAsig, int codEv) {
		
		String hql = "SELECT avg(e.nota) \n"
				+ "from Evaluaciones e \n"
				+ "WHERE e.id.codEvaluacion = " + codEv
				+ "AND e.id.codAsig = " + codAsig;
		
				
		Object nota = s.createQuery(hql, Object.class).uniqueResult();
		return nota;
		
	}

	public static Object getMediaAsignatura(Session s, BigInteger codAsig) {
		
		String hql = "SELECT avg(e.nota) \n"
				+ "from Evaluaciones e \n"
				+ "WHERE e.id.codAsig = " + codAsig;
		
		Object nota = s.createQuery(hql, Object.class).uniqueResult();
		return nota;
	}


}
