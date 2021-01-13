package api.shop.shop.service;

import api.shop.shop.model.ShopUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BasicService<ShopUser, Long>, UserDetailsService {

    public boolean update(Long id, ShopUser user);
}
