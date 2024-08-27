package org.elibrary.application.Service;

import org.elibrary.application.dto.AddUserRequest;
import org.elibrary.application.enums.UserType;
import org.elibrary.application.exceptions.BookException;
import org.elibrary.application.mapper.UserMapper;
import org.elibrary.application.model.User;
import org.elibrary.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public User addStudent(AddUserRequest addUserRequest){
        String userEmail= addUserRequest.getEmail();
        User userCheck= userRepository.findUserByEmail(userEmail);
        if(userCheck!=null)
            throw new BookException("user already exist");
        User user= UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.STUDENT);
        user.setAuthorities("STUDENT");
        return userRepository.save(user);
    }
    public User fetchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findUserByEmail(username);
        if(user!=null)
            return user;
        throw new UsernameNotFoundException(username.concat(" doesn't exist"));
    }

    public User addAdmin(AddUserRequest addUserRequest) {
        User user = UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.ADMIN);
        user.setAuthorities("ADMIN");//if you want admin to have authorities of student then user.setAuthorities("ADMIN,STUDENT");
        return userRepository.save(user);
    }
}
