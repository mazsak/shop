package api.shop.shop.service;

import api.shop.shop.model.ShopOrder;
import api.shop.shop.repo.ShopOrderRepo;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderServiceImpl extends BasicServiceImpl<ShopOrder, ShopOrderRepo, Long> implements ShopOrderService {
    public ShopOrderServiceImpl(ShopOrderRepo shopOrderRepo) {
        super(shopOrderRepo);
    }
}
