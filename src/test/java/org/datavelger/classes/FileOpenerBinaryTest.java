package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenerBinaryTest {
    final File folder = new File("components");

    @Test
    void openFile() throws IOException, ClassNotFoundException {
        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary();
        for (final File file : Objects.requireNonNull(folder.listFiles())){
            if(file.isDirectory()){
                //print herfra
                for (final File fileInDirectory : Objects.requireNonNull(file.listFiles())){
                    System.out.println(fileOpenerBinary.openFile(fileInDirectory.getPath(), false));
                }
            }else {
                System.out.println(fileOpenerBinary.openFile(file.getPath(), false));
            }
        }
        //System.out.println(fileOpenerBinary.openFile("components/keyboards/*.jobj", false));
    }
}