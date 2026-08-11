package enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnumsTest {
    @Test
    public void testSizeClassValues() {
        sizeClass[] vals = sizeClass.values();
        assertEquals(3, vals.length);
    }

    @Test
    public void testThreatTypeValues() {
        threatType[] vals = threatType.values();
        assertEquals(2, vals.length);
    }
}
