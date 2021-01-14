package api.shop.shop.controller;

import api.shop.shop.model.Product;
import api.shop.shop.model.ShopItem;
import api.shop.shop.model.ShopOrder;
import api.shop.shop.model.ShopUser;
import api.shop.shop.security.configuration.JwtTokenUtil;
import api.shop.shop.security.models.JwtRequest;
import api.shop.shop.security.models.JwtResponse;
import api.shop.shop.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final ShopItemService shopItemService;
    private final ProductService productService;
    private final ShopOrderService shopOrderService;

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
    public ResponseEntity<?> addNewOrder(@RequestBody List<Product> items, Authentication authentication) {
        ShopOrder order = new ShopOrder();
        List<ShopItem> shopItems = new ArrayList<>();
        for (Product product : items) {
            if (shopItems.isEmpty()) {
                ShopItem item = new ShopItem();
                item.setProduct(product);
                item.setAmount(1);
                shopItems.add(item);
            } else {
                boolean newItem = true;
                for (ShopItem it : shopItems) {
                    if (it.getProduct().getId().equals(product.getId())) {
                        it.setAmount(it.getAmount() + 1);
                        newItem = false;
                        break;
                    }
                }
                if (newItem) {
                    ShopItem item = new ShopItem();
                    item.setProduct(product);
                    item.setAmount(1);
                    shopItems.add(item);
                }

            }
        }
        for (ShopItem shopItem : shopItems) {
            Product product = shopItem.getProduct();
            product.setStockAmount(product.getStockAmount()-shopItem.getAmount());
            productService.save(product);
        }
        shopItemService.saveAll(shopItems);
        order.setShopItems(shopItems);
        order.setTotalPrice(items.stream().mapToDouble(Product::getPrice).sum());
        shopOrderService.save(order);
        ShopUser user = (ShopUser) authentication.getPrincipal();
        List<ShopOrder> orderList = user.getOrderList() == null ? new ArrayList<>() : user.getOrderList();
        orderList.add(order);
        userService.update(user.getId(), user);
        return ResponseEntity.ok(order);
    }
}
