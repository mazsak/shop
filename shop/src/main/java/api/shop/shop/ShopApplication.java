package api.shop.shop;

import api.shop.shop.model.Directory;
import api.shop.shop.model.Subdirectory;
import api.shop.shop.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class ShopApplication {

    private final DirectoryService directoryService;
    private final SubdirectoryService subdirectoryService;
    private final ProductService productService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ShopOrderService shopOrderService;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }


}

