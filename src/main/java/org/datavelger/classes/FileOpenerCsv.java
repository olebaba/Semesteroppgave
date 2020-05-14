package org.datavelger.classes;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileOpenerCsv extends Task<List<List<String>>> implements FileOpener {
    private String path;
    private boolean semicolon;

    public FileOpenerCsv(String path, boolean semicolon){
        this.path = path;
        this.semicolon = semicolon;
    }

    public static Component findComponent(String component) throws IOException, ClassNotFoundException {
        Component foundComp = new Component();
        String folder = "komponenter";
        for (File file : Objects.requireNonNull(new File(folder).listFiles())){
            if (file.isDirectory()){
                for (File fileInFolder : Objects.requireNonNull(file.listFiles())){
                    if (fileInFolder.getName().equals(component+".jobj")){
                        FileOpenerBinary fileOpenerBinary = new FileOpenerBinary(folder);
                        foundComp = fileOpenerBinary.openFile(fileInFolder.getPath(), true);
                        //System.out.println(foundComp.toString());
                    }
                    //System.out.println(fileInFolder.getPath() + " er ikke " + component);
                }
            }
        }
        return foundComp;
    }

    @Override
    public String openFile(String path, boolean semicolon) {
        return null;
    }

    @Override
    protected List<List<String>> call() throws Exception {
        List<List<String>> list = new ArrayList<>(9);

        try{
            Thread.sleep(3000); //vise at GUI blir slått av
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        String delimiter = ",";
        if(semicolon) delimiter = ";";

        File file = new File(path);

        try (Scanner lineScanner = new Scanner(file)){
            int number = 0;
            while (lineScanner.hasNextLine()){
                list.add(new ArrayList<>());
                String line = lineScanner.nextLine();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(delimiter);
                while (scanner.hasNext()){
                    list.get(number).add(scanner.next());
                }

                //System.out.println("new line");
                number++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
