package api.shop.shop;

import api.shop.shop.model.UserRole;
import api.shop.shop.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

//    @Override
//    public void run(String... args) throws Exception {
//        List<String> directories = Arrays.asList("Electronics", "Fashion", "Garden", "Motorization", "Sport");
//        List<List<String>> subdirectories = Arrays.asList(Arrays.asList("Phones", "Computers", "Household appliances"),
//                Arrays.asList("For him","For her","For children"),
//                Arrays.asList("Plants","Gardening equipment","Furniture"),
//                Arrays.asList("Cars","Car parts","Car accessories"),
//                Arrays.asList("Bicycles and accessories","Gym and fitness","Fishing"));
//        for (int i = 0; i < directories.size(); i++){
//            Directory directory = new Directory();
//            directory.setName(directories.get(i));
//            for (int j =0; j < subdirectories.get(i).size(); j++){
//                Subdirectory subdirectory = new Subdirectory();
//                subdirectory.setName(subdirectories.get(i).get(j));
//                directory.addSubdirectory(subdirectory);
//            }
//            directoryService.save(directory);
//        }
//        Random random = new Random();
//        List<Subdirectory> subdirectories = subdirectoryService.findAll();
//        for (Subdirectory sub : subdirectories) {
//            Product product = new Product();
//            product.setName("a" + random.nextInt() + "qwertyuiopasdfghjklzxcvbnm".charAt(random.nextInt("qwertyuiopasdfghjklzxcvbnm".length())));
//            product.setDescription("AAAAAAAAAAAAAAAA");
//            product.setPrice(49.99);
//            product.setStockAmount(1);
//            product.setSubdirectory(sub);
//            productService.save(product);
//        }
//        for (Subdirectory sub : subdirectories) {
//            Product product = new Product();
//            product.setName("b" + random.nextInt() + "qwertyuiopasdfghjklzxcvbnm".charAt(random.nextInt("qwertyuiopasdfghjklzxcvbnm".length())));
//            product.setDescription("AAAAAAAAAAAAAAAA");
//            product.setPrice(49.99);
//            product.setStockAmount(2);
//            product.setSubdirectory(sub);
//            productService.save(product);
//        }
//        ShopUser shopUser = new ShopUser();
//        shopUser.setUsername("admin");
//        shopUser.setPassword("admin");
//        UserRole role = new UserRole();
//        role.setName("ROLE_USER");
//        userRoleService.save(role);
//        UserRole role1 = new UserRole();
//        role1.setName("ROLE_ADMIN");
//        userRoleService.save(role1);
        //        shopUser.setRole(role);
//        userService.save(shopUser);
//        Product p = productService.findById((long) 21);
//        ShopOrder o = new ShopOrder();
//        List<Product> productList = o.getItems() == null ? new ArrayList<>() : o.getItems();
//        productList.add(p);
//        o.setItems(productList);
//        o.setOrderNumber("ABC123");
//        o.setTotalPrice(33.33);
//        shopOrderService.save(o);
//
//        ShopUser u = userService.findById(55L);
//        List<ShopOrder> shopOrderList = new ArrayList<>();
//        shopOrderList.add(shopOrderService.findById(60L));
//        u.setOrderList(shopOrderList);
//        userService.update(55L, u);
//    }
}

