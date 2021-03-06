package api.shop.shop.service;

import api.shop.shop.model.ShopUser;
import api.shop.shop.repo.ShopUserRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BasicServiceImpl<ShopUser, ShopUserRepo, Long> implements UserService {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(final ShopUserRepo userRepo, @Lazy final PasswordEncoder passwordEncoder) {
        super(userRepo);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(ShopUser object) {
        if (!repo.existsByUsername(object.getUsername())) {
            object.setPassword(passwordEncoder.encode(object.getPassword()));
            return super.save(object);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Long id, ShopUser user) {
        user.setId(id);
        return super.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser shopUser = new ShopUser();
        try {
            shopUser = repo.findByUsername(username).orElseThrow((() ->
                    new UsernameNotFoundException("User with that login doesn't exist")));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return shopUser;
    }
}