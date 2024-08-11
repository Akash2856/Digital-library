package org.elibrary.application.Service;

import org.elibrary.application.dto.AddUserRequest;
import org.elibrary.application.enums.UserType;
import org.elibrary.application.mapper.UserMapper;
import org.elibrary.application.model.User;
import org.elibrary.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addStudent(AddUserRequest addUserRequest){
        User user= UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.STUDENT);
        return userRepository.save(user);
    }
    public User fetchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
