package com.mycomp.app.dao;

import org.hibernate.Session;
import com.mycomp.app.model.Book;
import com.mycomp.app.util.HibernateUtil;

public class BookDAOImpl implements IDAO<Book> {

	@Override
	public Book create(Book t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public Book find(Object id) {
		return null;
	}

	@Override
	public Book update(Book t) {
		return null;
	}

	@Override
	public void delete(Book t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
	}
}
