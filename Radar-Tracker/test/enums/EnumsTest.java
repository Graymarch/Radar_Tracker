package enums;

import org.junit.Test;
import static org.junit.Assert.*;

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
