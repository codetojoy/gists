
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

public class FooServiceTestCase {
    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testGetFoo_argCaptureExample() {
        int id = 5150;
        FooDAO fooDAO = mock(FooDAO.class);

        FooService fooService = new FooService();
        fooService.setFooDAO(fooDAO);

        // test
        Foo result = fooService.getFoo(id);        

        ArgumentCaptor<Integer> intArg = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Bar> barArg = ArgumentCaptor.forClass(Bar.class);
        verify(fooDAO).createFoo(intArg.capture(), barArg.capture());

        assertEquals((int) id, (int) intArg.getValue());
        assertEquals(id, (int) barArg.getValue().getId());
        assertEquals("bar::" + id, barArg.getValue().getName());
    }
}
