package api.shop.shop.repo;

import api.shop.shop.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<ShopUser, Long> {
    public boolean existsByUsername(String username);

    public Optional<ShopUser> findByUsername(String username);

    @Query(value = "SELECT u FROM ShopUser u where u.username = ?1 and u.password = ?2 ")
    public Optional<ShopUser> login(String username, String password);
}
