package org.datavelger.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSaverJSON implements FileSaver {
    private final Component component;

    public FileSaverJSON(Component component){
        this.component = component;
    }

    @Override
    public void saveFile(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        StringBuilder newFile = new StringBuilder();

        if(!Files.exists(path)) Files.createFile(path);
        else {
            FileOpenerJSON fileOpenerJSON = new FileOpenerJSON();
            String prevFile = fileOpenerJSON.openFile(path.toString(), true);
            String[] prevFileArray = prevFile.split(System.lineSeparator());

            for (int i = 0; i<prevFileArray.length-1; i++){
                newFile.append(prevFileArray[i]).append(i == prevFileArray.length - 2 ? "" : System.lineSeparator());
            }
        }

        try {

            Files.writeString(path, newFile + "," + System.lineSeparator() +
                    "  {" + component.toJSON() +  "}" + System.lineSeparator() + "]", StandardOpenOption.WRITE);
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
