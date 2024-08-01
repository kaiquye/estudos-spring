package com.auth.auth.modules.user;


import com.auth.auth.modules.user.enums.ERoles;
import com.auth.auth.shared.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "tb_role")
public class RoleModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private ERoles role;
    private String description;
    @ManyToMany(mappedBy = "roles")
    private HashSet<UserModel> users;

    public RoleModel(Long id, ERoles role, String description, HashSet<UserModel> users) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.users = users;
    }

    public RoleModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERoles getRole() {
        return role;
    }

    public void setRole(ERoles role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<UserModel> getUsers() {
        return users;
    }

    public void setUsers(HashSet<UserModel> users) {
        this.users = users;
    }
}
