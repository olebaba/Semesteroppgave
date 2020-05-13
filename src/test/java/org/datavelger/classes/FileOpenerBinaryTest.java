package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerBinaryTest {
    final File folder = new File("components");

    @Test
    void openFile() throws IOException, ClassNotFoundException {
        /*FileOpenerBinary fileOpenerBinary = new FileOpenerBinary("components");
        for (Component comp : fileOpenerBinary.getValue()){
            System.out.println(comp);
        }*/
    }
}