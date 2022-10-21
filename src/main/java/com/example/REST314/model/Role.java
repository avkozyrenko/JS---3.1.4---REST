package com.example.REST314.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(name = "role")
    @NotEmpty(message = "Назначьте права доступа новому пользователю")
    private String role;


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int role_id, String role) {
        this.role_id = role_id;
        this.role = role;

    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserRole() {
        return role;
    }


    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role_id == role1.role_id && Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role);
    }

    @Override
    public String toString() {
        return role.substring(5);
    }

}
