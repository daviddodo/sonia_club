package com.mti825.sonia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sponsor_id", nullable = false)
    private Sponsor sponsor;

    private String fname;
    private String lname;
    private String role;
    private String email;
    private String phone;

    public Contact(String fname, String lname, String role, String email, String phone, Sponsor sponsor) {
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.sponsor = sponsor;
    }
}
