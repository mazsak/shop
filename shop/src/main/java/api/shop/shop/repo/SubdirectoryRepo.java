package api.shop.shop.repo;

import api.shop.shop.model.Subdirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdirectoryRepo extends JpaRepository<Subdirectory, Long> {
}
