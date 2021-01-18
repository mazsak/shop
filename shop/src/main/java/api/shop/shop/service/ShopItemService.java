package api.shop.shop.service;

import api.shop.shop.model.ShopItem;

import java.util.List;

public interface ShopItemService extends BasicService<ShopItem, Long> {

    List<ShopItem> findByProductId(Long id);
}
