package org.elibrary.application.mapper;

import lombok.experimental.UtilityClass;
import org.elibrary.application.dto.AddUserRequest;
import org.elibrary.application.enums.UserStatus;
import org.elibrary.application.model.User;

@UtilityClass
public class UserMapper {
public User mapToUser(AddUserRequest userRequest){

    return  User.builder()
            .phoneNo(userRequest.getPhoneNo())
            .name(userRequest.getUserName())
            .email(userRequest.getEmail())
            .address(userRequest.getAddress())
            .userStatus(UserStatus.ACTIVE).build();
}
}
