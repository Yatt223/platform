package com.formation.platform.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.platform.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}
