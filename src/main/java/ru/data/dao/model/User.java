package ru.data.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn (name = "user_id"),
        inverseJoinColumns = @JoinColumn (name = "role_id"))
    private List<Role> roles;

}
