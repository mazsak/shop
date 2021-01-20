package api.shop.shop.utils;

import api.shop.shop.model.*;
import api.shop.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor

public class InitDatabase {

    private final Environment environment;
    private final DirectoryService directoryService;
    private final SubdirectoryService subdirectoryService;
    private final ProductService productService;
    private final UserRoleService userRoleService;
    private final UserService userService;

    @PostConstruct
    public void init() {
        if (environment.getActiveProfiles().length == 0) {
            if (directoryService.findAll().isEmpty() || subdirectoryService.findAll().isEmpty()) {
                List<String> directories = Arrays.asList("Electronics", "Fashion", "Garden", "Motorization", "Sport");
                List<List<String>> subdirectories = Arrays.asList(Arrays.asList("Phones", "Computers", "Household appliances"),
                        Arrays.asList("For him", "For her", "For children"),
                        Arrays.asList("Plants", "Gardening equipment", "Furniture"),
                        Arrays.asList("Cars", "Car parts", "Car accessories"),
                        Arrays.asList("Bicycles and accessories", "Gym and fitness", "Fishing"));
                for (int i = 0; i < directories.size(); i++) {
                    Directory directory = new Directory();
                    directory.setName(directories.get(i));
                    for (int j = 0; j < subdirectories.get(i).size(); j++) {
                        Subdirectory subdirectory = new Subdirectory();
                        subdirectory.setName(subdirectories.get(i).get(j));
                        subdirectoryService.save(subdirectory);
                        directory.addSubdirectory(subdirectory);
                    }
                    directoryService.save(directory);
                }
            }
            if (productService.findAll().isEmpty()) {
                Random random = new Random();
                List<Subdirectory> subdirectoriesFound = subdirectoryService.findAll();
                for (Subdirectory sub : subdirectoriesFound) {
                    Product product = new Product();
                    product.setName("a" + random.nextInt() + "qwertyuiopasdfghjklzxcvbnm".charAt(random.nextInt("qwertyuiopasdfghjklzxcvbnm".length())));
                    product.setDescription("AAAAAAAAAAAAAAAA");
                    product.setPrice(49.99);
                    product.setStockAmount(random.nextInt(100));
                    product.setSubdirectory(sub);
                    productService.save(product);
                }
                for (Subdirectory sub : subdirectoriesFound) {
                    Product product = new Product();
                    product.setName("b" + random.nextInt() + "qwertyuiopasdfghjklzxcvbnm".charAt(random.nextInt("qwertyuiopasdfghjklzxcvbnm".length())));
                    product.setDescription("BBBBBBB");
                    product.setPrice(29.99);
                    product.setStockAmount(random.nextInt(200));
                    product.setSubdirectory(sub);
                    productService.save(product);
                }
            }
            if (userRoleService.findAll().isEmpty() || userService.findAll().isEmpty()) {
                ShopUser shopUser = new ShopUser();
                shopUser.setUsername("admin");
                shopUser.setPassword("admin");
                UserRole role = new UserRole();
                role.setName("ROLE_USER");
                userRoleService.save(role);
                UserRole role1 = new UserRole();
                role1.setName("ROLE_ADMIN");
                userRoleService.save(role1);
                shopUser.setRole(role1);
                userService.save(shopUser);
            }
        }
    }
}