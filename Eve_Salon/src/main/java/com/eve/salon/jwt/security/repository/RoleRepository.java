package com.eve.salon.jwt.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eve.salon.jwt.security.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
