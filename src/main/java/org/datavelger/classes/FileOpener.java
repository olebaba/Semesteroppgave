package org.datavelger.classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {
    String openFile(String path, boolean semicolon) throws IOException;
}
