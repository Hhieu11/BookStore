package module6.sprint2.controller;

import module6.sprint2.entity.account.Account;
import module6.sprint2.entity.book.Book;
import module6.sprint2.entity.cart.Cart;
import module6.sprint2.entity.cart.CartBook;
import module6.sprint2.repository.ICartBookRepository;
import module6.sprint2.repository.ICartRepository;
import module6.sprint2.service.IAccountService;
import module6.sprint2.service.ICartBookService;
import module6.sprint2.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @Autowired
    ICartBookService cartBookService;

    @Autowired private IAccountService accountService;

    @Autowired private ICartRepository cartRepository;

    @Autowired private ICartBookRepository cartBookRepository;

    @GetMapping("list-cart-book/{accountId}")
    public ResponseEntity<List<CartBook>> findAllCartBook(@PathVariable("accountId") Long accountId) {
        List<CartBook> cartBookList = cartBookService.findAllCartBook(accountId);
        if (cartBookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cartBookList, HttpStatus.OK);
        }
    }
//
//    @PutMapping("/update-quantity")
//    public ResponseEntity<CartBook> updateQuantityCart(@RequestBody CartBook cartBook) {
//        Double totalMoney = cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
//                - cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
//                * (cartBook.getBookId().getBookPromotionId().getPromotionPercent() / 100);
//        cartBook.getCartId().setCartTotalMoney(totalMoney);
//        cartService.updateQuantityCart(cartBook.getCartId().getCartQuantity(), cartBook.getCartId().getCartTotalMoney(), cartBook.getCartId().getCartId());
//        return new ResponseEntity<>(cartBook, HttpStatus.CREATED);
//    }

//    @PutMapping("/add-book")
//    public ResponseEntity<CartBook> updateQuantityCart(@RequestBody Book book) {
//
//    }

    @PostMapping("/addBookIntoCart/{id}")
    public ResponseEntity<?> insertBookCart(@PathVariable("id") Long id ,@RequestBody Book book) {
        List<CartBook> cartBooksList = cartBookService.findAllCartBook(id);
        for (CartBook cartBook: cartBooksList) {
            Double total = cartBook.getBookId().getBookPrice()*cartBook.getBookId().getBookQuantity();
           if (cartBook.getBookId().getBookId() == book.getBookId()) {
                cartService.updateQuantityCart(cartBook.getCartId().getCartQuantity()+1,total,id,cartBook.getCartId().getCartId());
               return new ResponseEntity<>(HttpStatus.OK);
           }
        }

//        them sach
       long millis=System.currentTimeMillis();
        LocalDate date = LocalDate.now();
        Account account = accountService.findAccountById(id);
        Cart cart = new Cart(1,book.getBookPrice(),date,account);
        CartBook cartBook = new CartBook(cart,book);
        cartRepository.save(cart);
        cartBookRepository.save(cartBook);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
    @PostMapping("/changeQuantityCart")
    public ResponseEntity<?> changeQuantityCart(@RequestParam("quantity") Integer quantity,
                                                @RequestParam("money") Double money,
                                                @RequestParam("cartId") Long cartId) {
        Optional<Cart> existCartBydId = cartService.findCartByCartId(cartId);
        if (existCartBydId.isPresent()){
            cartService.updateCart(quantity,money,cartId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/cart-delete")
    public ResponseEntity<Cart> deleteCart(@RequestParam("cardId") Long cardId) {
        Optional<Cart> foundCart = cartService.findCartByCartId(cardId);
        if (!foundCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cartService.deleteCartById(cardId);
            return new ResponseEntity<>(foundCart.get(), HttpStatus.NO_CONTENT);
        }
    }


}
