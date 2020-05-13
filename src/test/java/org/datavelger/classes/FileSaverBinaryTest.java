package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverBinaryTest {
    @Test
    void saveFile() throws InvalidNameException, InvalidPriceException, IOException {
        Keyboard keyboard = new Keyboard(200, "Best keyboard", "Tastatur", false);
        Processor processor = new Processor(4000, "i5", "Prosessor", "Intel");
        GraphicsCard graphicsCard = new GraphicsCard(2999, "RTX 2070 SUPER", "Grafikkort");
        FileSaverBinary fileSaverBinary = new FileSaverBinary(keyboard);
        fileSaverBinary.saveFile("komponenter/"+ keyboard.getCompType() +"/" + keyboard.getName() + ".jobj");


    }

}