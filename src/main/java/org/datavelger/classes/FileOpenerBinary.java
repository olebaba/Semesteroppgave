package org.datavelger.classes;

import javafx.concurrent.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileOpenerBinary extends Task<List<Component>> implements FileOpener {
    private final String folderPath;

    public FileOpenerBinary(String folderpath) {
        this.folderPath = folderpath;
    }

    @Override
    public Component openFile(String path, boolean semicolon) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (Component) objectInputStream.readObject();
    }

    @Override
    protected List<Component> call() throws Exception {
        ArrayList<Component> components = new ArrayList<>();

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (!new File(folderPath).exists()) throw new FileNotFoundException("Ingen slike filer.");
        else {
            for (final File file : Objects.requireNonNull(new File(folderPath).listFiles())) {
                if (file.isDirectory()) {
                    //print herfra
                    for (final File fileInDirectory : Objects.requireNonNull(file.listFiles())) {
                        //System.out.println(fileOpenerBinary.openFile(fileInDirectory.getPath(), false));
                        components.add(openFile(fileInDirectory.getPath(), false));
                    }
                } else {
                    //System.out.println(fileOpenerBinary.openFile(file.getPath(), false));
                    components.add(openFile(file.getPath(), false));
                }
            }
        }

        return components;
    }
}
