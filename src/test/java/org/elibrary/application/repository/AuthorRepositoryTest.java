package org.elibrary.application.repository;

import org.elibrary.application.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    public void setup(){
        Author author=Author.builder().name("akash").email("akash@gmail.com").build();
        authorRepository.save(author);
    }

    @Test
    public void fetchAuthorByEmailByNativeQuery_ValidAuthor_ReturnData(){
        Author ReturnAuthor=authorRepository.getAuthorByEmail("akash@gmail.com");
        Assertions.assertEquals("akash",ReturnAuthor.getName());
    }

    @Test
    public void fetchAuthorByEmailByNativeQuery_InvalidAuthor_ReturnNull(){
        Assertions.assertNull(authorRepository.getAuthorByEmail("zxc@gmail.com"));
    }
}
