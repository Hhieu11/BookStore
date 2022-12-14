package module6.sprint2.controller;

import module6.sprint2.entity.book.Book;
import module6.sprint2.entity.book.Category;
import module6.sprint2.service.IAuthorService;
import module6.sprint2.service.IBookService;
import module6.sprint2.service.ICategoryService;
import module6.sprint2.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @Autowired
    IAuthorService authorService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IPromotionService promotionService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("book-list")
    public ResponseEntity<Page<Book>> getAllBook(@PageableDefault(value = 8) Pageable pageable) {
        Page<Book> books = bookService.findAllBook(pageable);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.finBookById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookSameCate(@PathVariable Long categoryId) {

        return bookService.findBookSameCategory(categoryId);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategory() {

        return categoryService.findAllCategory();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBookId(@PathVariable Long id) {
        Optional<Book>book = bookService.finBookById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<Book>> getAllBookByCategoryId(@PathVariable("id") Long categoryId, @PageableDefault(value = 8) Pageable pageable) {
        Page<Book> books = bookService.findAllBookByCategoryId(categoryId, pageable);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<Book>> searchBook(@PathVariable("name") String name,@PageableDefault(value = 4) Pageable pageable) {
        Page<Book> books = bookService.searchBookName(name,pageable);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
