package com.mycomp.app.dao;

import org.hibernate.Session;

import com.mycomp.app.model.Author;
import com.mycomp.app.util.HibernateUtil;

public class AuthorDAOImpl implements IDAO<Author> {

	@Override
	public Author create(Author t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public Author find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author update(Author t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Author t) {
		// TODO Auto-generated method stub
		
	}

}
