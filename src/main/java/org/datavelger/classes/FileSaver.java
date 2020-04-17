package org.datavelger.classes;

import java.io.IOException;
import java.util.List;

public interface FileSaver {
    void saveFile(Parts parts, boolean semicolon) throws IOException;
}
