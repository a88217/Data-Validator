package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMapSchema {

    Validator v;
    MapSchema schema;
    Map<Integer, String> inputMap, emptyMap;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator() ;
        schema = v.map();
        inputMap = Map.of(1, "one", 2, "two");
        emptyMap = new HashMap<>();
    }

    @Test
    public void testDefaultScheme() throws Exception {
        boolean expected = true;
        boolean actual = schema.isValid(null);
        assertEquals(expected, actual);
        actual = schema.isValid(5);
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
        actual = schema.isValid(inputMap);
        assertEquals(expected, actual);
        actual = schema.isValid(emptyMap);
        assertEquals(expected, actual);
    }

    @Test
    public void testSchemeSizeOf() throws Exception {
        boolean expected = false;
        boolean actual = schema.sizeof(3).isValid(inputMap);
        assertEquals(expected, actual);
        actual = schema.sizeof(3).isValid(emptyMap);
        assertEquals(expected, actual);
        expected = true;
        actual = schema.sizeof(2).isValid(inputMap);
        assertEquals(expected, actual);
    }

}
