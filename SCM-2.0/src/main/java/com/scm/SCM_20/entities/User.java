package com.scm.SCM_20.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false , unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 5000)
    private String about;
    private String phoneNum;
    @Column(length = 5000)
    private String profilePicLink;

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneNumVerified = false;

    // Which auth provider user use to sign-up -> like {SELF, GOOGL, GITHUB} , BY-DEFAULT --> SELF
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // One user have multiple contact
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
    
    
    // Add the more field if needed
}
