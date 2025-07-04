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
@Table(name = "club_reps")
@Data
@NoArgsConstructor
public class ClubRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "clubRep", cascade = CascadeType.ALL)
    private List<Contribution> contributions;

    private String fname;
    private String lname;
    private String email;
    private String phone;

    public ClubRep(String fname, String lname, String email, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return String.format("%s %s", fname, lname);
    }
}
