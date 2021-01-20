package api.shop.shop.service;

import api.shop.shop.model.Directory;
import api.shop.shop.model.ShopUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService service;
    private List<ShopUser> models = Arrays.asList(ShopUser.builder().username("1").mail("1@gmail.com").password("jj").build(), ShopUser.builder().username("2").mail("2@gmail.com").password("jj").build());

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

    @Test
    void update() {
        service.save(models.get(0));
        assert service.update(models.get(0).getId(), models.get(0));
    }

    @Test
    void loadUserByUsername() {
        service.save(models.get(0));
        assert  ((ShopUser)service.loadUserByUsername(models.get(0).getUsername())).getId().equals(models.get(0).getId());
    }
}