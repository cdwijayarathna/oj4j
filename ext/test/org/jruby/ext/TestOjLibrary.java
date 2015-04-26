package org.jruby.ext;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestOjLibrary{

    OjLibrary oj;
    public TestOjLibrary(){
        oj = new OjLibrary();
    }

    @Test
    public void test_compat_load() {
        String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
        
    }

    @Test
    public void test_compat_load1() {
        int a = 1;
        assertEquals(1,a);
        
    }

    

}
