package br.com.s2v.irradiation.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city_irradiation")
public class CityIrradiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city_irradiation")
    private long id;

    @Column(name = "annual_irradiation")
    private Double annualIrradiation;

    @Column(name = "jan")
    private Double jan;

    @Column(name = "feb")
    private Double feb;

    @Column(name = "mar")
    private Double mar;

    @Column(name = "apr")
    private Double apr;

    @Column(name = "may")
    private Double may;

    @Column(name = "jun")
    private Double jun;

    @Column(name = "jul")
    private Double jul;

    @Column(name = "aug")
    private Double aug;

    @Column(name = "sep")
    private Double sep;

    @Column(name = "oct")
    private Double oct;

    @Column(name = "nov")
    private Double nov;

    @Column(name = "dec")
    private Double dec;

    @OneToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;
}
