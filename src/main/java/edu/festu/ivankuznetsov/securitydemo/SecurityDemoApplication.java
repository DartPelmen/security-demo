package edu.festu.ivankuznetsov.securitydemo;

import edu.festu.ivankuznetsov.securitydemo.entity.Account;
import edu.festu.ivankuznetsov.securitydemo.entity.Role;
import edu.festu.ivankuznetsov.securitydemo.repository.AccountRepository;
import edu.festu.ivankuznetsov.securitydemo.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SecurityDemoApplication {
    private static AccountRepository userRepository = null;
    private static RoleRepository repository = null;

    public SecurityDemoApplication(AccountRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
        Account user = new Account("admin",new BCryptPasswordEncoder().encode("admin"));
        user.setLocked(false);
        user.setEnabled(true);
        user.setAccountExpired(false);
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        userRepository.save(user);
        repository.save(role);
        user.setRoleList(List.of(role));
        userRepository.save(user);
        role.setUsers(List.of(user));
        repository.save(role);
    }

}
