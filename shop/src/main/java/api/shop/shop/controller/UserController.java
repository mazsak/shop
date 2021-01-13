package api.shop.shop.controller;

import api.shop.shop.model.ShopUser;
import api.shop.shop.security.configuration.JwtTokenUtil;
import api.shop.shop.security.models.JwtRequest;
import api.shop.shop.security.models.JwtResponse;
import api.shop.shop.service.UserRoleService;
import api.shop.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody JwtRequest user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        ShopUser userDetails = (ShopUser) userService.loadUserByUsername(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtTokenUtil.generateToken(userDetails)));
    }

    @PostMapping(value = "/register", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@RequestBody ShopUser user) {
        user.setRole(userRoleService.getRoleByName("ROLE_USER"));
        userService.save(user);
        return ResponseEntity.ok("Account created");
    }
}
