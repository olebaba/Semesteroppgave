package org.datavelger.classes;

import java.io.*;

public class FileSaverBinary implements FileSaver{
    private final Component component;

    public FileSaverBinary(Component component){
        this.component = component;
    }
    @Override
    public void saveFile(String filepath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(component);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found.");
        }catch (IOException e){
            System.out.println("IO error.");
            e.printStackTrace();
        }
    }
}
