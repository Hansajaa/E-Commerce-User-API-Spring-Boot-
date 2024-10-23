package org.clothify.repository;

import org.clothify.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
    LoginEntity findByUserName(String username);
}
