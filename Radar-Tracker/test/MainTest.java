import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testMainClassLoads() {
        // Ensure the Main class is present and loadable
        assertNotNull(Main.class);
    }
}
