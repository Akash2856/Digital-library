package org.elibrary.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elibrary.application.enums.UserStatus;
import org.elibrary.application.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
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

}
