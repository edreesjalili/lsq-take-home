package com.jalili.lsqtakehome.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "invoices", uniqueConstraints = @UniqueConstraint(columnNames={"invoice_id", "supplier_id"}))
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 36, name = "invoice_id")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name="uuid-generator",
        strategy = "com.jalili.lsqtakehome.internal.UuidGenerator")
    private String invoiceId;

    @Column(name = "created_at")
    private java.sql.Date createdAt;

    @Column(name = "days_till_due")
    private Short daysTillDue;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplierId;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoicePayment> payments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public java.sql.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Date createdAt) {
        this.createdAt = createdAt;
    }

    public Short getDaysTillDue() {
        return daysTillDue;
    }

    public void setDaysTillDue(Short daysTillDue) {
        this.daysTillDue = daysTillDue;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    public List<InvoicePayment> getPayments() {
        return payments;
    }
}