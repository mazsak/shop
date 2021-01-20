package api.shop.shop.service;

import api.shop.shop.model.Directory;
import api.shop.shop.model.Product;
import api.shop.shop.model.Subdirectory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private ProductService service;

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private SubdirectoryService subdirectoryService;

    private List<Product> models = Arrays.asList(Product.builder().name("1").description("234").stockAmount(123).price(12.3).build(), Product.builder().name("2").description("sds4").stockAmount(323).price(32.3).build());

    @AfterEach
    public void deleteAll() {
        service.findAll().forEach(element -> service.deleteById(element.getId()));
        directoryService.findAll().forEach(element -> directoryService.deleteById(element.getId()));
        subdirectoryService.findAll().forEach(element -> subdirectoryService.deleteById(element.getId()));
    }

    @Test
    public void save() {
        assert service.save(models.get(0));
    }

    @Test
    public void saveAll() {
        assert service.saveAll(models);
    }

    @Test
    public void deleteById() {
        service.save(models.get(0));
        assert service.deleteById(models.get(0).getId());
    }

    @Test
    public void findById() {
        service.save(models.get(0));
        assert models.get(0).getId().equals(service.findById(models.get(0).getId()).getId());
    }

    @Test
    public void findAll() {
        service.saveAll(models);
        assert service.findAll().size() == 2;
    }

    @Test
    void getAllProducts() {
        service.saveAll(models);
        Page<Product> products = service.getAllProducts(0, 1);
        assert products.getTotalElements() == 2 && products.getTotalPages() == 2 && products.getNumberOfElements() == 1;
    }

    @Test
    void getDirectoryProducts() {
        Subdirectory subdirectory = Subdirectory.builder().name("1").build();
        subdirectoryService.save(subdirectory);
        Directory directory = Directory.builder().name("2").subdirectories(Collections.singletonList(subdirectory)).build();
        directoryService.save(directory);
        models.get(0).setSubdirectory(subdirectory);
        service.saveAll(models);
        Page<Product> products = service.getDirectoryProducts("2", 0, 1);

        assert products.getTotalElements() == 1 && products.getTotalPages() == 1 && products.getNumberOfElements() == 1;
    }

    @Test
    void getSubdirectoryProducts() {
        Subdirectory subdirectory = Subdirectory.builder().name("1").build();
        subdirectoryService.save(subdirectory);

        models.get(0).setSubdirectory(subdirectory);
        service.saveAll(models);
        Page<Product> products = service.getSubdirectoryProducts("1", 0, 1);

        assert products.getTotalElements() == 1 && products.getTotalPages() == 1 && products.getNumberOfElements() == 1;
    }
}