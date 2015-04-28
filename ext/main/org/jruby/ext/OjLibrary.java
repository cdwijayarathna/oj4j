package org.jruby.ext;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyException;
import org.jruby.RubyKernel;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyClass;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.Library;
import org.jruby.javasupport.JavaEmbedUtils;

public class OjLibrary implements Library {
    public void load(Ruby runtime, boolean wrap) throws IOException {
        RubyKernel.require(runtime.getKernel(), runtime.newString("oj"), Block.NULL_BLOCK);
        RubyModule ojModule = runtime.getOrCreateModule("Oj");
        ojModule.defineAnnotatedMethods(Oj.class);

        RubyClass docClass = runtime.defineClassUnder("Doc", runtime.getObject(), DOC_ALLOCATOR, ojModule);
        docClass.defineAnnotatedMethods(Doc.class);
        
        RubyClass streamWriterClass = runtime.defineClassUnder("StreamWriter", runtime.getObject(), STREAM_WRITER_ALLOCATOR, ojModule);
        streamWriterClass.defineAnnotatedMethods(StreamWriter.class);
        
        RubyClass stringWriterClass = runtime.defineClassUnder("StringWriter", runtime.getObject(), STRING_WRITER_ALLOCATOR, ojModule);
        stringWriterClass.defineAnnotatedMethods(StringWriter.class);
    }
    
    private static final ObjectAllocator DOC_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new Doc(runtime, klazz);
        }
    };
    
    private static final ObjectAllocator STREAM_WRITER_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new StreamWriter(runtime, klazz);
        }
    };
    
    private static final ObjectAllocator STRING_WRITER_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new StringWriter(runtime, klazz);
        }
    };

    @JRubyClass(name="Doc", parent="Object")
    public static class Doc extends RubyObject {
    	Ruby runtime;
        public Doc(Ruby runtime, RubyClass klass) {
            super(runtime, klass);
            this.runtime = runtime;
        }
        
        @JRubyMethod(name = { "open", "parse" }, meta=true)
        public static IRubyObject open(ThreadContext context, IRubyObject self, IRubyObject json, Block block) {
            System.out.println("Doc.Open");
            if (!block.isGiven()) return context.nil;
            block.yield(context, json);
            return context.nil;
        }
        
        @JRubyMethod(meta=true)
        public static IRubyObject open_file(ThreadContext context, IRubyObject self, IRubyObject filename, Block block) {
            System.out.println("Doc.Open_file");
            if (!block.isGiven()) return context.nil;
            block.yield(context, true);///pass json from file
            return context.nil;
        }
        
        @JRubyMethod
        public IRubyObject close(ThreadContext context) {
            System.out.println("Doc Close");
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject dump(ThreadContext context, IRubyObject[] args) {
        	System.out.println("Doc Dump");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject each_child(ThreadContext context, IRubyObject[] args, Block block) {
        	System.out.println("Doc each_child");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            if (!block.isGiven()) return context.nil;
            block.yield(context, true);
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject each_leaf(ThreadContext context, IRubyObject[] args, Block block) {
        	System.out.println("Doc each_leaf");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            if (!block.isGiven()) return context.nil;
            block.yield(context, true);
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject each_value(ThreadContext context, IRubyObject[] args, Block block) {
        	System.out.println("Doc each_value");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            if (!block.isGiven()) return context.nil;
            block.yield(context, true);
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject fetch(ThreadContext context, IRubyObject[] args) {
        	System.out.println("Doc Fetch");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            return context.nil;
        }
        
        @JRubyMethod
        public IRubyObject home(ThreadContext context) {
        	System.out.println("Doc Home");
            return context.nil;
        }
        
        @JRubyMethod
        public IRubyObject local_key(ThreadContext context) {
        	System.out.println("Doc local_key");
            return context.nil;
        }
        
        @JRubyMethod
        public IRubyObject move(ThreadContext context, IRubyObject path) {
        	System.out.println("Doc move");
            return context.nil;
        }
        
        @JRubyMethod
        public IRubyObject size(ThreadContext context) {
        	System.out.println("Doc size");
            return context.nil;
        }
        
        @JRubyMethod(required = 0, optional = 1)
        public IRubyObject type(ThreadContext context, IRubyObject[] args) {
        	System.out.println("Doc type");
            RubyString path = runtime.newString("");
            if (args.length > 0) {
                path = args[0].convertToString();
            }
            return context.nil;
        }
        
        @JRubyMethod(name = "where?")
        public IRubyObject where(ThreadContext context) {
        	System.out.println("Doc where");
            return context.nil;
        }

    }

    
}
