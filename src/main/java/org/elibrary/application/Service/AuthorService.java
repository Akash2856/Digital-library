package org.elibrary.application.Service;

import lombok.extern.slf4j.Slf4j;
import org.elibrary.application.model.Author;
import org.elibrary.application.repository.AuthorRepository;
import org.elibrary.application.repository.RedisDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    RedisDataRepository redisDataRepository;

    public Author getAuthorByEmail(String email){
        Author author=redisDataRepository.getAuthorByEmail(email);
        if(author!=null){
            log.info("autor found from radis");
            return author;
        }
        author=authorRepository.getAuthorByEmail(email);
        if(author!=null){
            log.info("author found from table");
            redisDataRepository.saveAuthorToRedis(author);
            log.info("author save in radis");
        }
        return author;
    }

public  Author addAuthor(Author author){

        Author saveAuthor= authorRepository.save(author);
        redisDataRepository.saveAuthorToRedis(saveAuthor);
        return saveAuthor;
}

}
