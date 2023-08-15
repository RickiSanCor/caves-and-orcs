package daos.daoPJ;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelPJ.Atributos;
import services.serviceGeneral.HibernateManager;

public class DAOAtributos {
	
	
//CREATE
	public int createAtributos(Atributos atributos) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(atributos);
		tx.commit();
		session.close();
		
		return id;
	}

//RETRIEVE
	public Atributos retrieveAtributos(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Atributos atributos = (Atributos)session.get(Atributos.class, idBuscado);
		session.close();
		return atributos;
	}

//UPDATE
	public void updateAtributos(Atributos atributos) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(atributos);
		tx.commit();
		session.close();
	}

//DELETE
	public void deleteAtributos(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Atributos atributos = retrieveAtributos(idToDelete);
		session.delete(atributos);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Atributos> retrieveAllAtributos() {
		
		String query = "FROM Atributos";
		
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Atributos> objs = null;
		try {
			objs = (List<Atributos>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		ArrayList<Atributos> listaAtributoss = (ArrayList<Atributos>)objs;
		return listaAtributoss;
	}

//RETRIEVE ALL OR FILTERING
	public List<Atributos> list(String query) {
		if(query.equals("")) {
			query = "FROM Atributos";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Atributos> objs = null;
		try {
			objs = (List<Atributos>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}
	
//RETRIEVE ONE FILTERING
	public Atributos retrieveAtributosNoId(String query) {
		if(query.equals("")) {
			query = "FROM Atributos";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		Atributos obj = null;
		try {
			obj = (Atributos)session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return obj;
	}	
}
