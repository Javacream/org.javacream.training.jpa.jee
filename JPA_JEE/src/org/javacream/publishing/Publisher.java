package org.javacream.publishing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publisher {
	
	@Id
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="publisher", fetch=FetchType.LAZY)//, cascade={CascadeType.ALL})
	private Set<Book> books;

	
	protected Publisher(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
		books = new HashSet<>();
	}

	
	public void addBooks(Book... books){
		this.books.addAll(Arrays.asList(books));
		for (Book book: books){
			book.setPublisher(this);
		}
	}
	public void removeBooks(Book... books){
		this.books.removeAll(Arrays.asList(books));
		for (Book book: books){
			book.setPublisher(null);
		}
		
	}
	
	public String getName() {
		return name;
	}


	public Address getAddress() {
		return address;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Publisher other = (Publisher) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Publisher [name=" + name + ", address=" + address + "]";
	}


	protected Publisher() {
		super();
	}


	public Set<Book> getBooks(){
		return Collections.unmodifiableSet(books);
	}
}
