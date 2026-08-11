import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testMainClassLoads() {
        // Ensure the Main class is present and loadable
        assertNotNull(Main.class);
    }
}
