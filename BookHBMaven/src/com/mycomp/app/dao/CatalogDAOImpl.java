package com.mycomp.app.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;
import com.mycomp.app.util.HibernateUtil;

public class CatalogDAOImpl implements IDAO<Catalog> {

	@Override
	public Catalog create(Catalog t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public Catalog find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catalog update(Catalog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Catalog t) {
		// TODO Auto-generated method stub
		
	}
	
	public Catalog getById(int id) {
	        Session session = null;
	        Catalog catalog = null;
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            catalog =  (Catalog) session.get(Catalog.class, id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return catalog;
	}
	
}
