package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Student;

public interface StudentDAO extends Remote {
	public boolean add(Student student)throws RemoteException;
	public List<Student> findAll() throws RemoteException;
	public List<Student> findStudentsEnrolledIn(int year)throws RemoteException;
	public List<Student> findStudentsEnrolledInCourse(String title)throws RemoteException;
}
