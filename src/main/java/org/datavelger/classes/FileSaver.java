package org.datavelger.classes;

import java.io.IOException;

public interface FileSaver {
    void saveFile(Computer computer, boolean semicolon) throws IOException;
}
