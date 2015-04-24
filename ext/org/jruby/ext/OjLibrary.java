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
    }
    
    private static final ObjectAllocator DOC_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new Doc(runtime, klazz);
        }
    };

    @JRubyClass(name="Doc", parent="Object")
    public static class Doc extends RubyObject {

	Ruby runtime;
        public Doc(Ruby runtime, RubyClass klass) {
            super(runtime, klass);
	    this.runtime = runtime;
        }

    }

    
}
