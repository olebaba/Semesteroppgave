package org.datavelger.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileOpenerBinary implements FileOpener {
    @Override
    public String openFile(String path, boolean semicolon) throws IOException {
        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath());
        StringBuilder sb = new StringBuilder();
        String regex = "^Computer\\{([\\w]*\\{[\\d]*;[\\w ]*(;\\d,\\d*;\\d)?(;\\w*)?}){8}}" +
                "|^Parts\\{(([\\w]*\\{[\\d]*;?[\\w ]*(;\\d,\\d*;\\d)?(;\\w*)?}){8})?}";
        for (String line : lines){
            if(line.matches(regex)) sb.append(line).append(System.lineSeparator());
            else throw new IOException("File is corrupt.");
        }
        return sb.toString();
    }
}
