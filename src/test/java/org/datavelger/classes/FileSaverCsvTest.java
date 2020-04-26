package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverCsvTest {

    Parts parts = new Parts(new GraphicsCard(3000, "GTX1080"),
                    new Harddrive(400, "Seagate 5400", 2.5, 2),
                    new Keyboard(400, "Corsair K70", true),
                    new Memory(1200, "Kingston", "2444MHz"),
                    new Monitor(2500, "Asus TUF", 27),
                    new Motherboard(2000, "ROG STRIX", "AMD"),
                    new Mouse(699, "Logitech G502", false),
                    new Processor(2340, "Ryzen 5 3600X", "AMD"));

    Parts parts2 = new Parts();

    @Test
    void saveCsv() throws IOException {
        parts2.setMemory(new Memory(900, "GSkill", "3666MHz"));
        FileSaverCsv saverCsv = new FileSaverCsv();
        String path = "fileforexcel.csv";
        saverCsv.saveFile(path, parts, true);
    }
}