package com.xunhui.gateway.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_users")
@Data
public class Users implements Serializable{
    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(nullable = true, length = 64)
    private String number;

    @Column(nullable = true, length = 64)
    private String userName;

    @Column(nullable = true, length = 64)
    private String email;

    @Column(nullable = true, length = 64)
    private String password;

    @Column(nullable = true)
    private boolean enabled;
}
