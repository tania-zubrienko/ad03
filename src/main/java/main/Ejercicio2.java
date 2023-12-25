package main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tarea03.*;

public class Ejercicio2 {

	
	public static boolean existeAlumno(Session s, int cod) {
		BigInteger codAlumno = BigInteger.valueOf(cod);
		
		Alumnos a = s.createQuery("from Alumnos a where a.numAlumno = :codAlumno", Alumnos.class)
					 .setParameter("codAlumno", codAlumno)
					 .uniqueResult();
		if(a!=null) {
			return true;
		}
		return false;
	}
	
	public static Set<BigInteger> getAsignaturas(Session s, int cod){
		BigInteger codAlumno = BigInteger.valueOf(cod);
		Set<BigInteger> asignaturas = new HashSet<>();

		String hql = "select a.codAsig \n"
				+ "from Asignaturas a \n"
				+ "join a.cursos c \n"
				+ "join c.alumnoses al \n"
				+ "where al.numAlumno = " + codAlumno;
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator<Object> i = lista.iterator();
		
		while(i.hasNext()) {
			asignaturas.add((BigInteger) i.next());
		}
		
		return asignaturas;
	}

	public static boolean comprobarAsignatura(Session s, Set<BigInteger> asignaturas, int asignatura, int numAlumno, int evaluacion) {
		
		if(asignaturas.contains(BigInteger.valueOf(asignatura))) {
			
			String hql = "SELECT e.id.codEvaluacion \n"
					+ "FROM Evaluaciones e \n"
					+ "WHERE e.id.numAlumno = " +  numAlumno
					+ "  AND e.id.codAsig =  " + asignatura;
			
			Query<Object> q = s.createQuery(hql, Object.class);
			List<Object> lista = q.list();
			Iterator<Object> i = lista.iterator();
			
			Set<BigInteger> evaluaciones = new HashSet<>();
			while(i.hasNext()) {
				evaluaciones.add((BigInteger) i.next());
			}
			
			if(evaluaciones.contains(BigInteger.valueOf(evaluacion))) {
				System.out.println("YA EXISTE NOTA PARA EL ALUMNO EN ESA ASIG Y EVALUACIÓN");
				return false;
			}
			return true;
		}else {
			System.out.println("Numero de asignatura no valido");
			return false;
		}
	}

	public static Evaluaciones insertarEvaluacion(Session s, int numAlumno, int asignatura, int evaluacion, int nota) {
	
		BigInteger alumno = BigInteger.valueOf(numAlumno);
		BigInteger asig = BigInteger.valueOf(asignatura);
		BigInteger ev = BigInteger.valueOf(evaluacion);
		BigDecimal n = new BigDecimal(nota);
		
		EvaluacionesId nuevoId = new EvaluacionesId(asig, ev, alumno);
		Evaluaciones nuevaEvaluacion = new Evaluaciones();
		nuevaEvaluacion.setId(nuevoId);
		nuevaEvaluacion.setNota(n);
		nuevaEvaluacion.setAlumnos(s.get(Alumnos.class, alumno));
		nuevaEvaluacion.setAsignaturas(s.get(Asignaturas.class, asig));

		return nuevaEvaluacion;
		
	}
	
}
