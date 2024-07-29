package com.auth.auth.modules.user.repositories;

import com.auth.auth.modules.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}
