package org.javacream.publishing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

public class Util {

	public static final Address ADDRESS1 = new Address("München", "Marienplatz");
	public static final Address ADDRESS2 = new Address("Berlin",
			"Alexanderplatz");
	public static final Publisher PUBLISHER = new Publisher("Springer",
			ADDRESS2);
	public static final Book BOOK1;
	public static final Book BOOK2;
	public static final Set<Book> BOOKS;
	public static final Author AUTHOR1 = new Author("Sawitzki", "Rainer",
			"Ulrich");
	public static final Author AUTHOR2 = new Author("Meier", "Hans");
	public static final Author AUTHOR3 = new Author("Polt", "Heinz", "Rüdiger");
	public static final BookStatistics BOOKSTATISTIC1 = new BookStatistics(122,
			new Date());

	static {
		BOOK1 = new Book(new Isbn(1, 2, 3, 4), "Title1");
		BOOK1.setMedium(BookMedium.PRINT);
		BOOK2 = new Book(new Isbn(1, 2, 3, 5), "Title2");
		BOOK2.setMedium(BookMedium.EBOOK);
		BOOK1.setPrice(19.99);
		BOOK1.setPages(400);
		BOOK1.addRevision(new Revision("Javacream", new Date(), "1.0"));
		BOOK1.setMedium(BookMedium.PRINT);
		BOOK1.addTags("sports", "olympics", "doping");
		BOOK2.setPrice(9.99);
		BOOK2.setPages(100);
		BOOK2.setMedium(BookMedium.EBOOK);
		BOOK2.addRevision(new Revision("Sawitzki", new Date(), "1.0"));
		BOOK2.addRevision(new Revision("Javacream", new Date(), "2.0"));
		BOOK2.addTags("science", "physics");

		Set<Book> books = new HashSet<Book>();
		for (int i = 0; i < 10; i++) {
			Book book = new Book(new Isbn(i, 2, 3, 4), "Title-" + i);
			book.setMedium(BookMedium.PRINT);
			book.addRevision(new Revision("Sawitzki", new Date(), "1." + i));
			book.addRevision(new Revision("Sawitzki", new Date(), "1."
					+ (i + 1)));
			book.addTags("science", "politics");
			book.setPages(100 * i);
			book.setPrice(9.99 + i);
			book.setAvailable(i % 2 == 0);
			books.add(book);
		}
		BOOKS = Collections.unmodifiableSet(books);
	}

	public static void createPublishers(EntityManager entityManager) {
		int publisherSize = 4;
		int booksSize = 40;
		int authorsSize = 3;

		String[] tags1 = new String[] { "sports", "socker" };
		String[] tags2 = new String[] { "science fiction", "star trek",
				"enterprise" };

		Author[] authors = new Author[authorsSize];
		for (int i = 0; i < authorsSize; i++) {
			ArrayList<String> givenNames = new ArrayList<String>(3);
			for (int j = 0; j < 3; j++) {
				givenNames.add("GivenName" + i + "_" + j);
			}
			Author author = new Author("AuthorLastname" + (i + 1),
					givenNames.toArray(new String[givenNames.size()]));
			authors[i] = author;
			entityManager.persist(author);
		}

		Publisher[] publishers = new Publisher[publisherSize];
		for (int i = 0; i < publisherSize; i++) {
			Publisher publisher = new Publisher("Publisher" + (i + 1),
					new Address("Publisher-City" + i, "Publisher-Street" + i));
			entityManager.persist(publisher);
			publishers[i] = publisher;
			for (int j = 0; j < booksSize; j++) {
				Book book = new Book(new Isbn(i, j, 3, 4), "Title" + i + j);
				book.setPages(200 + j);
				book.setPrice(9.95 * j);
				BookStatistics bookStatistics = new BookStatistics(50 * j,
						new Date());
				bookStatistics.setBook(book);
				if (j % 2 == 0) {
					authors[0].addBook(book);
				}
				if (i % 2 == 0) {
					authors[1].addBook(book);
				}
				if ((i + j) % 2 == 0) {
					authors[2].addBook(book);
				}
				if ((i % 2) == 0) {
					book.addTags(tags1);
					book.addRevision(new Revision("Javacream", new Date(), "1.0"));
				} else {
					book.addTags(tags2);
					book.addRevision(new Revision("Sawitzki", new Date(), "1.0"));
					book.addRevision(new Revision("Mustermann", new Date(), "1.1"));
				}
				
				entityManager.persist(book);
				entityManager.persist(bookStatistics);
				entityManager.persist(book);
				publisher.addBooks(book);
			}

		}
	}

}
