package edu.festu.ivankuznetsov.securitydemo.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Account implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany
    @JoinTable(name="account_roles",
    joinColumns =@JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<Role> roleList;
    private boolean enabled;
    private boolean credentialExpired;
    private boolean locked;
    private boolean accountExpired;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList
                .stream()
                .map(role -> (GrantedAuthority) role::getRoleName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }
}
