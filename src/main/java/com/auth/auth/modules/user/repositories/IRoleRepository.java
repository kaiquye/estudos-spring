package com.auth.auth.modules.user.repositories;

import com.auth.auth.modules.user.RoleModel;
import com.auth.auth.modules.user.enums.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByRole(ERoles role);
}
