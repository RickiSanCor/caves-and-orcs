package daos.daoPJ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelPJ.Clase;
import services.serviceGeneral.HibernateManager;

public class DAOClase {
	

//CREATE
	public void createClase(Clase clase) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(clase);
		tx.commit();
		session.close();
	}

//RETRIEVE
	public Clase retrieveClase(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Clase clase = (Clase)session.get(Clase.class, idBuscado);
		session.close();
		return clase;
	}

//UPDATE
	public void updateClase(Clase clase) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(clase);
		tx.commit();
		session.close();
	}

//DELETE
	public void deleteClase(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Clase clase = retrieveClase(idToDelete);
		session.delete(clase);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Clase> retrieveAllClases() {
		
		String query = "FROM Clase";
		
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Clase> objs = null;
		try {
			objs = (List<Clase>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		ArrayList<Clase> listaClases = (ArrayList<Clase>)objs;
		return listaClases;
	}

//RETRIEVE ALL OR FILTERING
	public List<Clase> list(String query) {
		if(query.equals("")) {
			query = "FROM Clase";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Clase> objs = null;
		try {
			objs = (List<Clase>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}
	
//RETRIEVE ONE FILTERING
	public Clase retrieveClaseNoId(String query) {
		if(query.equals("")) {
			query = "FROM Clase";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		Clase obj = null;
		try {
			obj = (Clase)session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return obj;
	}

}
