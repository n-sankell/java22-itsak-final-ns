package com.example.demo.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Activity {
    @Id
    private UUID activityId;
    @Column(unique = true)
    private String activityName;

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

}