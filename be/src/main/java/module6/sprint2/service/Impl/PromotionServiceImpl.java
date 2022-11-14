package module6.sprint2.service.Impl;

import module6.sprint2.entity.book.Promotion;
import module6.sprint2.repository.IPromotionRepository;
import module6.sprint2.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements IPromotionService {
    @Autowired
    IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAllPromotion() {
        return promotionRepository.findAllPromotion();
    }
}
