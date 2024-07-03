package com.stb.politik.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);
  Optional<User> findByUsername(String username);
  User findByPhone(String phone);
  User findByEmail(String email);
  boolean existsByEmail(String email);
  boolean existsByPhone(String phone);
  boolean existsByUsername(String username);
}
