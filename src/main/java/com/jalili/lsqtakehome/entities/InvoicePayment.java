package com.jalili.lsqtakehome.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_payments")
public class InvoicePayment {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private java.sql.Date paymentDate;

    @Column(nullable = true)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}