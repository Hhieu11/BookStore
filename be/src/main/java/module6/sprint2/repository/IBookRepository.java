package module6.sprint2.repository;

import module6.sprint2.entity.book.Book;
import module6.sprint2.entity.book.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from `book` where book_flag=0", nativeQuery = true)
    Page<Book> findAllBook(Pageable pageable);
    @Query(value = "select * from book where book_id = ?1 ", nativeQuery = true)
    Optional<Book> findBookById(Long id);

    //lay book co cung category
    @Query(value = "SELECT * from book  left join category on category.category_id = book.book_category_id where book.book_flag = 0 and book.book_category_id=?1  order by book.book_category_id limit 0,4", nativeQuery = true)
    List<Book>findBookSameCategory(Long categoryId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE book set book_flag = true where book_id = :id", nativeQuery = true)
    void deleteBookById(Long id);

    @Query(value = "SELECT * FROM book where book_category_id=?1 order by book_publish_date desc", nativeQuery = true)
    Page<Book> findAllBookByCategoryId(Long book_category_id, Pageable pageable);

    @Query(value = "select * from book where book_name like %:name% and book_flag = 0", nativeQuery = true)
    Page<Book> searchBookName(@Param("name") String name, Pageable pageable);
}
