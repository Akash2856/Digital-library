package org.elibrary.application.repository;

import org.elibrary.application.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDataRepository {
    @Autowired
    RedisTemplate redisTemplate;

    private String AUTHOR_KEY="author:";

    public void saveAuthorToRedis(Author author){
        redisTemplate.opsForValue().set(AUTHOR_KEY.concat(author.getEmail()),author);
    }
    public Author getAuthorByEmail(String email){
        return (Author)redisTemplate.opsForValue().get(AUTHOR_KEY.concat(email));
    }
}
