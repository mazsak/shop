package api.shop.shop.controller;

import api.shop.shop.model.ShopOrder;
import api.shop.shop.model.ShopUser;
import api.shop.shop.security.configuration.JwtTokenUtil;
import api.shop.shop.security.models.JwtRequest;
import api.shop.shop.security.models.JwtResponse;
import api.shop.shop.service.ShopOrderService;
import api.shop.shop.service.UserRoleService;
import api.shop.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRoleService userRoleService;
    private final ShopOrderService shopOrderService;
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
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserOrders(Authentication authentication) {
        ShopUser principal = (ShopUser) authentication.getPrincipal();
        return ResponseEntity.ok(principal.getOrderList());
    }

    @PostMapping(value = "/orders", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addNewOrder(@RequestBody ShopOrder order, Authentication authentication) {
        shopOrderService.save(order);
        ShopUser user = (ShopUser) authentication.getPrincipal();
        List<ShopOrder> orderList = user.getOrderList() == null ? new ArrayList<>() : user.getOrderList();
        orderList.add(order);
        userService.update(user.getId(), user);
        return ResponseEntity.ok(order);
    }
}
