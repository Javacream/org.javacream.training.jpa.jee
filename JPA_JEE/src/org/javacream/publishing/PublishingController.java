package org.javacream.publishing;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
public class PublishingController {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void insertData() {
		Util.createPublishers(entityManager);
	}
	
	@Transactional
	public String findBookByTitle(String title) {
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book where book.title = :title", Book.class);
		query.setParameter("title", title);
		return query.getSingleResult().toString();
	}

	@Transactional(value=TxType.REQUIRED)
	public Book findBookByTitle2(String title) {
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book where book.title = :title", Book.class);
		query.setParameter("title", title);
		return query.getSingleResult();
	}

	
	@Transactional
	public String findBookByTitleWithCriteria(String title) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("title"), title));
		TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
		return query.getSingleResult().toString();
	}

	
	@Transactional
	public String findAllBooks() {
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book", Book.class);
		return query.getResultList().toString();
	}


	@Transactional
	public String findAllBooksWithCriteria() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		criteriaQuery.from(Book.class);
		TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList().toString();
	}
	@Transactional
	public String findBooksForAuthor(String authorName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		criteriaQuery.from(Book.class);
		TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList().toString();
	}

}
