package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileOpenerCsvTest {
    FileOpener fileOpenerCsv;

    @Test
    void openCsv() throws IOException, ClassNotFoundException {
        fileOpenerCsv = new FileOpenerCsv("file.csv", false);
        System.out.println(fileOpenerCsv.openFile("file.csv", false));
    }
}