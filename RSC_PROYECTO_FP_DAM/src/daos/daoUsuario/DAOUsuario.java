package daos.daoUsuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.modelUsuario.Usuario;
import services.serviceGeneral.HibernateManager;


public class DAOUsuario {
	
//CREATE
	public void createUser(Usuario usuario) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer)session.save(usuario);
		tx.commit();
		session.close();
	}

//RETRIEVE
	public Usuario retrieveUser(int idBuscado) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Usuario usuario = (Usuario)session.get(Usuario.class, idBuscado);
		session.close();
		return usuario;
	}

//UPDATE
	public void updateUser(Usuario user) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

//DELETE
	public void deleteUser(int idToDelete) {
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Usuario user = retrieveUser(idToDelete);
		session.delete(user);
		tx.commit();
		session.close();
	}

//RETRIEVE ALL
	public ArrayList<Usuario> retrieveAllUsers() {
		
		String query = "FROM Usuario";
		
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Usuario> objs = null;
		try {
			objs = (List<Usuario>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>)objs;
		return listaUsuarios;
	}

//RETRIEVE ALL OR FILTERING
	public List<Usuario> list(String query) {
		if(query.equals("")) {
			query = "FROM Usuario";
		}
		Session session = HibernateManager.getSessionFactory().openSession();
		List<Usuario> objs = null;
		try {
			objs = (List<Usuario>)session.createQuery(query).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.close();
		return objs;
	}
}
