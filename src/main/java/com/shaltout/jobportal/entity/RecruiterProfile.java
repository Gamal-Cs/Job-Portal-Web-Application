package com.shaltout.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recruiter_profile")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecruiterProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userAccountId;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private Users userId;

    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private  String company;
    @Column(nullable = true, length = 64)
    private String profilePhoto;
    public RecruiterProfile(Users users){
        this.userId = users;
    }
}
