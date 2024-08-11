package org.elibrary.application.repository;

import org.elibrary.application.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select * from author where email=:email", nativeQuery = true)
    public Author getAuthorByEmail(String email);

    Author findByEmail(String email);
}
