package com.xunhui.gateway.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_user_roles")
@Data
public class UserRoles implements Serializable{
    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(nullable = true, length = 64)
    private String role;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false, updatable=false)
    private Users user;
}
