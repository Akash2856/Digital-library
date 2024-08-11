package org.elibrary.application.mapper;

import lombok.experimental.UtilityClass;
import org.elibrary.application.dto.AddBookRequest;
import org.elibrary.application.model.Book;

@UtilityClass
public class BookMApper{
    public Book maptoBook(AddBookRequest addBookRequest){
        return Book.builder()
                .bookNo(addBookRequest.getBookNo())
                .bookTitle(addBookRequest.getBookTitle())
                .bookType(addBookRequest.getBookType())
                .securityAmount(addBookRequest.getSecurityAmount())
                .build();
    }
}
