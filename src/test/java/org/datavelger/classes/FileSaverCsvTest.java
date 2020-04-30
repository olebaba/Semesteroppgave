package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverCsvTest {

    Order order = new Order(new String("Fatima"),
                    new GraphicsCard(3000, "GTX2080"),
                    new Harddrive(400, "Seagate 5400", 2.5, 2),
                    new Keyboard(400, "Corsair K90", true),
                    new Memory(1200, "Kingston", "2444MHz"),
                    new Monitor(2500, "Asus ROG", 27),
                    new Motherboard(2000, "ROG STRIX", "Intel"),
                    new Mouse(699, "Logitech G502", false),
                    new Processor(2340, "Intel i5 7500", "Intel"));

    Order order2 = new Order();

    FileSaverCsvTest() throws InvalidNameException, InvalidPriceException {
    }

    @Test
    void saveCsv() throws IOException, InvalidNameException, InvalidPriceException {
        order2.setMemory(new Memory(900, "GSkill", "3666MHz").getName());
        FileSaverCsv saverCsv = new FileSaverCsv(order,true);
        String path = "file.csv";
        saverCsv.saveFile(path);
    }
}