package api.shop.shop.repo;

import api.shop.shop.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<ShopOrder, Long> {
}
