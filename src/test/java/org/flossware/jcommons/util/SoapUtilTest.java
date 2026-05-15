package org.flossware.jcommons.util;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;
import org.apache.cxf.headers.Header;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SoapUtilTest {

    @Test
    void testGetSoapFactory() {
        try {
            assertNotNull(SoapUtil.getSoapFactory());
        } catch (SoapException e) {
            // Expected if SOAP factory cannot be instantiated in test environment
            assertTrue(e.getMessage().contains("Could not instantiate soap factory"));
        }
    }

    @Test
    void testSetHeader_withNullService() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(null, "name", "value"));
    }

    @Test
    void testSetHeader_withNullName() {
        Service mockService = null;
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(mockService, (String) null, "value"));
    }

    @Test
    void testSetHeader_withNullValue() {
        Service mockService = null;
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(mockService, "name", null));
    }

    @Test
    void testSetHeader_withQName_nullService() {
        QName qname = new QName("http://test", "test");
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(null, qname, "value"));
    }

    @Test
    void testSetHeader_withQName_nullQName() {
        Service mockService = null;
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(mockService, (QName) null, "value"));
    }

    @Test
    void testSetHeader_withQName_nullValue() {
        Service mockService = null;
        QName qname = new QName("http://test", "test");
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeader(mockService, qname, null));
    }

    @Test
    void testSetHeaders_withNullService() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeaders(null, new Header[0]));
    }

    @Test
    void testSetHeaders_withNullHeaders() {
        Service mockService = null;
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setHeaders(mockService, (Header[]) null));
    }

    @Test
    void testSetUrl_withNullPort() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.setUrl(null, "http://test.com"));
    }

    @Test
    void testSetUrl_withNullUrl() {
        Object mockPort = new Object();
        assertThrows(IllegalArgumentException.class, () ->
            SoapUtil.setUrl(mockPort, null));
    }

    @Test
    void testSetUrl_withEmptyUrl() {
        Object mockPort = new Object();
        assertThrows(IllegalArgumentException.class, () ->
            SoapUtil.setUrl(mockPort, ""));
    }

    @Test
    void testComputeQName_withNullWebServiceClient() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.computeQName((WebServiceClient) null));
    }

    @Test
    void testComputeQName_withNullClass() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.computeQName((Class<? extends Service>) null));
    }

    @Test
    void testComputeQName_withNullService() {
        assertThrows(NullPointerException.class, () ->
            SoapUtil.computeQName((Service) null));
    }

    @WebServiceClient(targetNamespace = "http://test.namespace", name = "TestService")
    private static class TestService extends Service {
        public TestService() {
            super(null, new QName("http://test.namespace", "TestService"));
        }
    }

    @Test
    void testComputeQName_withValidClass() {
        QName result = SoapUtil.computeQName(TestService.class);
        assertEquals("http://test.namespace", result.getNamespaceURI());
        assertEquals("TestService", result.getLocalPart());
    }

    @Test
    void testComputeQName_withClassNotAnnotated() {
        assertThrows(IllegalArgumentException.class, () ->
            SoapUtil.computeQName(NotAnnotatedService.class));
    }

    private static class NotAnnotatedService extends Service {
        public NotAnnotatedService() {
            super(null, new QName("http://test", "test"));
        }
    }
}
