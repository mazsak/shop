package api.shop.shop.service;

import api.shop.shop.model.Product;
import api.shop.shop.repo.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BasicServiceImpl<Product, ProductRepo, Long> implements ProductService {
    public ProductServiceImpl(ProductRepo productRepo) {
        super(productRepo);
    }

    @Override
    public Page<Product> getAllProducts(int current, int size) {
        return repo.findAll(PageRequest.of(current, size, Sort.by("name")));
    }

    @Override
    public Page<Product> getDirectoryProducts(String directory, int current, int size) {
        return repo.findAllByDirectoryName(directory, PageRequest.of(current, size, Sort.by("name")));
    }

    @Override
    public Page<Product> getSubdirectoryProducts(String subdirectory, int current, int size) {
        return repo.findAllBySubdirectoryName(subdirectory, PageRequest.of(current, size, Sort.by("name")));
    }
}
