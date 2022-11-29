package module6.sprint2.service.Impl;

import module6.sprint2.entity.cart.Cart;
import module6.sprint2.repository.ICartRepository;
import module6.sprint2.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;

    @Override
    public List<Cart> findAllCart(Long id) {
        return cartRepository.findAllCart(id);
    }

//    @Override
//    public void updateQuantityCart(Integer cartQuantity, Double cartTotalMoney, Long cartId) {
//        cartRepository.updateQuantityCart(cartQuantity,cartTotalMoney,cartId);
//
//    }

    @Override
    public void insertBookIntoCart(LocalDate dateCreate, Integer quantity, Double totalMoney, Long accountId) {
        cartRepository.insertBookIntoCart(dateCreate, quantity, totalMoney, accountId);
    }

    @Override
    public void updateQuantityCart(Integer quantity, Double totalMoney, Long accountId, Long cartId) {
        cartRepository.updateQuantityCart(quantity,totalMoney,accountId,cartId);
    }

    @Override
    public Optional<Cart> findCartByCartId(Long cartId) {
        return cartRepository.findCartByCartId(cartId);
    }

    @Override
    public void updateCart(Integer quantity, Double totalMoney, Long cartId) {
        cartRepository.updateCart(quantity,totalMoney,cartId);
    }

    @Override
    public void deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
    }

//    @Override
//    public void insertBookIntoCart(String cartCode, LocalDate cartPurchaseDate, Integer cartQuantity, Boolean cartStatus, Double cartTotalMoney, Long cartAccountId) {
//        cartRepository.insertBookIntoCart(cartCode,cartPurchaseDate,cartQuantity,cartStatus,cartTotalMoney,cartAccountId);
//    }
}
