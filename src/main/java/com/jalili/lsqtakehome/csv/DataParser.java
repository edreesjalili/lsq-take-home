package com.jalili.lsqtakehome.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataParser {
    public static List<Entry> parse(FileReader file) throws FileNotFoundException, IOException, CsvValidationException{
        List<Entry> records = new ArrayList<>();

        try(CSVReader reader = new CSVReader(file)) {
            String values[] = null;
            while ((values = reader.readNext()) != null) {
                records.add(Entry.from(Arrays.asList(values)));
            }
        }

        return records;
    }
}