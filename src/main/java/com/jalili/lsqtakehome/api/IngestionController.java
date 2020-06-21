package com.jalili.lsqtakehome.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import com.jalili.lsqtakehome.csv.DataParser;
import com.jalili.lsqtakehome.csv.Entry;
import com.jalili.lsqtakehome.services.EntryIngestionService;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/v1/ingestions")
public class IngestionController {
    private final static String CSV_FILES_RESOURCE_PATH = "classpath:/data/*.csv";

    @Autowired
    EntryIngestionService service;

    private static File loadCsvFileByName(String name) throws IOException {
        return new PathMatchingResourcePatternResolver(IngestionController.class.getClassLoader())
                .getResource(CSV_FILES_RESOURCE_PATH).getFile();
    }

    // should probably be moved to some other service class
    private static void loadEntries(List<Entry> entries) {

    }

    @GetMapping(value = "/")
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(ServerErrorException.class)
    public List<String> index() throws IOException {

        return Arrays
                .asList(new PathMatchingResourcePatternResolver(IngestionController.class.getClassLoader())
                        .getResources(CSV_FILES_RESOURCE_PATH))
                .stream().map(Resource::getFilename).map(name -> name.replaceFirst(".csv", ""))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/{name}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ExceptionHandler(ServerErrorException.class) // should create custom exception handler for duplicate errors
    public void ingest(@PathVariable("name") String name) {
        try {
            File file = IngestionController.loadCsvFileByName(name);
            service.ingestAll(DataParser.parse(new FileReader(file)));
        } catch (IOException|CsvValidationException e) {
            throw new RuntimeException("There was an error with the csv file.", e);
        }
    }
}