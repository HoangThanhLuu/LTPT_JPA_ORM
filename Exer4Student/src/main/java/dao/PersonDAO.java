package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Person;



public interface PersonDAO extends Remote {
	
	public boolean add(Person person) throws RemoteException;
	public boolean update(Person person)throws RemoteException;
	public boolean delete(int id)throws RemoteException;
	public Person findByID(int id)throws RemoteException;
	public List<Person> findAll()throws RemoteException;
	public List<Person> findByTitle(String title)throws RemoteException; // tim tuong doi
	public Person findByTitle2(String title)throws RemoteException; // tim tuyet doi
	
}
