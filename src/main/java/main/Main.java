package main;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tarea03.*;

public class Main {
	public static void main(String[] args) {
		
		//EJERCICIO 1
		SessionFactory sesion = Conexion.getSessionFactory();		
		Session session = sesion.openSession();	
		Transaction t = session.beginTransaction();
		/*
		//Actualizamos Centros
		System.out.println("a)\n");
		
		Query<Centros> qCentros = session.createQuery("from Centros", Centros.class);
		List<Centros> listaCentros = qCentros.list();
		Iterator<Centros> iterCentros = listaCentros.iterator();
		
		while(iterCentros.hasNext()) {
			Centros c = iterCentros.next();
			BigInteger codCen = c.getCodCentro();
			Ejercicio1.actualizarCentro(session, codCen);
		}
		//Actualizamos Cursos
		Query<Cursos> qCursos = session.createQuery("from Cursos", Cursos.class);
		List<Cursos> listaCursos = qCursos.list();
		Iterator<Cursos> iterCursos = listaCursos.iterator();
		
		while(iterCursos.hasNext()) {
			Cursos c = iterCursos.next();
			BigInteger codCurso = c.getCodCurso();
			Ejercicio1.actualizarCurso(session, codCurso);
		}
		
		
		//Actualizamos departamentos
		System.out.println("b)\n");
		
		Query<Departamentos> qDep = session.createQuery("from Departamentos", Departamentos.class);
		List<Departamentos> listaDep = qDep.list();
		Iterator<Departamentos> iterDep = listaDep.iterator();
		
		while(iterDep.hasNext()) {
			Departamentos d = iterDep.next();
			BigInteger codDep = d.getCodDepar();
			BigInteger numAsignaturas = Ejercicio1.getNumAsignaturas(session, codDep);
			Ejercicio1.actualizarDep(session, codDep, numAsignaturas);
			
		}
		
		
		//Actualizamos aluimnos
		System.out.println("c)\n");
		
		Query<Alumnos> q = session.createQuery("from Alumnos", Alumnos.class);
		List <Alumnos> lista = q.list();
		Iterator <Alumnos> iter = lista.iterator();
		
		while (iter.hasNext()){
			Alumnos  a = iter.next();
			BigInteger numAlumno = a.getNumAlumno();
			BigDecimal nm = new BigDecimal(Ejercicio1.calcularNota(session, numAlumno));
			Ejercicio1.actualizarNota(session, numAlumno, nm);
			
		}
		
		t.commit();
		
		
		//EJERCICIO 2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			int evaluacion;
			int numAlumno;
			do {
				System.out.print("\nCod Evaluación (1 y 3, 0 para terminar):  ");
				evaluacion = Integer.parseInt(br.readLine());
				
				if(evaluacion==0) {break;}
				if(evaluacion!=1 && evaluacion!=2 && evaluacion!=3) {
					
					System.out.println("Numero de evaluacion incorrecto");
					
				}else {
					
					System.out.println("\nNúmero de alumno (> 0)");
					numAlumno = Integer.parseInt(br.readLine());
					if(numAlumno == 0) {
						break;
					}
					
					if(Ejercicio2.existeAlumno(session, numAlumno)) {
						
						Set<BigInteger> asignaturas = Ejercicio2.getAsignaturas(session, numAlumno);
						
						System.out.print("Cod de Asig (> 0): (Asignaturas para el alumno: ");
						asignaturas.forEach(a-> System.out.print(a + " "));
						System.out.print(")");
						
						int asignatura = Integer.parseInt(br.readLine());
						
						if(Ejercicio2.comprobarAsignatura(session, asignaturas, asignatura, numAlumno, evaluacion)) {
							
							System.out.println("\nIntroduce la nota(entre 1 y 10): ");
							int nota = Integer.parseInt(br.readLine());
							Evaluaciones nueva = Ejercicio2.insertarEvaluacion(session, numAlumno, asignatura, evaluacion, nota );
							session.persist(nueva);
							t.commit();
						}
						
						
					} else {
						System.out.println("No existe alumno con codigo " + numAlumno);
						
					}//comprobacion codigo alumno
				}//comprobacion de evaluacion
				
			}while(evaluacion!=0);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	*/	
		
		
		//EJERCICIO 3
		
	}
	
	
	
}
