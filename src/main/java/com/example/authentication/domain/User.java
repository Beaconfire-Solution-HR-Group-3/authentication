package com.example.authentication.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String createDate;
    @Column
    private String modificationDate;



}