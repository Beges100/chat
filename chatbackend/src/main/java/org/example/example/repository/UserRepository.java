package org.example.example.repository;

import org.example.example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUserName(String username);

    Optional<UserEntity> findByEmail(String email);
}
