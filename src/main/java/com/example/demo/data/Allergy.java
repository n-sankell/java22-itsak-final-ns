package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public final class Allergy {

    @Id
    private UUID allergyId;
    @Getter
    private String type;

    public Allergy(UUID allergyId, String type) {
        this.allergyId = allergyId;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
