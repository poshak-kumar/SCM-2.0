package com.scm.SCM_20.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    
    @Id
    private String name;
    private String email;
    private String phoneNum;
    private String address;
    private String profilePicture;
    @Column(length = 5000)
    private String discription;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;

    // One contact have only one user
    @ManyToOne
    private User user;

    // One user have multiple Social Links
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLinks> socialLinks = new ArrayList<>();

}
