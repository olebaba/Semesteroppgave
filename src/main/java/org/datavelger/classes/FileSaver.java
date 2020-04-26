package org.datavelger.classes;

import java.io.IOException;

public interface FileSaver {
    void saveFile(String filepath, Parts parts, boolean semicolon) throws IOException;
}
