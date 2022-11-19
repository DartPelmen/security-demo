package edu.festu.ivankuznetsov.securitydemo.repository;

import edu.festu.ivankuznetsov.securitydemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role,String> {
}
