package com.nester.Rew.data.repository;

import com.nester.Rew.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
