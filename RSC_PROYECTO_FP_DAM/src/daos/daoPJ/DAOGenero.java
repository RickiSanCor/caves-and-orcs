package daos.daoPJ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelPJ.Genero;
import services.serviceGeneral.HibernateManager;

public class DAOGenero {

	
//CREATE
	public void createGenero(Genero genero) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(genero);
		tx.commit();
		session.close();
	}

//RETRIEVE
	public Genero retrieveGenero(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Genero genero = (Genero)session.get(Genero.class, idBuscado);
		session.close();
		return genero;
	}

//UPDATE
	public void updateGenero(Genero genero) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(genero);
		tx.commit();
		session.close();
	}

//DELETE
	public void deleteGenero(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Genero genero = retrieveGenero(idToDelete);
		session.delete(genero);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Genero> retrieveAllGeneros() {
		
		String query = "FROM Genero";
		
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Genero> objs = null;
		try {
			objs = (List<Genero>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		ArrayList<Genero> listaGeneros = (ArrayList<Genero>)objs;
		return listaGeneros;
	}

//RETRIEVE ALL OR FILTERING
	public List<Genero> list(String query) {
		if(query.equals("")) {
			query = "FROM Genero";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Genero> objs = null;
		try {
			objs = (List<Genero>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}
	
//RETRIEVE ONE FILTERING
	public Genero retrieveGeneroNoId(String query) {
		if(query.equals("")) {
			query = "FROM Genero";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		Genero obj = null;
		try {
			obj = (Genero)session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return obj;
	}
}
