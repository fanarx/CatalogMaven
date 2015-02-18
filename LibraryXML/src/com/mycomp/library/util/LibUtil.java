package com.mycomp.library.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.mycomp.library.model.Author;
import com.mycomp.library.model.Book;
import com.mycomp.library.model.BookCatalog;
import com.mycomp.library.prop.Prop;
import com.mycomp.library.validator.XmlValidator;

public class LibUtil {
	private static final Logger log = Logger.getLogger(LibUtil.class);
	private static List<Author> authors = new ArrayList<Author>();
	private static int k;

	public static Book addBooktoCatalog(BookCatalog catalog, Book book) {
		
		List<Book> books = catalog.getBooks();
		books.add(book);
		catalog.setBooks(books);

		return book;
	}

	public static BookCatalog getCatalogfromXML(File xmlFile) {
		BookCatalog bookCatalog = null;
		try {
			JAXBContext jcxt = JAXBContext.newInstance(BookCatalog.class);
			Unmarshaller unMarshaller = jcxt.createUnmarshaller();
			bookCatalog = (BookCatalog) unMarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookCatalog;
	}

	public static Book enterBooktoXML(File xmlFile, BookCatalog ctlg, Book book) {
		Book currentBook = null;
		try {
			currentBook = addBooktoCatalog(ctlg, book);
			JAXBContext jcxt = JAXBContext.newInstance(BookCatalog.class);
			Marshaller marshaller = jcxt.createMarshaller();

			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(ctlg, xmlFile);
			marshaller.marshal(ctlg, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return currentBook;
	}

	public static boolean validatingBook(Book book) {
		boolean flag = true;
		try {
			JAXBContext jcxt = JAXBContext.newInstance(Book.class);
			Marshaller marshaller = jcxt.createMarshaller();
			// pretty printing
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(book,
					new File(Prop.getPropByName("bookXMLPath")));
			marshaller.marshal(book, System.out);

			if (XmlValidator.validateXSD(
					new File(Prop.getPropByName("bookXMLPath")),
					new File(Prop.getPropByName("bookXSDPath")))) {
				log.debug("The book with title " + book.getName()
						+ " xml entry is valid.");
				flag = true;
			} else {
				log.debug("The xml entry for book with title " + book.getName()
						+ " is not valid");
				flag = false;
			}

		} catch (JAXBException e) {
			flag = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static void addAllBooksToCatalog() {
		System.out.println("Please, enter a book details");
		addBookToCatalog();
		System.out
				.println("Do you want to add another book ? \n 1. yes \n 0. no");
		Scanner in = new Scanner(System.in);
		k = Integer.parseInt(in.nextLine());
		while (k == 1) {
			addAllBooksToCatalog();
		}
	}

	private static void addBookToCatalog() {
		authors = new ArrayList<Author>();
		Book book = new Book();
		String s;
		Scanner in = new Scanner(System.in);

		System.out.println("Enter a book name: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book name can not be blank");
			return;
		} else {
			book.setName(s);
		}

		System.out.println("Enter an author's firstname: ");
		Author author = new Author();
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Author's first name can not be blank");
			return;
		} else {
			author.setFirstName(s);
		}
		System.out.println("Enter an author's lastname: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Author's first name can not be blank");
			return;
		} else {
			author.setLastName(s);
		}
		authors.add(author);
		enteringAnotherAuthor(book);
		book.setAuthors(authors);

		System.out.println("Enter a book count: ");
		s = in.nextLine();

		if (Integer.parseInt(s) > 0) {
			book.setCount(Integer.parseInt(s));
		} else {
			System.out
					.println("Book count can not be less than or equal to zero !");
			return;
		}

		System.out.println("Enter a book ISBN: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book ISBN can not be blank");
			return;
		} else {
			book.setISBN(s);
		}

		System.out.println("Enter a book edition year: ");
		s = in.nextLine();

		if (Integer.parseInt(s) > 0) {
			book.setEditionYear(Integer.parseInt(s));
		} else {
			System.out.println("Book count can not be less than zero !");
			return;
		}

		System.out.println("Enter a book publishing company: ");
		s = in.nextLine();
		if (s.trim().equals("") || s == null) {
			System.out.println("Book ISBN can not be blank");
			return;
		} else {
			book.setPubCompany(s);
		}

		System.out
				.println("Do you want to add a description to this book ? \n 1. yes \n 0. no");
		s = in.nextLine();
		int m = Integer.parseInt(s);
		if (m == 1) {
			System.out.println("Please, type your description");
			s = in.nextLine();
			book.setDescription(s);
		}

		enterBook(new File(Prop.getPropByName("catalogXMLPath")),
				new File(Prop.getPropByName("catalogXSDPath")), book);
		System.out.println("The Book has been added to XML !!! ");
	}

	private static void enteringAnotherAuthor(Book book) {
		System.out
				.println("Does book have another author ? \n 1. yes \n 0. no ");
		Scanner in = new Scanner(System.in);
		k = Integer.parseInt(in.nextLine());
		while (k == 1) {
			Scanner input = new Scanner(System.in);
			Author author = new Author();
			System.out.println("Enter an author firstname: ");
			input = new Scanner(System.in);
			author.setFirstName(input.nextLine());
			System.out.println("Enter an author lastname: ");
			input = new Scanner(System.in);
			author.setLastName(input.nextLine());
			authors.add(author);
			enteringAnotherAuthor(book);
		}
	}

	public static void enterBook(File xmlFile, File xsdFile, Book book) {

		BookCatalog bookCatalog = LibUtil.getCatalogfromXML(xmlFile);

		if (LibUtil.validatingBook(book)) {
			System.out.println("The book is valid !!!");
			LibUtil.enterBooktoXML(xmlFile, bookCatalog, book);
			if (XmlValidator.validateXSD(xmlFile, xsdFile)) {
				log.debug("The book with name " + book.getName()
						+ " is in catalog.");
			}
		} else {
			System.out.println("The book entry is not valid!");
			log.debug(xmlFile.getName() + " is not valid");
		}
	}
}
