package org.elibrary.application.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elibrary.application.enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 10,unique = true)
    String transactionId;

    @ManyToOne//transaction u can get books
    Book book;

    int settlementAmount;

    @ManyToOne
    User user;

    @Enumerated(value = EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updtaedOn;
}
