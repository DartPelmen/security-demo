package edu.festu.ivankuznetsov.securitydemo.repository;

import edu.festu.ivankuznetsov.securitydemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
        @Transactional
        @Query("SELECT t FROM Account t LEFT JOIN FETCH t.roleList where t.username = ?1")
        Optional<Account> findByUsername(String username);
        }
