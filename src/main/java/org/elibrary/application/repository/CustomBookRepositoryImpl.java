package org.elibrary.application.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.elibrary.application.enums.BookType;
import org.elibrary.application.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository{
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Book> findBookByFilters(String bookTitle, BookType bookType) {

        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery= criteriaBuilder.createQuery(Book.class);

        Root<Book> bookRoot=criteriaQuery.from(Book.class);//select * from book; bookRoot is resultset

        List<Predicate> predicates= new ArrayList<>();//this is for where clouse

        //1st where condition operand is taken from bookroot.get("bookTitle") and like %% is operator and bookTitle is value.
        if(bookTitle !=null && !bookTitle.isEmpty()){
            Predicate titlePredicate= criteriaBuilder.like(bookRoot.get("bookTitle"),"%"+bookTitle+"%");
            predicates.add(titlePredicate);
        }
        //2nd where condition operand is taken from bookRoot.get("bookType") and equal == is operator and bookType is value.
        if(bookType!=null){
            Predicate typePredicate=criteriaBuilder.equal(bookRoot.get("bookType"),bookType);
            predicates.add(typePredicate);
        }
        Predicate finalPredicate= criteriaBuilder.or(predicates.toArray(new Predicate[0]));//this is the operator between 1st or 2nd
        criteriaQuery.select(bookRoot).where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

















