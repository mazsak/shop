package api.shop.shop.service;

import api.shop.shop.model.Directory;
import api.shop.shop.model.UserRole;
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
class UserRoleServiceImplTest {

    @Autowired
    private UserRoleService service;
    private List<UserRole> models = Arrays.asList(UserRole.builder().name("1").build(), UserRole.builder().name("2").build());

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
    void getRoleByName() {
        service.saveAll(models);
        assert "1".equals(service.getRoleByName("1").getName());
    }
}