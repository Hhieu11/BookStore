package module6.sprint2.service;

import module6.sprint2.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAllBook1();
    Page<Book>findAllBook(Pageable pageable);

    Optional<Book> finBookById(Long id);

    List<Book>findBookSameCategory(Long categoryId);
}
