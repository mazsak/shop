package api.shop.shop.controller;

import api.shop.shop.model.Directory;
import api.shop.shop.model.ShopUser;
import api.shop.shop.model.Subdirectory;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DirectoryControllerTest {

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
    void findAll() throws Exception {
        mockMvc.perform(get("/directory/")).andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void createDirectory() throws Exception {
        mockMvc.perform(post("/directory/")
                .content("{\"name\": \"1\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    void getDirectory() throws Exception {
        Directory directory = Directory.builder().name("1").build();
        directoryService.save(directory);
        mockMvc.perform(get("/directory/" + directory.getId())
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void updateDirectory() throws Exception {
        Directory directory = Directory.builder().name("1").build();
        directoryService.save(directory);
        mockMvc.perform(put("/directory/" + directory.getId())
                .content("{\"id\": " + directory.getId() + ", \"name\": \"2\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void removeDirectory() throws Exception {
        Directory directory = Directory.builder().name("1").build();
        directoryService.save(directory);
        mockMvc.perform(delete("/directory/" + directory.getId() )
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void findSubdirectoryAll() throws Exception {
        mockMvc.perform(get("/directory/subdirectory")
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void createSubdirectory() throws Exception {
        mockMvc.perform(post("/directory/subdirectory")
                .content("{\"name\": \"@\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    void getSubdirectory() throws Exception {
        Subdirectory subdirectory = Subdirectory.builder().name("@").build();
        subdirectoryService.save(subdirectory);
        mockMvc.perform(get("/directory/subdirectory/" + subdirectory.getId())
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void updateSubdirectory() throws Exception {
        Subdirectory subdirectory = Subdirectory.builder().name("@").build();
        subdirectoryService.save(subdirectory);
        mockMvc.perform(put("/directory/subdirectory/" + subdirectory.getId())
                .content("{\"name\": \"1\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    void removeSubdirectory() throws Exception {
        Subdirectory subdirectory = Subdirectory.builder().name("@").build();
        subdirectoryService.save(subdirectory);
        mockMvc.perform(delete("/directory/subdirectory/" + subdirectory.getId())
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().is(200));
    }
}