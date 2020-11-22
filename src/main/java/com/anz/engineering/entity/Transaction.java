package com.anz.engineering.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class for Transaction.
 */
@Entity
@Table(name = "transactions")
public @Data class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column
    private Long id;

    @Column(name="value_date")
    private LocalDate valueDate;

    @Column(name = "debit_amt", precision = 12, scale = 2)
    private BigDecimal debitAmt;

    @Column(name = "credit_amt", precision = 12, scale = 2)
    private BigDecimal creditAmt;

    @Column(name = "debit_credit")
    private String debitCredit;

    @Column(name = "transaction_narrative")
    private String transNarative;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_number", referencedColumnName = "number")
    private Account account;

}
