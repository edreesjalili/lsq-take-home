package com.jalili.lsqtakehome.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 36)
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name="uuid-generator",
        strategy = "com.jalili.lsqtakehome.internal.UuidGenerator")
    private String invoiceId;

    @Column
    private Long supplierId;

    @Column
    private java.sql.Date createdAt;

    @Column
    private Short daysTillDue;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoicePayment> payments = new ArrayList<>();   
}