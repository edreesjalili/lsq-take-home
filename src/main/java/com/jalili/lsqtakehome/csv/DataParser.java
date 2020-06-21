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
    public static List<Entry> parse(String fileName) throws FileNotFoundException, IOException, CsvValidationException{
        List<Entry> records = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String values[] = null;
            while ((values = reader.readNext()) != null) {
                records.add(Entry.valueOf(Arrays.asList(values)));
            }
        }

        return records;
    }
}