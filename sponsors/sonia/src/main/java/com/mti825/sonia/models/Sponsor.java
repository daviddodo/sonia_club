package com.mti825.sonia.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sponsors")
@Data
@NoArgsConstructor
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<Contact> contacts;
    
    private String name;
    private String description;

    public Sponsor(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
