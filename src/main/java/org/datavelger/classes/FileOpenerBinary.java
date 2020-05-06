package org.datavelger.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileOpenerBinary implements FileOpener {

    @Override
    public String openFile(String path, boolean semicolon) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Keyboard keyboard = (Keyboard) objectInputStream.readObject();
        return keyboard.toString();
    }
}
