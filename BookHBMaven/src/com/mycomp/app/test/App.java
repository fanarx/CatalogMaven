package com.mycomp.app.test;

import java.util.HashSet;
import java.util.Set;

import com.mycomp.app.dao.BookDAOImpl;
import com.mycomp.app.model.Author;
import com.mycomp.app.model.Book;
import com.mycomp.app.model.Catalog;

public class App {

	public static void main(String[] args) {

		Catalog catalog = new Catalog();
		catalog.setName("MyFirstCatalog");
		//Adding Catalog
		
		//ctlgDI.create(catalog);
		
		
		//Catalog catalog2 = ctlgDI.getById(1);
		
		//System.out.println("Catalog name: " + catalog2.getName());
		
		
		Author author1 = new Author();
		author1.setFirstname("Oscar");
		author1.setLastname("Wilde");
		
		Author author2 = new Author();
		author2.setFirstname("Fyodor");
		author2.setLastname("Dostoevskiy");
		
		Author author3 = new Author();
		author3.setFirstname("Eghishe");
		author3.setLastname("Charents");
		
		
		// End of Adding Catalog
		BookDAOImpl bookDI = new BookDAOImpl();
		Book book = new Book();
		Set<Author> authors = new HashSet<Author>();
		
		authors.add(author1);
		authors.add(author2);
		authors.add(author3);
		
		book.setAuthors(authors);
		book.setCatalog(catalog);
		book.setTitle("Universal Book");
//		CatalogDAOImpl ctlgDI = new CatalogDAOImpl();
//		Catalog catalog2 = ctlgDI.getById(1);

			bookDI.create(book);
			System.out.println("The Book is Added");


	}

}
