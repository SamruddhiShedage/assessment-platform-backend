package com.samruddhi.assessment_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samruddhi.assessment_platform.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

}
