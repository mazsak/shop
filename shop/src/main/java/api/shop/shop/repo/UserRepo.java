package api.shop.shop.repo;

import api.shop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public boolean existsByUsername(String username);

    public Optional<User> findByUsername(String username);
}
