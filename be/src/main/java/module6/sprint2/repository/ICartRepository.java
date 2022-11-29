package module6.sprint2.repository;

import module6.sprint2.entity.account.Account;
import module6.sprint2.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart, Long> {
//    @Query(value = "SELECT `cart`.*, `cart_book`.book_id FROM `cart` " +
//            "join cart_book on `cart` .cart_id = `cart_book`.cart_id " +
//            "having `cart_book`.book_id > 0 and `cart`.cart_status = 0 and `cart`.cart_account_id = ?1" +
//            ";", nativeQuery = true)
    @Query(value="SELECT cart_book.*, cart.cart_status, cart.cart_account_id FROM cart_bookjoin cart on cart .cart_id = cart_book.cart_id having cart_book.book_id > 0 and cart.cart_status = 0 and cart.cart_account_id = ?1;", nativeQuery = true)
    List<Cart> findAllCart(Long id);

//    @Modifying
//    @Query(value = "UPDATE `cart` SET `cart`.cart_quantity = ?1, `cart`.cart_total_money = ?2 WHERE (`cart`.cart_id = ?3)", nativeQuery = true)
//    void updateQuantityCart(Integer cartQuantity, Double cartTotalMoney, Long cartId);



    @Query(value = "INSERT INTO `cart` (`cart_purchase_date`, `cart_quantity`, `cart_total_money`, `cart_account_id`)" +
            "VALUES (?1, ?2, ?3, ?4, ?5);", nativeQuery = true)
    @Transactional
    @Modifying
    void insertBookIntoCart( LocalDate cartPurchaseDate, Integer cartQuantity, Double cartTotalMoney, Long cartAccountId );

//    @Query(value = "select * from cart where cart_account_id= :accountId AND cart_account_id= :accountId AND cart_flag = 0", nativeQuery = true)
//    Optional<Cart> existsByCartId(@Param("accountId") Long accountId, @Param("bookName") String bookName);

    @Modifying
    @Query(value = "UPDATE cart SET cart_quantity = :quantity, cart_total_money= :totalMoney  WHERE cart_account_id = :accountId AND cart_id= :cartId", nativeQuery = true)
    void updateQuantityCart(@Param("quantity") Integer quantity,@Param("totalMoney") Double totalMoney,@Param("accountId") Long accountId, @Param("cartId") Long cartId);

    @Query(value = "select * from cart where cart_id= :cartId  AND cart_status =0", nativeQuery = true)
    Optional<Cart> findCartByCartId(@Param("cartId") Long cartId);

    @Modifying
    @Query(value = "UPDATE cart SET cart_quantity= :quantity, cart_total_money= :totalMoney  WHERE cart_id= :cartId AND cart_status =0", nativeQuery = true)
    void updateCart(@Param("quantity") Integer quantity,@Param("totalMoney") Double totalMoney, @Param("cartId") Long cartId );

}


