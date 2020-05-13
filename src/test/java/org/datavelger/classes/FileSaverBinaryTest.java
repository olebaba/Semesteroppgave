package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverBinaryTest {
    @Test
    void saveFile() throws InvalidNameException, InvalidPriceException, IOException {
        Keyboard keyboard = new Keyboard(200, "Best keyboard", false);
        Processor processor = new Processor(4000, "i5", "Intel");
        GraphicsCard graphicsCard = new GraphicsCard(2999, "RTX 2070 SUPER");
        FileSaverBinary fileSaverBinary = new FileSaverBinary(graphicsCard);
        fileSaverBinary.saveFile("components/graphicsCards/" + graphicsCard.getName() + ".jobj");


    }

}