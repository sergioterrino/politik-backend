package com.stb.politik.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);
  User findByPhone(String phone);
  User findByEmail(String email);
  // User findByUsername(String username);
  Optional<User> findByUsername(String username);
}
