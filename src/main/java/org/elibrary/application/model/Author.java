package org.elibrary.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false,length = 50)
    String name;

    @Column(unique = true,nullable = false,length = 50)
    String email;

    @OneToMany(mappedBy = "author")//this name should be same as Model Book's Author author
            @JsonIgnoreProperties(value="author")
    List<Book> books;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updtaedOn;
}
