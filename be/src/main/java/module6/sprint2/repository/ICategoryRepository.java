package module6.sprint2.repository;

import module6.sprint2.entity.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from `category` ", nativeQuery = true)
    List<Category> findAllCategory();

}
