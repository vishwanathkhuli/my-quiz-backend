package com.example.my_quiz_backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.my_quiz_backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

}
