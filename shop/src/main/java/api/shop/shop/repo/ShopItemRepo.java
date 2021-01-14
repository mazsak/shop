package api.shop.shop.repo;

import api.shop.shop.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopItemRepo extends JpaRepository<ShopItem, Long> {
}
