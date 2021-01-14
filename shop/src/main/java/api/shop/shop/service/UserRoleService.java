package api.shop.shop.service;

import api.shop.shop.model.UserRole;

public interface UserRoleService extends BasicService<UserRole, Long> {
    public UserRole getRoleByName(String name);
}
