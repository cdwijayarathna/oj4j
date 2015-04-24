package org.jruby.ext;

import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyModule;
import org.jruby.common.RubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.*;
import java.util.concurrent.Callable;
import org.jruby.javasupport.JavaEmbedUtils;

import static org.jruby.runtime.Helpers.invokedynamic;
import static org.jruby.runtime.invokedynamic.MethodNames.OP_CMP;


@JRubyModule(name="Oj")
public class Oj {
    
    @JRubyMethod(module = true)
    public static IRubyObject compat_load(IRubyObject self, IRubyObject json,IRubyObject options) {
        
        return Null;
    }
}
