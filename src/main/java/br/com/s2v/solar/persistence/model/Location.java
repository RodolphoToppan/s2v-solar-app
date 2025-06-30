package br.com.s2v.solar.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "location", schema = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Long id;

    private String state;
    private String city;
    private String address;

    @OneToMany(mappedBy = "location")
    private List<Client> clients;

    @OneToMany(mappedBy = "location")
    private List<Project> projects;

    @OneToMany(mappedBy = "location")
    private List<Bill> bills;
}
