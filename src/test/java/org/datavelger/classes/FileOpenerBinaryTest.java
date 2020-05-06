package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerBinaryTest {

    @Test
    void openFile() throws IOException {
        FileOpenerBinary openerBinary = new FileOpenerBinary();
        System.out.println(openerBinary.openFile("components.json", true));
    }
}