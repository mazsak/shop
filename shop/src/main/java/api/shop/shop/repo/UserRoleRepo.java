package api.shop.shop.repo;

import api.shop.shop.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    public UserRole getByName(String name);
}
