package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestApp {
    @Test
    public void testMain() throws Exception {
        String expected = "Hello World!";
        String actual = App.hello();
        assertEquals(expected, actual);
    }
}
