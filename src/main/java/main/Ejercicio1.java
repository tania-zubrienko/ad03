package main;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tarea03.Departamentos;

public class Ejercicio1 {
	
	//ACTUALIZAR INFO DEL CENTRO. Apartado a)
	
	public static void actualizarCentro(Session s, BigInteger codCentro) {
		
		BigInteger numCursos = Ejercicio1.getNumCursos(s, codCentro);
		BigInteger numAlumnos = Ejercicio1.getNumAlumnos(s, codCentro);
		
		String hql = "UPDATE Centros c set c.numAlumnos = :numAlumnos, c.numCursos = :numCursos " +
							"WHERE c.codCentro = :codCentro";
		int filasModif = s.createMutationQuery(hql)
				  		  .setParameter("numAlumnos", numAlumnos)
				  		  .setParameter("numCursos", numCursos)
				  		  .setParameter("codCentro", codCentro)
				  		  .executeUpdate();
		if(filasModif > 0) {
			System.out.println("Cod Centro: " + codCentro + ", Num.Cursos: " + numCursos + ", Num.Alumnos: " + numAlumnos);
		}else {
			System.out.println("No se ha modificado ninguna fila");
		}
		
	}
	private static BigInteger getNumCursos(Session s, BigInteger codCentro) {
		BigInteger numCursos = null;
		String hql = "SELECT COUNT(c.codCurso) \n"
				+ "FROM Centros cen \n"
				+ "JOIN cen.cursoses c \n"
				+ "WHERE cen.codCentro = " + codCentro;
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator <Object> iter = lista.iterator();
		
		if(iter.hasNext()) {
			long resultado =  (long) lista.get(0);
			numCursos = BigInteger.valueOf(resultado);
		}
		return numCursos;
	}
	private static BigInteger getNumAlumnos(Session s, BigInteger codCentro) {
		BigInteger numAlumnos = null;
		
		String hql="SELECT COUNT(a.numAlumno) \n"
				+ "FROM Centros cen\n"
				+ "JOIN cen.cursoses c \n"
				+ "JOIN c.alumnoses a \n"
				+ "where cen.codCentro = " + codCentro;
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator <Object> iter = lista.iterator();
		
		if(iter.hasNext()) {
			long resultado =  (long) lista.get(0);
			numAlumnos = BigInteger.valueOf(resultado);
		}
		return numAlumnos;
	}
	public static void actualizarCurso(Session s, BigInteger codCurso) {
		BigInteger numAlumnos = Ejercicio1.getAlumnosPorCurso(s, codCurso);
		String hql = "UPDATE Cursos c set c.numAlumnos = :numAlumnos WHERE c.codCurso = :codCurso";
		int filasModif = s.createMutationQuery(hql)
			  		  .setParameter("numAlumnos", numAlumnos)
			  		  .setParameter("codCurso", codCurso)
			  		  .executeUpdate();
		if(filasModif<=0) {
			System.out.println("Se ha producido un error");
		}
	}
	private static BigInteger getAlumnosPorCurso(Session s, BigInteger codCurso) {
		BigInteger numAlumnos = null;
		
		String hql = "SELECT COUNT(a) \n"
				+ "FROM Cursos c \n"
				+ "JOIN c.alumnoses a \n"
				+ "WHERE c.codCurso = " + codCurso;
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator <Object> iter = lista.iterator();
		
		if(iter.hasNext()) {
			long resultado =  (long) lista.get(0);
			numAlumnos = BigInteger.valueOf(resultado);
		}
		return numAlumnos;
	}
	
	//ACTUALIZAR DEPARTAMENTO Apartado b)
	public static BigInteger getNumAsignaturas(Session s, BigInteger codDep) {
		BigInteger numero = null;
		String hql = "SELECT COUNT(a) \n"
				+ "FROM Departamentos d \n"
				+ "JOIN d.asignaturases a \n"
				+ "WHERE d.codDepar = " + codDep;
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator <Object> iter = lista.iterator();
		
		if(iter.hasNext()) {
			long resultado =  (long) lista.get(0);
			numero = BigInteger.valueOf(resultado);
		}
		return numero;
	}
	public static void actualizarDep(Session s, BigInteger cod, BigInteger numAsig) {
		String hql = "Update Departamentos d SET d.numAsig = :numAsig WHERE d.codDepar = :codDep";
		int filasModif = s.createMutationQuery(hql)
						  .setParameter("numAsig", numAsig)
						  .setParameter("codDep", cod)
						  .executeUpdate();
		if(filasModif > 0) {
			System.out.println("Cod Dep: " + cod + ", Nº asignaturas: " + numAsig);
		}else {
			System.out.println("Se ha producido un error al modificar registro " + cod);
		}
		
	}
	
	//ACTUALIZAR ALUMNO Apartado c)
	public static void actualizarNota(Session s, BigInteger numAlumno, BigDecimal notaMedia) {
		String hql = "UPDATE Alumnos a set a.notaMedia = :nota where a.numAlumno = :num";
		int filasModif = s.createMutationQuery(hql)
						  .setParameter("nota", notaMedia)
						  .setParameter("num", numAlumno)
						  .executeUpdate();
		
		if(filasModif>0) {
			System.out.printf("\nNum Alumno: %d, Nota media: %.2f", numAlumno, notaMedia);
		}
	}
	public static double calcularNota(Session s, BigInteger numAlumno) {
		String hql = "SELECT AVG(e.nota)\n"
				+ "FROM Alumnos a \n"
				+ "JOIN a.evaluacioneses e \n"
				+ "WHERE a.numAlumno = " + numAlumno +"\n"
				+ "GROUP BY a.numAlumno";
		
		Query<Object> q = s.createQuery(hql, Object.class);
		List<Object> lista = q.list();
		Iterator <Object> iter = lista.iterator();
		if(iter.hasNext()) {
			double nota = (double) (lista.get(0));
			return (double) (lista.get(0));
		} else {
			return 0;
		}
	}
}
