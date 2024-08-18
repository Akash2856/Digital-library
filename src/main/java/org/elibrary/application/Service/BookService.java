package org.elibrary.application.Service;

import jakarta.validation.constraints.Null;
import org.elibrary.application.dto.AddBookRequest;
import org.elibrary.application.enums.BookType;
import org.elibrary.application.exceptions.BookException;
import org.elibrary.application.mapper.AuthorMapper;
import org.elibrary.application.mapper.BookMApper;
import org.elibrary.application.model.Author;
import org.elibrary.application.model.Book;
import org.elibrary.application.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;


    public Book addbook(AddBookRequest BookRequest){
    Author authorFromDb= authorService.getAuthorByEmail(BookRequest.getAuthorEmail());
    if(authorFromDb== null) {
        authorFromDb = AuthorMapper.mapToAuthor(BookRequest);
        authorFromDb = authorService.addAuthor(authorFromDb);
    }


        Book book= BookMApper.maptoBook(BookRequest);
        book.setAuthor(authorFromDb);
        return bookRepository.save(book);
    }

    public Book getBookByBookNo(String bookNo){
        return bookRepository.findBookByBookNo(bookNo);
    }

    public void updateBookMetaData(Book book){
        bookRepository.save(book);
    }
public  List<Book> getBooks(String bookTitle, BookType bookType){
        return bookRepository.findBookByFilters(bookTitle,bookType);
}
}
