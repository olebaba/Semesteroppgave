package org.datavelger.classes;

import java.io.IOException;
import java.util.List;

public interface FileOpener {
    String openFile(String path, boolean semicolon) throws IOException, ClassNotFoundException;
}
