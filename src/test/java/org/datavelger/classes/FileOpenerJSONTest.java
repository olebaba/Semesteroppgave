package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileOpenerJSONTest {

    @Test
    void openFile() throws IOException {
        FileOpenerJSON openerBinary = new FileOpenerJSON();
        System.out.println(openerBinary.openFile("components.json", true));
    }
}