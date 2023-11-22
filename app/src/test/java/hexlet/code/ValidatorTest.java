package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public final class ValidatorTest {

    private Validator v;
    private MapSchema mapSchema;
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private boolean expectedTrue = true;
    private boolean expectedFalse = false;

    @BeforeEach
    public void beforeEach() throws Exception {
        v = new Validator();
        mapSchema = v.map();
        stringSchema = v.string();
        numberSchema = v.number();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        mapSchema.shape(schemas);
    }

    @Test
    public void testStringValidator() throws Exception {
        boolean actual = stringSchema.isValid("");
        assertEquals(expectedTrue, actual);

        actual = stringSchema.isValid(null);
        assertEquals(expectedTrue, actual);

        stringSchema.required();
        actual = stringSchema.isValid(null);
        assertEquals(expectedFalse, actual);

        actual = stringSchema.isValid("");
        assertEquals(expectedFalse, actual);

        actual = stringSchema.isValid(5);
        assertEquals(expectedFalse, actual);

        actual = stringSchema.contains("wh").isValid("what does the fox say");
        assertEquals(expectedTrue, actual);

        actual = stringSchema.contains("what").isValid("what does the fox say");
        assertEquals(expectedTrue, actual);

        actual = stringSchema.contains("what").minLength(10).isValid("what does the fox say");
        assertEquals(expectedTrue, actual);

        actual = stringSchema.contains("what").minLength(50).isValid("what does the fox say");
        assertEquals(expectedFalse, actual);

        actual = stringSchema.contains("whatthe").isValid("what does the fox say");
        assertEquals(expectedFalse, actual);
    }

    @Test
    public void testNumberValidator() throws Exception {
        boolean actual = numberSchema.isValid(null);
        assertEquals(expectedTrue, actual);

        actual = numberSchema.positive().isValid(null);
        assertEquals(expectedTrue, actual);

        numberSchema.required();
        actual = numberSchema.isValid(null);
        assertEquals(expectedFalse, actual);

        actual = numberSchema.isValid("5");
        assertEquals(expectedFalse, actual);

        actual = numberSchema.isValid(5);
        assertEquals(expectedTrue, actual);

        actual = numberSchema.isValid(-5);
        assertEquals(expectedFalse, actual);

        actual = numberSchema.range(10, 20).isValid(15);
        assertEquals(expectedTrue, actual);

        actual = numberSchema.isValid(5);
        assertEquals(expectedFalse, actual);
    }

    @Test
    public void testMapValidator() throws Exception {

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", null);
        boolean actual = mapSchema.isValid(human1);
        assertEquals(expectedTrue, actual);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        actual = mapSchema.isValid(human2);
        assertEquals(expectedTrue, actual);

        actual = mapSchema.isValid(null);
        assertEquals(expectedTrue, actual);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        actual = mapSchema.isValid(human3);
        assertEquals(expectedFalse, actual);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        actual = mapSchema.isValid(human4);
        assertEquals(expectedFalse, actual);

        Map<String, Object> human5 = new HashMap<>();
        actual = mapSchema.isValid(5);
        assertEquals(expectedFalse, actual);
    }
}
