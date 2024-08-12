package org.elibrary.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elibrary.application.enums.BookType;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 30)
    String bookTitle;
    @Column(length = 10, nullable = false,unique = true)
    String bookNo;
    @Column(name = "security_amount")
    int securityAmount;

    @Enumerated(value=EnumType.STRING)
    BookType bookType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Author author;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "book")
    List<Transaction>transactions;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updtaedOn;
}
