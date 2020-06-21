package com.jalili.lsqtakehome.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.jalili.lsqtakehome.csv.Entry;
import com.jalili.lsqtakehome.entities.Invoice;
import com.jalili.lsqtakehome.entities.Supplier;
import com.jalili.lsqtakehome.repositories.InvoicePaymentRepository;
import com.jalili.lsqtakehome.repositories.InvoiceRepository;
import com.jalili.lsqtakehome.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntryIngestionService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
     private InvoicePaymentRepository invoicePaymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private void createSuppliers(List<Entry> entries) {
        Set<String> suppliers = entries
        .stream()
        .map(e -> e.supplierId)
        .collect(Collectors.toSet());

        supplierRepository.findAll()
        .forEach(s ->  suppliers.remove(s.getName()));


        List<Supplier> newSuppliers = suppliers.stream().map(s -> {
            Supplier sup = new Supplier();
            sup.setName(s);
            return sup;
        })
        .collect(Collectors.toList());

        supplierRepository.saveAll(newSuppliers);
    }

    private void createInvoices(List<Entry> entries) {
        // the file can have duplicate ids for invoice but that is because
        // it is used to track multiple payments for an invoice (if that is a concept for the system)
        // edit: actually nevermind that is not a concept

        // i've been thinking about this incorrectly this entire time
        // i thought an invoice could only have one supplier but
        // i just realized that an invoice can have multiple suppliers, which make a lot more sense
        // I think this just invlidated my entire strategy here, my data model relations are incorrect
        // should have been doing:
            // invoice can have many suppliers
            // suppliers can have many invoices
            // therefore an invoice can only have one payment

        // lets see if I can fix this in whatever time I got left
        //map all invoice ids to their unqiue to determine duplicates

        // eagerly map name to supplier for easier matching
        // why did i not just let the name be the id
        Map<String, Supplier> suppliersByName = new HashMap<>(1000);
        supplierRepository.findAll().forEach(supplier -> suppliersByName.put(supplier.getName(), supplier));


        // create a mapping of invoice uuids to supplier
        // if we run into one that exists throw a duplicate error here
        // Map<String, String> duplicates;



        Set<String> ids = entries
        .stream()
        .map(e -> e.invoiceId)
        .collect(Collectors.toSet());

    }

    // need to find out how to catch the transaction failure to throw as duplicate record found
    // technically not always true but assuming everything else is correct it should be the only
    // failure due to constraints
    @Transactional
    public void ingestAll(List<Entry> entries) {
        // save all supplier to have ids generated
        createSuppliers(entries);

        createInvoices(entries);
    }
}