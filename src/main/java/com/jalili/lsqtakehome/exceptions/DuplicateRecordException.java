package com.jalili.lsqtakehome.exceptions;

public class DuplicateRecordException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 5771308585205461116L;

    DuplicateRecordException() {
        super("Duplicate record found in data");
    }
}