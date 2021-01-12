package api.shop.shop.controller;

import api.shop.shop.model.User;
import api.shop.shop.service.DirectoryService;
import api.shop.shop.service.SubdirectoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final DirectoryService directoryService;
    private final SubdirectoryService subdirectoryService;

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        return directoryService.findAll();
    }
}
