package org.javacream.publishing.rs;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.publishing.Book;
import org.javacream.publishing.PublishingController;

@Path("/jpa")
public class PublishingRest {
	
	@Inject
	private PublishingController publishingController;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/insertData")
	public String insert(){
		publishingController.insertData();
		return "OK";
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/book/{title}")
	public String findByTitle(@PathParam("title")String title){
		return publishingController.findBookByTitleWithCriteria(title);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/book2/{title}")
	@Transactional(value=TxType.REQUIRES_NEW)
	public String findByTitle2(@PathParam("title")String title){
		Book book = publishingController.findBookByTitle2(title);
		return book.toString() + ", publisher is " + book.getPublisher();
	}

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/books")
	public String findAllBooks(){
		return publishingController.findAllBooksWithCriteria();
	}

}
