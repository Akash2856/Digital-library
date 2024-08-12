package org.elibrary.application.repository;

import org.elibrary.application.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer>,CustomBookRepository {
    Book findBookByBookNo(String bookNo);
}
