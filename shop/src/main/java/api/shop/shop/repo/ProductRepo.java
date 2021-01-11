package api.shop.shop.repo;

import api.shop.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Long> {
    public Page<Product> findAllBySubdirectoryName(String name, Pageable pageable);

    @Query("SELECT p FROM Product p, Directory d WHERE d.name = ?1 AND p.subdirectory.id IN " +
            "(SELECT subs.id FROM d.subdirectories subs)")
    public Page<Product> findAllByDirectoryName(String name, Pageable pageable);
}
