package org.elibrary.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elibrary.application.enums.UserStatus;
import org.elibrary.application.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable,UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 30)
    String name;
    @Column(unique = true,nullable = false,length = 50)
    String email;
    @Column(unique = true,length = 15)
    String  phoneNo;

    @OneToMany(mappedBy = "user")
    List<Book> books;

    @OneToMany(mappedBy = "user")
    List<Transaction>transactions;

    String address;
    @Enumerated(value = EnumType.STRING)
    UserType userType;
    @Enumerated
    UserStatus userStatus;//0,1,2

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updtaedOn;

    String password;
    String authorities;

    @Override
    public String getUsername() {
        return email;
    }
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.stream(authorities.split(","))
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}
