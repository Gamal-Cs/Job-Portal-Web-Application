package com.shaltout.jobportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users_type")
@Getter
@Setter
public class UsersType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userTypeId;
    private String userTypeName;
    @OneToMany(targetEntity = Users.class, cascade = CascadeType.ALL, mappedBy = "userTypeId")
    private List<Users> users;

    @Override
    public String toString() {
        return "UsersType{" +
                "userTypeName='" + userTypeName + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
