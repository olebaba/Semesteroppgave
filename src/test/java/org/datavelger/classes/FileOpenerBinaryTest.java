package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerBinaryTest {

    @Test
    void openFile() throws IOException, ClassNotFoundException {
        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary();
        System.out.println(fileOpenerBinary.openFile("components.jobj", false));
    }
}