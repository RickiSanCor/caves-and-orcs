package daos.daoPJ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelPJ.Genero;
import models.modelPJ.Raza;
import services.serviceGeneral.HibernateManager;

public class DAORaza {

	
//CREATE
	public void createRaza(Raza raza) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(raza);
		tx.commit();
		session.close();
	}

//RETRIEVE
	public Raza retrieveRaza(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Raza raza = (Raza)session.get(Raza.class, idBuscado);
		session.close();
		return raza;
	}

//UPDATE
	public void updateRaza(Raza raza) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(raza);
		tx.commit();
		session.close();
	}

//DELETE
	public void deleteRaza(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Raza raza = retrieveRaza(idToDelete);
		session.delete(raza);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Raza> retrieveAllRazas() {
		
		String query = "FROM Raza";
		
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Raza> objs = null;
		try {
			objs = (List<Raza>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		ArrayList<Raza> listaRazas = (ArrayList<Raza>)objs;
		session.close();
		return listaRazas;
	}

//RETRIEVE ALL OR FILTERING
	public List<Raza> list(String query) {
		if(query.equals("")) {
			query = "FROM Raza";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Raza> objs = null;
		try {
			objs = (List<Raza>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}
	
//RETRIEVE ONE FILTERING
	public Raza retrieveRazaNoId(String query) {
		if(query.equals("")) {
			query = "FROM Raza";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		Raza obj = null;
		try {
			obj = (Raza)session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return obj;
	}
}
