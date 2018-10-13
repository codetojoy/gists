
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

// see https://github.com/powermock/powermock/wiki/Mockito

@RunWith(PowerMockRunner.class)
@PrepareForTest(FooDAO.class)
public class FooServiceTestCase {
    private FooService fooService = new FooService();

    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testGetFoo_evenId() {
        int id = 5150;
        Foo foo = new Foo();

        PowerMockito.mockStatic(FooDAO.class);
        Mockito.when(FooDAO.getFoo(id)).thenReturn(foo);

        // test
        Foo result = fooService.getFoo(id);        

        assertTrue(result instanceof Foo);
        assertFalse(result instanceof Bar);
        assertEquals((int) id, (int) result.id);
    }

    @Test
    public void testGetFoo_oddId() {
        int id = 5151;
        Foo foo = new Foo();

        PowerMockito.mockStatic(FooDAO.class);
        Mockito.when(FooDAO.getFoo(id)).thenReturn(foo);

        // test
        Foo result = fooService.getFoo(id);        

        assertTrue(result instanceof Bar);
        Bar bar = (Bar) result;
        assertEquals((int) id, (int) bar.id);
        assertNotNull(bar.timestamp);
    }
}

