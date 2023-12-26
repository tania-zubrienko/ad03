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
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//EJERCICIO 1
		SessionFactory sesion = Conexion.getSessionFactory();		
		Session session = sesion.openSession();	
		Transaction t = session.beginTransaction();
		
		//Actualizamos Centros
		System.out.println("\n------------------------EJERCICIO 1\n");
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
		
		
		
		//EJERCICIO 2
		System.out.println("\n------------------------EJERCICIO 2");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int evaluacion;
			int numAlumno;
			System.out.print("\nCod Evaluación (1 y 3, 0 para terminar):  ");
			evaluacion = Integer.parseInt(br.readLine());
			
			while(evaluacion!=0) {	
				
				if(evaluacion!=1 && evaluacion!=2 && evaluacion!=3 && evaluacion!=0) {					
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
				
				System.out.print("\nCod Evaluación (DOWN) (1 y 3, 0 para terminar):  ");
				evaluacion = Integer.parseInt(br.readLine());
			}
		
			
		//EJERCICIO 3
		System.out.println("\n------------------------EJERCICIO 3");
		Query<Cursos> queryCursos = session.createQuery("from Cursos", Cursos.class);
		List<Cursos> listaCursos2 = queryCursos.list();
		Iterator<Cursos> iterCursos2 = listaCursos2.iterator();
		
		while(iterCursos2.hasNext()) {

			Cursos c = iterCursos2.next();
			
			BigInteger codCurso = c.getCodCurso();
			String nombre = c.getDenominacion();
			System.out.printf("\n \nCOD-CURSO: %s NOMBRE CURSO: %s\n", codCurso, nombre);
			
			Object[] centro = Ejercicio3.getInfoCentro(session, codCurso);
			System.out.printf("NOMBRE CENTRO: %s, LOCALIDAD: %s \n", centro[0], centro[1]);
			
			System.out.println("========================================================================");
			System.out.print("NUM_ALUMNO   NOMBRE                      EV(1) EV(2) EV(3) NOTA_MEDIA");
			
			Set<Alumnos> alumnos = c.getAlumnoses();
			for(Alumnos a : alumnos) {
				
				System.out.print("\n     " + a.getNumAlumno() + "    " + Main.llenarEspacios(a.getNombre(), 30));
				List<Object[]> notas = Ejercicio3.getEvalulacionesAlumno(session, a.getNumAlumno());
				
				for(Object [] nota : notas) {
					for(int i=0; i<nota.length; i++) {
						
						if(nota[i]!="0") {
							System.out.print(Main.llenarEspacios(String.valueOf(nota[i]).substring(0, 3), 6));
						} else {
							System.out.print(Main.llenarEspacios("0", 6));
						}
					}
				}
				
				System.out.print(a.getNotaMedia());
			}
			System.out.print("\n Alumno con más nota media: " + Ejercicio3.alumnoMaxNota(session, codCurso));
		}
		
		
		//EJERCICIO 4
		System.out.println("\n------------------------EJERCICIO 4\n");
		
		List<Centros> lCentros= session.createQuery("from Centros", Centros.class).list();
		lCentros.forEach(centro ->{
			
			System.out.printf("\nCOD-CENTRO: %s NOMBRE CENTRO: %s LOCALIDAD: %s \n", centro.getCodCentro(), centro.getNombre(), centro.getLocalidad());
			
			Set<Cursos> cursos = centro.getCursoses();
			if(cursos.size()<=0) {
				System.out.println("<EL CENTRO NO TIENE CURSOS>");
			}
			cursos.forEach(curso ->{
				System.out.printf("COD-CURSO: %s DENOMINACIÓN: %s \n",curso.getCodCurso(), curso.getDenominacion());			
				System.out.print("COD-ASIG   NOMBRE               NOMBRE DEPART        NUM-ALUMNOS MED-EVA1 MED-EVA2 MED-EVA3 MEDIAASIG\r\n"
						         + "--------   -------------------- -------------------- ----------- -------- -------- -------- ---------");
				
				Set<Asignaturas> asignaturas = curso.getAsignaturases();
				asignaturas.forEach(asig -> {
					System.out.print("\n       " + asig.getCodAsig() + "   " + Main.llenarEspacios(asig.getNombre(), 20) 
									+ "  " + Main.llenarEspacios(asig.getDepartamentos().getNombreDepar(), 20) + "     "
									+ Ejercicio4.getNumAlumnos(session, asig.getCodAsig()) + "        ");
					
					for(int i=1; i<=3; i++) {
						String nota = String.valueOf(Ejercicio4.getMediaEvaluacion(session, asig.getCodAsig(), i));
						if(nota=="null") {
							System.out.print(Main.llenarEspacios("0", 6));
						}else {
							System.out.print(Main.llenarEspacios(nota.substring(0,3), 10));
						}
					}
					System.out.print(String.valueOf(Ejercicio4.getMediaAsignatura(session, asig.getCodAsig())).substring(0,3));
				});
				
				
				
				/* 
				BigInteger codigoCurso = curso.getCodCurso();
				List<Cursos> lista = Ejercicio4.getCursosCentro(session, codigoCurso);
				for(Cursos c: lista) {
					System.out.print(c.getDenominacion() + "    ");
				}*/
				System.out.println();
				System.out.println();
			});
		});
		
		
		
		
	}
	
	public static String llenarEspacios(String str, int longitud) {
		StringBuilder resultado = new StringBuilder();
		resultado.append(str);
		for(int i=0; i<longitud-str.length(); i++) {
			resultado.append(" ");
		}
		
		return resultado.toString();
		
	}
}
