package org.elibrary.application.repository;

import org.elibrary.application.model.Author;
import org.elibrary.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where email=:email",nativeQuery = true)
    public User findUserByEmail(String email);
    User findByEmail(String email);
}
