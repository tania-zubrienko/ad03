package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	private static SessionFactory sf;
	
	public static void main(String[] args) {
		sf = Conexion.getSession();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Ejercicio1.getAlumnos(session);
		

	}

}
