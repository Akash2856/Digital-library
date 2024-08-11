package org.elibrary.application.mapper;

import lombok.experimental.UtilityClass;
import org.elibrary.application.dto.AddBookRequest;
import org.elibrary.application.model.Author;

@UtilityClass
public class AuthorMapper {
    public Author mapToAuthor(AddBookRequest addBookRequest){
        return Author.builder()
        .name(addBookRequest.getAuthorName())
        .email(addBookRequest.getAuthorEmail())
        .build();
    }
}
