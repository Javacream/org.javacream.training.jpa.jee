package org.javacream.publishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Book")
@Table(name = "BOOKS", indexes = {
		@Index(unique = false, columnList = "price"),
		@Index(unique = false, columnList = "title") })
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, updatable = false, nullable = false)
	@Embedded
	private Isbn isbn;
	@Column(unique = false, updatable = false, nullable = false, length = 20)
	private String title;

	private int pages;

	//@Column(precision = 2, scale = 2)
	private double price;

	@Transient
	private boolean available;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public Book(Isbn isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.tags = new ArrayList<String>();
		this.revisions = new ArrayList<>();
	}

	public void addTags(String... tags) {
		this.tags.addAll(Arrays.asList(tags));
	}

	public void addRevision(Revision revision) {
		this.revisions.add(revision);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Isbn getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "BOOKTAGS", joinColumns = @JoinColumn(name = "BOOKID"))
	private List<String> tags;
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "BOOKREVISIONS", joinColumns = @JoinColumn(name = "BOOK"))
	// @GenericGenerator(name="hilo-generator",strategy="hilo")
	// @CollectionId(columns={@Column(name="REVISION_ID")},generator="hilo-generator",type=@Type(type="java.lang.Long"))
	private List<Revision> revisions;

	@Enumerated(EnumType.STRING)
	private BookMedium medium = BookMedium.UNKNOWN;

	public BookMedium getMedium() {
		return medium;
	}

	public void setMedium(BookMedium medium) {
		if (medium != null) {
			this.medium = medium;
		}
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Publisher publisher;

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", pages=" + pages
				+ ", price=" + price + ", available=" + available + ", id="
				+ id + ", tags=" + tags + ", revisions=" + revisions
				+ ", medium=" + medium + "]";
	}

	protected Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	@ManyToMany
	@JoinTable(name = "BOOKS_AUTHORS", joinColumns = { @JoinColumn(name = "ISBN") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID") })
	private Set<Author> authors;

	Set<Author> getAuthorsSet() {
		if (authors == null) {
			authors = new HashSet<Author>();
		}
		return authors;
	}

	public Set<Author> getAuthors() {
		if (authors == null) {
			authors = new HashSet<Author>();
		}
		return Collections.unmodifiableSet(authors);
	}

}
