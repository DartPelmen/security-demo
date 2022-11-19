package edu.festu.ivankuznetsov.securitydemo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    private String roleName;

    @ManyToMany
    @JoinTable(name = "account_roles",
            joinColumns =@JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Account> users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
}
