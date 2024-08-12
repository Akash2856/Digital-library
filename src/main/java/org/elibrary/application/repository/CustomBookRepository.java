package org.elibrary.application.repository;

import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Null;
import org.elibrary.application.enums.BookType;
import org.elibrary.application.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository {


    List<Book> findBookByFilters(String bookTitle, BookType bookType);
}
