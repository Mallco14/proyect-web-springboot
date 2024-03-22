package com.jhon.startup.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private boolean enabled;
    @Column(name="role")
    private String role;


}
