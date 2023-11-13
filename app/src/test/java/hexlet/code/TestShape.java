package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShape {

    Validator v;
    MapSchema schema;
    Map<Integer, String> inputMap;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator() ;
        schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);
    }

    @Test
    public void testTrue() throws Exception {
        boolean expected = true;
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        boolean actual = schema.isValid(human1);
        assertEquals(expected, actual);
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        actual = schema.isValid(human2);
        assertEquals(expected, actual);
        actual = schema.isValid(null);
        assertEquals(expected, actual);
    }

    @Test
    public void testFalse() throws Exception {
        boolean expected = false;
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        boolean actual = schema.isValid(human3);
        assertEquals(expected, actual);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        actual = schema.isValid(human3);
        assertEquals(expected, actual);

    }

}
