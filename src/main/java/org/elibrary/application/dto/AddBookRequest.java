package org.elibrary.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.elibrary.application.enums.BookType;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequest {
    @NotBlank(message = "book title should not be blank")
    String bookTitle;
    @NotBlank(message = "bookNo should not be blank")
    String bookNo;

    int securityAmount;
    @NotNull(message = "book type should not be null")
    BookType bookType;
    @NotBlank(message = "Author name should not be blank")
    String authorName;
    @NotBlank(message = "Author email should not be blank")
    String authorEmail;
}
