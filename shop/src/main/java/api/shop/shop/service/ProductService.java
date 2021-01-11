package api.shop.shop.service;

import api.shop.shop.model.Product;

import java.util.List;

public interface ProductService extends BasicService<Product, Long> {
    List<Product> getAllProducts(int current, int size);

    List<Product> getDirectoryProducts(String directory, int current, int size);

    List<Product> getSubdirectoryProducts(String subdirectory, int current, int size);
}
