package org.elibrary.application.Service;

import org.elibrary.application.model.Author;
import org.elibrary.application.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorByEmail(String email){
            return authorRepository.getAuthorByEmail(email);
    }

public  Author addAuthor(Author author){
        return authorRepository.save(author);
}

}
