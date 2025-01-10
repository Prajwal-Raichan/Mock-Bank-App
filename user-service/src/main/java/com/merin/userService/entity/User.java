package com.merin.userService.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="TB_UserDetails")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private int userId;

    @Column(name = "Username",unique = true)
    private String userName;

    @Column(name="User_Password")
    private String userPassword;

    @Column(name = "UserRole")
    private String userRole;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

}
