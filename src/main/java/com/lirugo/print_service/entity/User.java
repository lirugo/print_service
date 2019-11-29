package com.lirugo.print_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String picture;
    private String room;
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;
}
