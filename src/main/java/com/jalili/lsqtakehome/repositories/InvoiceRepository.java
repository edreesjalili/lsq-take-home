package com.jalili.lsqtakehome.repositories;

import com.jalili.lsqtakehome.entities.Invoice;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    
}