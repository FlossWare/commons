package org.flossware.commons;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class AbstractBaseTest {

    private static class TestAbstractBase extends AbstractBase {
        public void testLog() {
            log(Level.INFO, "Test message");
        }

        public void testLogWithArgs() {
            log(Level.INFO, "Test {0} with {1}", "message", "args");
        }

        public void testLogWithThrowable() {
            log(Level.WARNING, new RuntimeException("test"), "Exception occurred");
        }

        public void testLogWithThrowableAndArgs() {
            log(Level.WARNING, new RuntimeException("test"), "Exception {0}", "occurred");
        }

        public String testLogAndReturn() {
            return logAndReturn(Level.INFO, "Returning value", "test");
        }

        public String testLogAndReturnWithArgs() {
            return logAndReturn(Level.INFO, "Values", "first", "second");
        }

        public String testLogAndReturnByIndex() {
            return logAndReturnByIndex(Level.INFO, "Values", 1, "first", "second", "third");
        }
    }

    @Test
    void testAbstractBase_hasLogger() {
        TestAbstractBase instance = new TestAbstractBase();
        assertNotNull(instance.getLogger());
    }

    @Test
    void testLog_simple() {
        TestAbstractBase instance = new TestAbstractBase();
        assertDoesNotThrow(instance::testLog);
    }

    @Test
    void testLog_withArgs() {
        TestAbstractBase instance = new TestAbstractBase();
        assertDoesNotThrow(instance::testLogWithArgs);
    }

    @Test
    void testLog_withThrowable() {
        TestAbstractBase instance = new TestAbstractBase();
        assertDoesNotThrow(instance::testLogWithThrowable);
    }

    @Test
    void testLog_withThrowableAndArgs() {
        TestAbstractBase instance = new TestAbstractBase();
        assertDoesNotThrow(instance::testLogWithThrowableAndArgs);
    }

    @Test
    void testLogAndReturn() {
        TestAbstractBase instance = new TestAbstractBase();
        String result = instance.testLogAndReturn();
        assertEquals("test", result);
    }

    @Test
    void testLogAndReturn_withArgs() {
        TestAbstractBase instance = new TestAbstractBase();
        String result = instance.testLogAndReturnWithArgs();
        assertEquals("first", result);
    }

    @Test
    void testLogAndReturnByIndex() {
        TestAbstractBase instance = new TestAbstractBase();
        String result = instance.testLogAndReturnByIndex();
        assertEquals("second", result);
    }

    @Test
    void testLogger_hasCorrectName() {
        TestAbstractBase instance = new TestAbstractBase();
        assertTrue(instance.getLogger().getName().contains("TestAbstractBase"));
    }
}
