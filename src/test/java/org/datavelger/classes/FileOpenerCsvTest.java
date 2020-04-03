package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileOpenerCsvTest {

    @Test
    void openCsv() throws IOException {
        FileOpenerCsv openerCsv = new FileOpenerCsv();
        openerCsv.openFile("file.csv", false);
    }
}