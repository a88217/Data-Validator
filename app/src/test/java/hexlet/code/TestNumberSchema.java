package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestNumberSchema {

    private Validator v;
    private NumberSchema schema;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public void testDefaultScheme() throws Exception {
        boolean expected = true;
        boolean actual = schema.isValid(null);
        assertEquals(expected, actual);
        actual = schema.positive().isValid(null);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemeRequired() throws Exception {
        schema.required();
        boolean expected = false;
        boolean actual = schema.isValid("5");
        assertEquals(expected, actual);
        actual = schema.isValid(null);
        assertEquals(expected, actual);
        expected = true;
        actual = schema.isValid(5);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemePositive() throws Exception {
        boolean expected = false;
        boolean actual = schema.positive().isValid(-10);
        assertEquals(expected, actual);
        actual = schema.positive().isValid(0);
        assertEquals(expected, actual);
        expected = true;
        actual = schema.positive().isValid(5);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemeRange() throws Exception {
        schema.required();
        boolean expected = false;
        boolean actual = schema.range(5, 10).isValid(11);
        assertEquals(expected, actual);
        expected = true;
        actual = schema.range(5, 10).isValid(7);
        assertEquals(expected, actual);
    }

}
