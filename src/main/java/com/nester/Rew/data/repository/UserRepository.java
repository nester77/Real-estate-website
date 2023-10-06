package com.nester.Rew.data.repository;

import com.nester.Rew.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE LOWER(u.email) = LOWER(:email)", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
