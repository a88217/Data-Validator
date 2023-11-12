package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStringSchema {

    Validator v;
    StringSchema schema;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator() ;
        schema = v.string();
    }

    @Test
    public void testDefaultScheme() throws Exception {
        boolean expected = true;
        boolean actual = schema.isValid("");
        assertEquals(expected, actual);
        actual = schema.isValid(null);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemeRequired() throws Exception {
        schema.required();
        boolean expected = false;
        boolean actual = schema.isValid("");
        assertEquals(expected, actual);
        actual = schema.isValid(null);
        assertEquals(expected, actual);
        actual = schema.isValid(5);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemeContains() throws Exception {
        schema.required();
        boolean expected = true;
        boolean actual = schema.contains("wh").isValid("what does the fox say");
        assertEquals(expected, actual);
        actual = schema.contains("what").isValid("what does the fox say");
        assertEquals(expected, actual);
        expected = false;
        actual = schema.contains("whatthe").isValid("what does the fox say");
        assertEquals(expected, actual);
    }


}
