package com.jalili.lsqtakehome.repositories;

import com.jalili.lsqtakehome.entities.InvoicePayment;

import org.springframework.data.repository.CrudRepository;

public interface InvoicePaymentRepository extends CrudRepository<InvoicePayment, Long> {
    
}