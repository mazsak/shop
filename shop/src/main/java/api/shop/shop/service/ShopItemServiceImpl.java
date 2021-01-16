package api.shop.shop.service;

import api.shop.shop.model.ShopItem;
import api.shop.shop.model.ShopOrder;
import api.shop.shop.repo.ShopItemRepo;
import api.shop.shop.repo.ShopOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopItemServiceImpl extends BasicServiceImpl<ShopItem, ShopItemRepo, Long> implements ShopItemService {
    public ShopItemServiceImpl(ShopItemRepo shopItemRepo) {
        super(shopItemRepo);
    }

    @Override
    public List<ShopItem> findByProductId(Long id) {
        return repo.findByProductId(id);
    }
}
