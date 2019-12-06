package com.lirugo.print_service.entity;

import com.lirugo.print_service.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "google_id", unique = true)
    private String googleId;

    @Column(unique = true)
    private String email;

    private String name;
    private String picture;
    private String room;

    @Column(name = "last_visit")
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;
}
