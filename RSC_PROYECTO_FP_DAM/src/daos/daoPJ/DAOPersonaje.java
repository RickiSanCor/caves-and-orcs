package daos.daoPJ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelPJ.Personaje;
import services.serviceGeneral.HibernateManager;

public class DAOPersonaje {


//CREATE
	public void createPersonaje(Personaje personaje) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(personaje);
		tx.commit();
		session.close();
	}

//RETRIEVE
	public Personaje retrievePersonaje(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Personaje personaje = (Personaje)session.get(Personaje.class, idBuscado);
		session.close();
		return personaje;
	}

//UPDATE
	public void updatePersonaje(Personaje personaje) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(personaje);
		tx.commit();
		session.close();
	}

//DELETE
	public void deletePersonaje(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Personaje personaje = retrievePersonaje(idToDelete);
		session.delete(personaje);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Personaje> retrieveAllPersonajes(String query) {
		if(query.equals("")) {
			query = "FROM Personaje";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Personaje> objs = null;
		try {
			objs = (List<Personaje>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		ArrayList<Personaje> listaPersonajes = (ArrayList<Personaje>)objs;
		return listaPersonajes;
	}

	
//RETRIEVE ALL OR FILTERING
	public List<Personaje> list(String query) {
		if(query.equals("")) {
			query = "FROM Personaje";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Personaje> objs = null;
		try {
			objs = (List<Personaje>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}

//RETRIEVE ONE FILTERING
	public Personaje retrievePersonajeNoId(String query) {
		if(query.equals("")) {
			query = "FROM Personaje";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		Personaje obj = null;
		try {
			obj = (Personaje)session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return obj;
	}
	
}
