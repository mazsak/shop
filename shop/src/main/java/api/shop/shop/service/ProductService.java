package api.shop.shop.service;

import api.shop.shop.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService extends BasicService<Product, Long> {
    Page<Product> getAllProducts(int current, int size);

    Page<Product> getDirectoryProducts(String directory, int current, int size);

    Page<Product> getSubdirectoryProducts(String subdirectory, int current, int size);
}
