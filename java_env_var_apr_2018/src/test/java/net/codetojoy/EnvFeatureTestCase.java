
package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class EnvFeatureTestCase {
    private EnvFeature envFeature = new EnvFeature();
    private String feature = EnvFeature.ACME_CLIENT_ABC;

    private EnvSource mockEnvSource = mock(EnvSource.class);

    private static final String BOGUS = "BOGUS";

    @Test
    public void testIsOn_PresentAndBogus() {
        when(mockEnvSource.getenv(feature)).thenReturn(BOGUS);
        envFeature.setEnvSource(mockEnvSource);
        
        // test
        boolean result = envFeature.isOn(feature);

        assertFalse(result);
    }

    @Test
    public void testIsOn_PresentAndTrue_LowerCase() {
        when(mockEnvSource.getenv(feature)).thenReturn(EnvFeature.ON_VALUE.toLowerCase());
        envFeature.setEnvSource(mockEnvSource);
        
        // test
        boolean result = envFeature.isOn(feature);

        assertTrue(result);
    }

    @Test
    public void testIsOn_PresentAndTrue() {
        when(mockEnvSource.getenv(feature)).thenReturn(EnvFeature.ON_VALUE);
        envFeature.setEnvSource(mockEnvSource);
        
        // test
        boolean result = envFeature.isOn(feature);

        assertTrue(result);
    }

    @Test
    public void testIsOn_NotPresent() {
        when(mockEnvSource.getenv(feature)).thenReturn(null);
        envFeature.setEnvSource(mockEnvSource);
        
        // test
        boolean result = envFeature.isOn(feature);

        assertFalse(result);
    }
}
