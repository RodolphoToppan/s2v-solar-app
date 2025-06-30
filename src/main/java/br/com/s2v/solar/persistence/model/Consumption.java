package br.com.s2v.solar.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "consumption", schema = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumption")
    private Long id;

    @Column(name = "jan")
    private BigDecimal jan;

    @Column(name = "feb")
    private BigDecimal feb;

    @Column(name = "mar")
    private BigDecimal mar;

    @Column(name = "apr")
    private BigDecimal apr;

    @Column(name = "may")
    private BigDecimal may;

    @Column(name = "jun")
    private BigDecimal jun;

    @Column(name = "jul")
    private BigDecimal jul;

    @Column(name = "aug")
    private BigDecimal aug;

    @Column(name = "sep")
    private BigDecimal sep;

    @Column(name = "oct")
    private BigDecimal oct;

    @Column(name = "nov")
    private BigDecimal nov;

    @Column(name = "dec")
    private BigDecimal dec;

}