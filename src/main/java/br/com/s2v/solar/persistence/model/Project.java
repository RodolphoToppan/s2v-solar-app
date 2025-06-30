package br.com.s2v.solar.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project", schema = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;

    @Column(name = "ground")
    private Boolean ground;

    @Column(name = "power")
    private BigDecimal power;

    @Column(name = "modules_brand")
    private String modulesBrand;

    @Column(name = "modules_power")
    private BigDecimal modulesPower;

    @Column(name = "modules_quantity")
    private Integer modulesQuantity;

    @Column(name = "inverter_brand")
    private String inverterBrand;

    @Column(name = "inverter_quantity")
    private Integer inverterQuantity;

    @Column(name = "inverter_power")
    private BigDecimal inverterPower;

    @Column(name = "generation")
    private BigDecimal averageGeneration;

    @OneToOne
    @JoinColumn(name = "id_month_generation")
    private MonthGeneration monthGeneration;

    @OneToMany(mappedBy = "project")
    private List<Bill> bills;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}