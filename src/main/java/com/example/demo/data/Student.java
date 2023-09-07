package com.example.demo.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Student {

    @Id
    private UUID studentId;
    @Column(unique = true)
    private String userName;
    private String name;
    private String gender;
    @Column(unique = true)
    private String personalIdentityNumber;
    private String phoneNumber;
    private String password;
    @OneToMany (fetch = FetchType.EAGER)
    private List<Allergy> allergies;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Activity> activities;
    @ManyToMany (fetch = FetchType.EAGER)
    private List<Role> roles;

    public void addAllergy(Allergy allergy) {
        allergies.add(allergy);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

}