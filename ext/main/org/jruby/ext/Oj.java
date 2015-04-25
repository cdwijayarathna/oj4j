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
    public static IRubyObject compat_load(IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Compat Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject compat_load(IRubyObject self, IRubyObject json) {
	System.out.println("Compat Load without options");
        return null;
    }


    @JRubyMethod(module = true)
    public static IRubyObject default_options(IRubyObject self) {
	System.out.println("Default Options 1");
        return null;
    }

    @JRubyMethod(name = "default_options=",module = true)
    public static IRubyObject default_options(IRubyObject self, IRubyObject opts) {
	System.out.println("Default Options 2");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject dump(IRubyObject self, IRubyObject obj, IRubyObject options) {
	System.out.println("Dump with options");
        return null;
    }
   
    @JRubyMethod(module = true)
    public static IRubyObject dump(IRubyObject self, IRubyObject obj) {
	System.out.println("Dump without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject load(IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject load(IRubyObject self, IRubyObject json) {
	System.out.println("Load without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject load_file(IRubyObject self, IRubyObject path, IRubyObject options) {
	System.out.println("Load File with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject load_file(IRubyObject self, IRubyObject path) {
	System.out.println("Load File without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject mimic_JSON(IRubyObject self) {
	System.out.println("mimic_JSON");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject object_load(IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Object Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject object_load(IRubyObject self, IRubyObject json) {
	System.out.println("Object Load without options");
        return null;
    }

    ////REGISTER ODD ARGUEMENTS UNCLEAR , can't have more than 3 arguements ##############

    @JRubyMethod(module = true)
    public static IRubyObject register_odd(IRubyObject self, IRubyObject clas, IRubyObject create_object, IRubyObject create_method) {
	System.out.println("Register Odd");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject safe_load(IRubyObject self, IRubyObject json) {
	System.out.println("Safe Load");
        return null;
    }

    //Parameters not very clear in documentation
    @JRubyMethod(module = true)
    public static IRubyObject saj_parse(IRubyObject self) {
	System.out.println("Saj Parse");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject sc_parse(IRubyObject self) {
	System.out.println("Sc Parse");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject strict_load(IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Strict Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject strict_load(IRubyObject self, IRubyObject json) {
	System.out.println("Strict Load without options");
        return null;
    }

   @JRubyMethod(module = true)
    public static IRubyObject to_file(IRubyObject self, IRubyObject file_path, IRubyObject obj, IRubyObject options) {
	System.out.println("To File with options");
        return null;
    }
    

    @JRubyMethod(module = true)
    public static IRubyObject to_file(IRubyObject self, IRubyObject file_path, IRubyObject obj) {
	System.out.println("To File without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject to_stream(IRubyObject self, IRubyObject io, IRubyObject obj, IRubyObject options) {
	System.out.println("To stream with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject to_stream(IRubyObject self, IRubyObject io, IRubyObject obj) {
	System.out.println("To stream without options");
        return null;
    }


}
