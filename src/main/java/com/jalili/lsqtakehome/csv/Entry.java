package com.jalili.lsqtakehome.csv;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

// making everything public for the sake of speed
public class Entry {
    public String supplierId;
    public String invoiceId;
    public LocalDate invoiceDate;
    public BigDecimal invoiceAmount;
    public Short terms;
    public LocalDate paymentDate;
    public BigDecimal paymentAmount;

    private Entry() {};

    public static Entry from(List<String> row) {
        int i = 0;
        Entry e = new Entry();
        String rawValue;

        e.supplierId = row.get(i++);
        e.invoiceId = row.get(i++);
        e.invoiceDate = LocalDate.parse(row.get(i++));
        e.invoiceAmount = new BigDecimal(row.get(i++));
        e.terms = Short.valueOf(row.get(i++));
        
        rawValue = row.get(i++);
        
        e.paymentDate =  rawValue != null ? LocalDate.parse(row.get(i++)) : null;

        rawValue = row.get(i++);

        e.paymentAmount = rawValue != null ? new BigDecimal(row.get(i++)) : null;
        return e;
    }
}