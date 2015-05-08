package org.jruby.ext;

import org.jruby.RubyHash;
import org.jruby.RubySymbol;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyModule;
import org.jruby.common.RubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import java.util.concurrent.Callable;
import org.jruby.javasupport.JavaEmbedUtils;

import static org.jruby.runtime.Helpers.invokedynamic;
import static org.jruby.runtime.invokedynamic.MethodNames.OP_CMP;

@JRubyModule(name="Oj")
public class Oj {
    
    @JRubyMethod(module = true)
    public static IRubyObject compat_load(ThreadContext context, IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Compat Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject compat_load(ThreadContext context, IRubyObject self, IRubyObject json) {
	System.out.println("Compat Load without options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject default_options(ThreadContext context, IRubyObject self) {
    	RubyHash opts=RubyHash.newHash(context.runtime);
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "indent"),context.runtime.newFixnum(OjLibrary.parser.getIndent()));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "second_precision"),context.runtime.newFixnum(OjLibrary.parser.getSec_prec()));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "circular"),OjLibrary.parser.getCircular()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getCircular()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "class_cache"),OjLibrary.parser.getClass_cache()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getClass_cache()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "auto_define"),OjLibrary.parser.getAuto_define()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getAuto_define()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "symbol_keys"),OjLibrary.parser.getSym_key()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getSym_key()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "bigdecimal_as_decimal"),OjLibrary.parser.getBigdec_as_num()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getBigdec_as_num()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "use_to_json"),OjLibrary.parser.getTo_json()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getTo_json()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "nilnil"),OjLibrary.parser.getNilnil()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getNilnil()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "allow_gc"),OjLibrary.parser.getAllow_gc()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getAllow_gc()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "quirks_mode"),OjLibrary.parser.getQuirks_mode()=='y'?context.runtime.newBoolean(true):(OjLibrary.parser.getQuirks_mode()=='n'?context.runtime.newBoolean(false):context.nil));
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "float_precision"),context.runtime.newFixnum(OjLibrary.parser.getFloat_prec()));
    	char mode = OjLibrary.parser.getMode();
    	switch (mode){
    		case 's':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "mode"),RubySymbol.newSymbol(context.runtime, "strict"));
    			break;
    		case 'c':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "mode"),RubySymbol.newSymbol(context.runtime, "compat"));
    			break;
    		case 'n':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "mode"),RubySymbol.newSymbol(context.runtime, "null"));
    			break;
    		case 'o':
    		default:	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "mode"),RubySymbol.newSymbol(context.runtime, "object"));
    			break;
    	}
    	char escapeMode = OjLibrary.parser.getEscape_mode();
    	switch (escapeMode){
    		case 'n':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "escape_mode"),RubySymbol.newSymbol(context.runtime, "newline"));
    			break;
    		case 'j':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "escape_mode"),RubySymbol.newSymbol(context.runtime, "json"));
    			break;
    		case 'x':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "escape_mode"),RubySymbol.newSymbol(context.runtime, "xss_safe"));
    			break;
    		case 'a':
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "escape_mode"),RubySymbol.newSymbol(context.runtime, "ascii"));
    			break;
    		default:	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "escape_mode"),RubySymbol.newSymbol(context.runtime, "json"));
    			break;
    	}
    	char timeFormat = OjLibrary.parser.getTime_format();
    	switch (timeFormat){
    		case 'x':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "time_format"),RubySymbol.newSymbol(context.runtime, "xmlschema"));
    			break;
    		case 'r':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "time_format"),RubySymbol.newSymbol(context.runtime, "ruby"));
    			break;
    		case 'z':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "time_format"),RubySymbol.newSymbol(context.runtime, "unix_zone"));
    			break;
    		case 'u':
    		default:	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "time_format"),RubySymbol.newSymbol(context.runtime, "unix"));
    			break;
    	}
    	char bigdecLoad = OjLibrary.parser.getBigdec_load();
    	switch (timeFormat){
    		case 'b':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "bigdecimal_load"),RubySymbol.newSymbol(context.runtime, "bigdecimal"));
    			break;
    		case 'f':	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "bigdecimal_load"),RubySymbol.newSymbol(context.runtime, "float"));
    			break;
    		case 'a':
    		default:	
    			opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "bigdecimal_load"),RubySymbol.newSymbol(context.runtime, "auto"));
    			break;
    	}
    	opts.op_aset(context,RubySymbol.newSymbol(context.runtime, "create_id"),OjLibrary.parser.getCreate_id()== null ?context.nil:context.runtime.newString(OjLibrary.parser.getCreate_id()));
    	return opts;
    }

    @JRubyMethod(name = "default_options=",module = true)
    public static IRubyObject default_options(ThreadContext context, IRubyObject self, IRubyObject opts) {
	System.out.println("Default Options 2");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject dump(ThreadContext context, IRubyObject self, IRubyObject obj, IRubyObject options) {
	System.out.println("Dump with options");
        return null;
    }
   
    @JRubyMethod(module = true)
    public static IRubyObject dump(ThreadContext context, IRubyObject self, IRubyObject obj) {
	System.out.println("Dump without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject load(ThreadContext context, IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject load(ThreadContext context, IRubyObject self, IRubyObject json) {
	System.out.println("Load without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject load_file(ThreadContext context, IRubyObject self, IRubyObject path, IRubyObject options) {
	System.out.println("Load File with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject load_file(ThreadContext context, IRubyObject self, IRubyObject path) {
	System.out.println("Load File without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject mimic_JSON(ThreadContext context, IRubyObject self) {
	System.out.println("mimic_JSON");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject object_load(ThreadContext context, IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Object Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject object_load(ThreadContext context, IRubyObject self, IRubyObject json) {
	System.out.println("Object Load without options");
        return null;
    }

    ////REGISTER ODD ARGUEMENTS UNCLEAR , can't have more than 3 arguements ##############

    @JRubyMethod(module = true)
    public static IRubyObject register_odd(ThreadContext context, IRubyObject self, IRubyObject clas, IRubyObject create_object, IRubyObject create_method) {
	System.out.println("Register Odd");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject safe_load(ThreadContext context, IRubyObject self, IRubyObject json) {
	System.out.println("Safe Load");
        return null;
    }

    //Parameters not very clear in documentation
    @JRubyMethod(module = true)
    public static IRubyObject saj_parse(ThreadContext context, IRubyObject self, IRubyObject handler, IRubyObject io) {
	System.out.println("Saj Parse");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject sc_parse(ThreadContext context, IRubyObject self, IRubyObject handler, IRubyObject io) {
	System.out.println("Sc Parse");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject strict_load(ThreadContext context, IRubyObject self, IRubyObject json, IRubyObject options) {
	System.out.println("Strict Load with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject strict_load(ThreadContext context, IRubyObject self, IRubyObject json) {
	System.out.println("Strict Load without options");
        return null;
    }

   @JRubyMethod(module = true)
    public static IRubyObject to_file(ThreadContext context, IRubyObject self, IRubyObject file_path, IRubyObject obj, IRubyObject options) {
	System.out.println("To File with options");
        return null;
    }
    

    @JRubyMethod(module = true)
    public static IRubyObject to_file(ThreadContext context, IRubyObject self, IRubyObject file_path, IRubyObject obj) {
	System.out.println("To File without options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject to_stream(ThreadContext context, IRubyObject self, IRubyObject io, IRubyObject obj, IRubyObject options) {
	System.out.println("To stream with options");
        return null;
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject to_stream(ThreadContext context, IRubyObject self, IRubyObject io, IRubyObject obj) {
	System.out.println("To stream without options");
        return null;
    }


}
