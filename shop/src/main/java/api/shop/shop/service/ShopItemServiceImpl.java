package api.shop.shop.service;

import api.shop.shop.model.ShopItem;
import api.shop.shop.model.ShopOrder;
import api.shop.shop.repo.ShopItemRepo;
import api.shop.shop.repo.ShopOrderRepo;
import org.springframework.stereotype.Service;

@Service
public class ShopItemServiceImpl extends BasicServiceImpl<ShopItem, ShopItemRepo, Long> implements ShopItemService {
    public ShopItemServiceImpl(ShopItemRepo shopItemRepo) {
        super(shopItemRepo);
    }
}
