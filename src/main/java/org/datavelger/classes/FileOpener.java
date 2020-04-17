package org.datavelger.classes;

import java.io.IOException;

public interface FileOpener {
    String openFile(String path, boolean semicolon) throws IOException;
}
