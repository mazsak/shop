package api.shop.shop.service;

import api.shop.shop.model.ShopOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ShopOrderServiceImplTest {

    @Autowired
    private ShopOrderService service;
    private List<ShopOrder> models = Arrays.asList(ShopOrder.builder().totalPrice(12.50).build(), ShopOrder.builder().totalPrice(12345.23).build());

    @BeforeEach
    public void deleteBAll() {
        service.findAll().forEach(element -> service.deleteById(element.getId()));
    }

    @AfterEach
    public void deleteAll() {
        service.findAll().forEach(element -> service.deleteById(element.getId()));
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

}