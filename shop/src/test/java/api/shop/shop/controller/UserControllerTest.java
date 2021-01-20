package api.shop.shop.controller;

import api.shop.shop.model.ShopUser;
import api.shop.shop.model.UserRole;
import api.shop.shop.service.DirectoryService;
import api.shop.shop.service.SubdirectoryService;
import api.shop.shop.service.UserRoleService;
import api.shop.shop.service.UserService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private DirectoryService directoryService;
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
        directoryService.findAll().forEach(item -> directoryService.deleteById(item.getId()));
        subdirectoryService.findAll().forEach(item -> subdirectoryService.deleteById(item.getId()));
        userRoleService.findAll().forEach(item -> userRoleService.deleteById(item.getId()));
    }

    @Test
    void getRole() throws Exception {
        mockMvc.perform(get("/user/role")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/user/login")
                .content("{\"username\": \"admintest\", \"password\": \"admintest\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void register() throws Exception {
        userRoleService.save(UserRole.builder().name("ROLE_USER").build());
        mockMvc.perform(post("/user/register")
                .content("{\"username\": \"admin6\", \"password\": \"admin\", \"mail\": \"admin@gmail.com\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void getUserOrders() throws Exception {
        mockMvc.perform(get("/user/orders")
                .content("[]")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void addNewOrder() throws Exception {
        mockMvc.perform(post("/user/orders")
                .content("[]")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }
}