package org.jruby.ext;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestOj{

    Oj oj;
    public TestOj(){
        oj = new Oj();
    }

    @Test
    public void test_compat_load() {
        String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
        
    }

    

}
