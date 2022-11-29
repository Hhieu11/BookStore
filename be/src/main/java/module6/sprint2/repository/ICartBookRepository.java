package module6.sprint2.repository;

import module6.sprint2.entity.cart.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface ICartBookRepository extends JpaRepository<CartBook, Long> {
    @Query(value = "SELECT `cart_book`.*, `cart`.cart_status, `cart`.cart_account_id FROM `cart_book`\\n\" +\n" +
            "            \"join `cart` on `cart` .cart_id = `cart_book`.cart_id\\n\" +\n" +
            "            \"group by  `cart_book`.book_id having `cart_book`.book_id > 0 and `cart`.cart_status =0 and `cart`.cart_account_id = ?1\n" +
            ";", nativeQuery = true)
    List<CartBook> findAllCartBook(Long id);

    @Query(value = "INSERT INTO `cart` ( `cart_purchase_date`,`cart_quantity`, `cart_total_money`,`cart_account_id`)" +
            "VALUES (?1, ?2, ?3,?4);", nativeQuery = true)
    @Transactional
    @Modifying
    void insertBookIntoCart(LocalDate dateCreate, Integer quantity, Double totalMoney, Long accountId);
}
