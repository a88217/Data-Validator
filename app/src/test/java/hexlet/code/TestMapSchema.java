package hexlet.code;

import hexlet.code.schemas.MapSchema;
import net.sf.saxon.ma.map.MapFunctionSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMapSchema {

    Validator v;
    MapSchema schema;
    Map<Integer, String> inputMap;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator() ;
        schema = v.map();
        inputMap = Map.of(1, "one", 2, "two");
    }

    @Test
    public void testDefaultScheme() throws Exception {
        boolean expected = true;
        boolean actual = schema.isValid(null);
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
    }

    @Test
    public void testSchemeSizeOf() throws Exception {
        schema.required();
        boolean expected = false;
        boolean actual = schema.sizeOf(3).isValid(inputMap);
        assertEquals(expected, actual);
        expected = true;
        actual = schema.sizeOf(2).isValid(inputMap);
        assertEquals(expected, actual);
    }

}
