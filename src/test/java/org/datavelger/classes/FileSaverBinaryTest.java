package org.datavelger.classes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileSaverBinaryTest {
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
    void saveFile() throws IOException {
        parts2.setKeyboard(new Keyboard(300, "Logitech g30", false));
        FileSaverBinary saverBinary = new FileSaverBinary();
        String path = "file.csv";
        saverBinary.saveFile(path, parts2, false);
    }
}