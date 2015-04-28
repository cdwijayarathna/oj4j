package org.jruby.ext;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

@JRubyClass(name="StreamWriter", parent="Object")
public class StreamWriter extends RubyObject {
	
	Ruby runtime;
    public StreamWriter(Ruby runtime, RubyClass klass) {
        super(runtime, klass);
        this.runtime = runtime;
    }
    
    @JRubyMethod(name = "new", meta=true,required = 1, optional = 1)
    public static IRubyObject neww(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("Stream_Writer new");
        IRubyObject io = args[0];
        return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject pop(ThreadContext context) {
    	System.out.println("Stream Writer pop");
        return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject pop_all(ThreadContext context) {
    	System.out.println("Stream Writer pop_all");
        return context.nil;
    }
    
    @JRubyMethod(required = 0, optional = 1)
    public IRubyObject push_array(ThreadContext context, IRubyObject[] args) {
    	System.out.println("Stream Writer push_array");
    	RubyString key = runtime.newString("");
        if (args.length > 0) {
            key = args[0].convertToString();
        }
        return context.nil;
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public IRubyObject push_value(ThreadContext context, IRubyObject[] args) {
    	System.out.println("Stream Writer push_value");
    	RubyString key = runtime.newString("");
        if (args.length > 1) {
            key = args[1].convertToString();
        }
        return context.nil;
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public IRubyObject push_json(ThreadContext context, IRubyObject[] args) {
    	System.out.println("Stream Writer push_json");
    	RubyString key = runtime.newString("");
        if (args.length > 1) {
            key = args[1].convertToString();
        }
        return context.nil;
    }
    
    @JRubyMethod
    public IRubyObject push_key(ThreadContext context, IRubyObject key) {
    	System.out.println("Stream Writer push_key");
        return context.nil;
    }
    
    @JRubyMethod(required = 0, optional = 1)
    public IRubyObject push_object(ThreadContext context, IRubyObject[] args) {
    	System.out.println("Stream Writer push_object");
    	RubyString key = runtime.newString("");
        if (args.length > 0) {
            key = args[0].convertToString();
        }
        return context.nil;
    }
    
}