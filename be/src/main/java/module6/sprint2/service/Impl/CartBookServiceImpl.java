package module6.sprint2.service.Impl;

import module6.sprint2.entity.cart.CartBook;
import module6.sprint2.repository.ICartBookRepository;
import module6.sprint2.service.ICartBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartBookServiceImpl implements ICartBookService {
    @Autowired
    ICartBookRepository cartBookRepository;

    @Override
    public List<CartBook> findAllCartBook(Long id) {
        return cartBookRepository.findAllCartBook(id);
    }
}
