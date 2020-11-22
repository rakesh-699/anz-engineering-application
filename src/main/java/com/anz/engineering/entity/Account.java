package com.anz.engineering.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class for Account.
 */
@Entity
@Table(name = "account")
public @Data class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long number;

    @Column
    private String name;

    @Column
    private String type;

    @Column(name="bal_date")
    private LocalDate balDate;

    @Column
    private String currency;

    @Column(name = "avail_bal", precision = 12, scale = 2)
    private BigDecimal availableBal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;

}
