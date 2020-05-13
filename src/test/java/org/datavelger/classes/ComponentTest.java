package org.datavelger.classes;

import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {

    @Test
    void setPrice() throws InvalidNameException, InvalidPriceException {
        Component component = new Component(200, "null", "Component");
    }

    @Test
    void setName() {
    }
}