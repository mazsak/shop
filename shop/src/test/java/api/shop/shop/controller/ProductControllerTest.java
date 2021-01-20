package api.shop.shop.controller;

import api.shop.shop.model.Product;
import api.shop.shop.model.ShopUser;
import api.shop.shop.model.Subdirectory;
import api.shop.shop.model.UserRole;
import api.shop.shop.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SubdirectoryService subdirectoryService;

    private String token;

    @BeforeEach
    void createUser() throws Exception {
        UserRole role = UserRole.builder().name("ROLE_ADMIN").build();
        userRoleService.save(role);
        ShopUser user = ShopUser.builder()
                .username("admintest")
                .password("admintest")
                .mail("admintest@gmail.com")
                .role(role)
                .build();
        userService.save(user);

        MvcResult login = mockMvc.perform(post("/user/login")
                .content("{\"username\": \"admintest\", \"password\": \"admintest\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        token = "Bearer " + login.getResponse().getContentAsString().split(":")[1]
                .replace("\"", "")
                .replace("}", "");
    }

    @AfterEach
    void deleteUser() {
        userService.findAll().forEach(user -> userService.deleteById(user.getId()));
        productService.findAll().forEach(item -> productService.deleteById(item.getId()));
        subdirectoryService.findAll().forEach(item -> subdirectoryService.deleteById(item.getId()));
    }

    @Test
    void findProducts() throws Exception {
        mockMvc.perform(get("/products/all")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void testFindProducts() throws Exception {
        mockMvc.perform(get("/products?subdirectory=&directory=&current=1&size=2")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void createProduct() throws Exception {
        mockMvc.perform(post("/products")
                .content("{\"name\": \"1\", \"description\": \"2\", " +
                        "\"price\": 3.00, \"stockAmount\": 4}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    void getProduct() throws Exception {
        Product product = Product.builder().name("qwe").price(2.12).stockAmount(32).description("rty").build();
        productService.save(product);
        mockMvc.perform(get("/products/product/"+ product.getId())
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void updateProduct() throws Exception {
        Product product = Product.builder().name("qwe").price(2.12).stockAmount(32).description("rty").build();
        productService.save(product);
        mockMvc.perform(put("/products/product/"+ product.getId())
                .content("{\"name\": \"qwe\", \"description\": \"2\", " +
                        "\"price\": 3.00, \"stockAmount\": 4}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void removeProduct() throws Exception {
        Product product = Product.builder().name("qwe").price(2.12).stockAmount(32).description("rty").build();
        productService.save(product);
        mockMvc.perform(delete("/products/product/"+ product.getId())
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }
}