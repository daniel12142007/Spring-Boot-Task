package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "locatedCountry")
    private String locatedCountry;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Groups> groups;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    @Override
    public String toString() {
        return "Company{" +
               "id=" + id +
               ", companyName='" + companyName + '\'' +
               ", locatedCountry='" + locatedCountry + '\'' +
               '}';
    }
}