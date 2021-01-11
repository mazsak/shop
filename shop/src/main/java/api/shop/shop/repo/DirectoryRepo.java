package api.shop.shop.repo;

import api.shop.shop.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepo extends JpaRepository<Directory, Long> {
}
