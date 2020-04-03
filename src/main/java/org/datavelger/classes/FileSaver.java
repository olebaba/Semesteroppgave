package org.datavelger.classes;

import java.io.IOException;

public interface FileSaver {
    void saveCsv(Computer computer, boolean semicolon) throws IOException;
}
