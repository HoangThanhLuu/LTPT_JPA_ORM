package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.CourseDAO;
import dao.StudentDAO;
import dao.impl.CourseImpl;
import dao.impl.StudentImpl;
import entity.Student;

public class Server1 {
	private static final String URL = "rmi://DESKTOP-Q14USF3:4567/";
	public static void main(String[] args) throws NamingException, RemoteException {
		Context context = new InitialContext();
		CourseDAO courseDAO = new CourseImpl();
		//StudentDAO studentDAO = new StudentImpl();
		
		LocateRegistry.createRegistry(4567);
		
//		context.bind(URL+"studentDAO", studentDAO);
		context.bind(URL+"courseDAO", courseDAO);
		System.out.println("Realy....");
		
	}
}
