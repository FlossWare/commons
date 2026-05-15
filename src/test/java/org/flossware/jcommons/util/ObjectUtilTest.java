package org.flossware.jcommons.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ObjectUtilTest {

    @Test
    void testGetPackage_withValidObject() {
        String obj = "test";
        String packageName = ObjectUtil.getPackage(obj);
        assertEquals("java.lang", packageName);
    }

    @Test
    void testGetPackage_withNullObject() {
        assertThrows(NullPointerException.class, () ->
            ObjectUtil.getPackage(null));
    }

    @Test
    void testGetPackage_withCustomClass() {
        ObjectUtilTest obj = new ObjectUtilTest();
        String packageName = ObjectUtil.getPackage(obj);
        assertEquals("org.flossware.jcommons.util", packageName);
    }

    @Test
    void testDefaultErrorMessage() {
        assertEquals("Invalid value", ObjectUtil.DEFAULT_ERROR_MSG);
    }
}
