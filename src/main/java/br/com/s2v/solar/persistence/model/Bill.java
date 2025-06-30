package br.com.s2v.solar.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill", schema = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Long id;

    @Column(name = "uc")
    private Long uc;

    @Column(name = "holder")
    private String holder;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "tariff")
    private BigDecimal tariff;

    @Column(name = "average_consumption")
    private BigDecimal averageConsumption;

    @ManyToOne
    @JoinColumn(name = "id_consumption")
    private Consumption consumption;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}