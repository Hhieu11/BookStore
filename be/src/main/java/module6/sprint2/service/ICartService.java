package module6.sprint2.service;

import module6.sprint2.entity.cart.Cart;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICartService {
    List<Cart> findAllCart(Long id);

//    void updateQuantityCart(Integer cartQuantity, Double cartTotalMoney, Long cartId);

//    void insertBookIntoCart(String cartCode, LocalDate cartPurchaseDate, Integer cartQuantity, Boolean cartStatus, Double cartTotalMoney, Long cartAccountId);

    void insertBookIntoCart(LocalDate dateCreate, Integer quantity, Double totalMoney, Long accountId);

    void updateQuantityCart( Integer quantity,  Double totalMoney,  Long accountId, Long cartId);

    Optional<Cart> findCartByCartId(Long cartId);

    void updateCart(Integer quantity, Double totalMoney, Long cartId);

    void deleteCartById(Long cartId);

}
