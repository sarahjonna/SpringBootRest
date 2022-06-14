package com.eve.salon.jwt.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eve.salon.jwt.security.models.ERole;
import com.eve.salon.jwt.security.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole roleUser);

}
