package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverBinaryTest {
    Order order = new Order(new String("Ole"),
            new GraphicsCard(3000, "GTX1080"),
            new Harddrive(400, "Seagate 5400", 2.5, 2),
            new Keyboard(400, "Corsair K70", true),
            new Memory(1200, "Kingston", "2444MHz"),
            new Monitor(2500, "Asus TUF", 27),
            new Motherboard(2000, "ROG STRIX", "AMD"),
            new Mouse(699, "Logitech G502", false),
            new Processor(2340, "Ryzen 5 3600X", "AMD"));
    Order order2 = new Order();

    FileSaverBinaryTest() throws InvalidNameException, InvalidPriceException {
    }


    @Test
    void saveFile() throws IOException, InvalidNameException, InvalidPriceException {
        order2.setKeyboard(new Keyboard(300, "Logitech g30", false).getName());
        FileSaverBinary saverBinary = new FileSaverBinary(new Processor(2999, "i5 7600", "Intel"));
        String path = "components.bin";
        saverBinary.saveFile(path);
    }
}