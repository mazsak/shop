package api.shop.shop.repo;

import api.shop.shop.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopItemRepo extends JpaRepository<ShopItem, Long> {

    List<ShopItem> findByProductId(Long id);
}
