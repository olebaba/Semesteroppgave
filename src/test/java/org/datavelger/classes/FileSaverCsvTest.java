package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverCsvTest {

    Order order = new Order("Fatima",
                    new GraphicsCard(3000, "GTX2080", "Grafikkort"),
                    new Harddrive(400, "Seagate 5400", "Harddisk", 2.5, 2),
                    new Keyboard(400, "Corsair K90", "Tastatur", true),
                    new Memory(1200, "Kingston", "Minne", "2444MHz"),
                    new Monitor(2500, "Asus ROG", "Skjerm", 27),
                    new Motherboard(2000, "ROG STRIX", "Hovedkort", "Intel"),
                    new Mouse(699, "Logitech G502", "Mus", false),
                    new Processor(2340, "Intel i5 7500", "Prosessor", "Intel"));

    Order order2 = new Order();

    FileSaverCsvTest() throws InvalidNameException, InvalidPriceException {
    }

    @Test
    void saveCsv() throws IOException, InvalidNameException, InvalidPriceException {
        order2.setMemory(new Memory(900, "GSkill", "Memory", "3666MHz").getName());
        FileSaverCsv saverCsv = new FileSaverCsv(order,true);
        String path = "orders.csv";
        saverCsv.saveFile(path);
    }
}