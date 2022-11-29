package module6.sprint2.service.Impl;

import module6.sprint2.entity.book.Book;
import module6.sprint2.repository.IBookRepository;
import module6.sprint2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    IBookRepository bookRepository;


    @Override
    public List<Book> findAllBook1() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllBook(Pageable page) {
        return bookRepository.findAllBook(page);
    }

    @Override
    public Optional<Book> finBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> findBookSameCategory(Long categoryId) {
        return bookRepository.findBookSameCategory(categoryId);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    public Page<Book> findAllBookByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findAllBookByCategoryId(categoryId,pageable);
    }

    @Override
    public Page<Book> searchBookName(String name, Pageable page) {
        return bookRepository.searchBookName(name,page);
    }


}
