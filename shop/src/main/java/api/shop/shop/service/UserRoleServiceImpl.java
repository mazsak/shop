package api.shop.shop.service;

import api.shop.shop.model.UserRole;
import api.shop.shop.repo.UserRoleRepo;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BasicServiceImpl<UserRole, UserRoleRepo, Long> implements UserRoleService {
    public UserRoleServiceImpl(UserRoleRepo userRoleRepo) {
        super(userRoleRepo);
    }

    @Override
    public UserRole getRoleByName(String name) {
        return repo.getByName(name);
    }
}


