package org.jruby.ext;

import org.jruby.RubyBoolean;
import org.jruby.RubyBoolean.False;
import org.jruby.RubyBoolean.True;
import org.jruby.RubyFixnum;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyModule;
import org.jruby.common.RubyWarnings;
import org.jruby.exceptions.RaiseException;
import org.jruby.ext.beans.*;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.TypeConverter;

import java.util.concurrent.Callable;

import org.jruby.javasupport.JavaEmbedUtils;

import static org.jruby.runtime.Helpers.invokedynamic;
import static org.jruby.runtime.invokedynamic.MethodNames.OP_CMP;

@JRubyModule(name = "Oj")
public class Oj {

    @JRubyMethod(module = true)
    public static IRubyObject default_options(ThreadContext context, IRubyObject self) {
        RubyHash opts = RubyHash.newHash(context.runtime);
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "indent"), context.runtime.newFixnum(OjLibrary
                .parser.getIndent()));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "second_precision"), context.runtime.newFixnum
				(OjLibrary.parser.getSec_prec()));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "circular"), OjLibrary.parser.getCircular() ==
				'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getCircular() == 'n' ? context.runtime
				.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "class_cache"), OjLibrary.parser.getClass_cache()
				== 'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getClass_cache() == 'n' ? context
				.runtime.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "auto_define"), OjLibrary.parser.getAuto_define()
				== 'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getAuto_define() == 'n' ? context
				.runtime.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "symbol_keys"), OjLibrary.parser.getSym_key() ==
				'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getSym_key() == 'n' ? context.runtime
				.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_as_decimal"), OjLibrary.parser
				.getBigdec_as_num() == 'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getBigdec_as_num()
				== 'n' ? context.runtime.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "use_to_json"), OjLibrary.parser.getTo_json() ==
				'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getTo_json() == 'n' ? context.runtime
				.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "nilnil"), OjLibrary.parser.getNilnil() == 'y' ?
				context.runtime.newBoolean(true) : (OjLibrary.parser.getNilnil() == 'n' ? context.runtime.newBoolean
				(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "allow_gc"), OjLibrary.parser.getAllow_gc() ==
				'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getAllow_gc() == 'n' ? context.runtime
				.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "quirks_mode"), OjLibrary.parser.getQuirks_mode()
				== 'y' ? context.runtime.newBoolean(true) : (OjLibrary.parser.getQuirks_mode() == 'n' ? context
				.runtime.newBoolean(false) : context.nil));
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "float_precision"), context.runtime.newFixnum
				(OjLibrary.parser.getFloat_prec()));
        char mode = OjLibrary.parser.getMode();
        switch (mode) {
            case 's':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "mode"), RubySymbol.newSymbol(context
						.runtime, "strict"));
                break;
            case 'c':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "mode"), RubySymbol.newSymbol(context
						.runtime, "compat"));
                break;
            case 'n':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "mode"), RubySymbol.newSymbol(context
						.runtime, "null"));
                break;
            case 'o':
            default:
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "mode"), RubySymbol.newSymbol(context
						.runtime, "object"));
                break;
        }
        char escapeMode = OjLibrary.parser.getEscape_mode();
        switch (escapeMode) {
            case 'n':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "escape_mode"), RubySymbol.newSymbol
						(context.runtime, "newline"));
                break;
            case 'j':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "escape_mode"), RubySymbol.newSymbol
						(context.runtime, "json"));
                break;
            case 'x':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "escape_mode"), RubySymbol.newSymbol
						(context.runtime, "xss_safe"));
                break;
            case 'a':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "escape_mode"), RubySymbol.newSymbol
						(context.runtime, "ascii"));
                break;
            default:
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "escape_mode"), RubySymbol.newSymbol
						(context.runtime, "json"));
                break;
        }
        char timeFormat = OjLibrary.parser.getTime_format();
        switch (timeFormat) {
            case 'x':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "time_format"), RubySymbol.newSymbol
						(context.runtime, "xmlschema"));
                break;
            case 'r':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "time_format"), RubySymbol.newSymbol
						(context.runtime, "ruby"));
                break;
            case 'z':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "time_format"), RubySymbol.newSymbol
						(context.runtime, "unix_zone"));
                break;
            case 'u':
            default:
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "time_format"), RubySymbol.newSymbol
						(context.runtime, "unix"));
                break;
        }
        char bigdecLoad = OjLibrary.parser.getBigdec_load();
        switch (timeFormat) {
            case 'b':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_load"), RubySymbol.newSymbol
						(context.runtime, "bigdecimal"));
                break;
            case 'f':
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_load"), RubySymbol.newSymbol
						(context.runtime, "float"));
                break;
            case 'a':
            default:
                opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_load"), RubySymbol.newSymbol
						(context.runtime, "auto"));
                break;
        }
        opts.op_aset(context, RubySymbol.newSymbol(context.runtime, "create_id"), OjLibrary.parser.getCreate_id() ==
				null ? context.nil : context.runtime.newString(OjLibrary.parser.getCreate_id()));
        return opts;
    }

    @JRubyMethod(name = "default_options=", module = true)
    public static IRubyObject default_options(ThreadContext context, IRubyObject self, IRubyObject opts) {

        TypeConverter.checkType(context, opts, context.runtime.getHash());
        RubyHash optHash = (RubyHash) opts;

        IRubyObject val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "indent"));
        if (!val.equals(context.nil)) {
            TypeConverter.checkType(context, val, context.runtime.getFixnum());
            int indent = ((RubyFixnum) val).getIntValue();
            OjLibrary.parser.setIndent(indent);
        }
        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "float_precision"));
        if (!val.equals(context.nil)) {
            TypeConverter.checkType(context, val, context.runtime.getFixnum());
            int floatPrecision = ((RubyFixnum) val).getIntValue();
            if (floatPrecision < 0) {
                floatPrecision = 0;
                OjLibrary.parser.setFloat_fmt("\0");
            } else {
                if (floatPrecision > 20) {
                    floatPrecision = 20;
                }
                OjLibrary.parser.setFloat_fmt("%%0." + floatPrecision + "g");
            }
            OjLibrary.parser.setFloat_prec(floatPrecision);
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "second_precision"));
        if (!val.equals(context.nil)) {
            TypeConverter.checkType(context, val, context.runtime.getFixnum());
            int secondPrecision = ((RubyFixnum) val).getIntValue();
            if (secondPrecision < 0) {
                secondPrecision = 0;
            } else if (secondPrecision > 9) {
                secondPrecision = 9;
            }
            OjLibrary.parser.setSec_prec(secondPrecision);
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "mode"));
        if (val.equals(context.nil)) {
            //ignore
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "object"))) {
            OjLibrary.parser.setMode('o');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "strict"))) {
            OjLibrary.parser.setMode('s');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "compat"))) {
            OjLibrary.parser.setMode('c');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "null"))) {
            OjLibrary.parser.setMode('n');
        } else {
            throw context.runtime.newArgumentError(":mode must be :object, :strict, :compat, or :null.");
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "time_format"));
        if (val.equals(context.nil)) {
            //ignore
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "unix"))) {
            OjLibrary.parser.setTime_format('u');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "unix_zone"))) {
            OjLibrary.parser.setTime_format('z');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "xml_schema"))) {
            OjLibrary.parser.setTime_format('x');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "ruby"))) {
            OjLibrary.parser.setTime_format('r');
        } else {
            throw context.runtime.newArgumentError(":time_format must be :unix, :unix_zone, :xmlschema, or :ruby.");
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "escape_mode"));
        if (val.equals(context.nil)) {
            //ignore
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "newline"))) {
            OjLibrary.parser.setEscape_mode('n');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "json"))) {
            OjLibrary.parser.setEscape_mode('j');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "xss_safe"))) {
            OjLibrary.parser.setEscape_mode('x');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "ascii"))) {
            OjLibrary.parser.setEscape_mode('a');
        } else {
            throw context.runtime.newArgumentError(":escape_mode must be :newline, :json, :xss_safe, or :ascii.");
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_load"));
        if (val.equals(context.nil)) {
            //ignore
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "bigdecimal")) || val.equals(context.runtime
				.newBoolean(true))) {
            OjLibrary.parser.setBigdec_load('b');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "float"))) {
            OjLibrary.parser.setBigdec_load('f');
        } else if (val.equals(RubySymbol.newSymbol(context.runtime, "auto")) || val.equals(context.runtime.newBoolean
				(false))) {
            OjLibrary.parser.setBigdec_load('a');
        } else {
            throw context.runtime.newArgumentError(":bigdecimal_load must be :bigdecimal, :float, or :auto.");
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "create_id"));
        if (!val.equals(context.nil)) {
            OjLibrary.parser.setCreate_id(((RubyString) val).decodeString());
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "circular"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setCircular('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setCircular('n');
            } else {
                throw context.runtime.newArgumentError(":circular must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "auto_define"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setAuto_define('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setAuto_define('n');
            } else {
                throw context.runtime.newArgumentError(":auto_define must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "symbol_keys"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setSym_key('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setSym_key('n');
            } else {
                throw context.runtime.newArgumentError(":symbol_keys must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "class_cache"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setClass_cache('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setClass_cache('n');
            } else {
                throw context.runtime.newArgumentError(":class_cache must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "bigdecimal_as_decimal"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setBigdec_as_num('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setBigdec_as_num('n');
            } else {
                throw context.runtime.newArgumentError(":bigdecimal_as_decimal must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "use_to_json"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setTo_json('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setTo_json('n');
            } else {
                throw context.runtime.newArgumentError(":use_to_json must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "nilnil"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setNilnil('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setNilnil('n');
            } else {
                throw context.runtime.newArgumentError(":nilnil must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "allow_gc"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setAllow_gc('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setAllow_gc('n');
            } else {
                throw context.runtime.newArgumentError(":allow_gc must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "quirks_mode"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setQuirks_mode('y');
            } else if (val instanceof False) {
                OjLibrary.parser.setQuirks_mode('n');
            } else {
                throw context.runtime.newArgumentError(":quirks_mode must be true, false, or nil.");
            }
        }

        val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "ascii_only"));
        if (!val.equals(context.nil)) {
            if (val instanceof True) {
                OjLibrary.parser.setEscape_mode('a');
            } else if (val instanceof False) {
                OjLibrary.parser.setEscape_mode('j');
            }
        }

        return context.nil;
    }

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject load(ThreadContext context, IRubyObject self, IRubyObject[] args) throws Exception {

        char mode = OjLibrary.parser.getMode();

        if (args.length <= 0) {
            throw context.runtime.newArgumentError("Wrong number of arguments to load().");
        } else if (args.length >= 2) {
            TypeConverter.checkType(context, args[1], context.runtime.getHash());
            RubyHash optHash = (RubyHash) args[1];

            IRubyObject val = optHash.op_aref(context, RubySymbol.newSymbol(context.runtime, "mode"));
            if (val.equals(context.nil)) {
                //ignore
            } else if (val.equals(RubySymbol.newSymbol(context.runtime, "object"))) {
                mode = 'o';
            } else if (val.equals(RubySymbol.newSymbol(context.runtime, "strict"))) {
                mode = 's';
            } else if (val.equals(RubySymbol.newSymbol(context.runtime, "compat"))) {
                mode = 'c';
            } else if (val.equals(RubySymbol.newSymbol(context.runtime, "null"))) {
                mode = 'n';
            } else {
                throw context.runtime.newArgumentError(":mode must be :object, :strict, :compat, or :null.");
            }
        }

        switch (mode) {
            case 's':
                return strict_load(context, self, args);
            case 'n':
            case 'c':
                return compat_load(context, self, args);
            case 'o':
            default:
                break;
        }

        return object_load(context, self, args);
    }

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject dump(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("Dump with options");
        return null;
    }

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject load_file(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("Load File with options");
        return null;
    }

    @JRubyMethod(module = true)
    public static IRubyObject mimic_JSON(ThreadContext context, IRubyObject self) {
        System.out.println("mimic_JSON");
        return null;
    }

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject object_load(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("Object Load with options");
        return null;
    }

    ////REGISTER ODD ARGUEMENTS UNCLEAR , can't have more than 3 arguements ##############

    @JRubyMethod(module = true)
    public static IRubyObject register_odd(ThreadContext context, IRubyObject self, IRubyObject clas, IRubyObject
			create_object, IRubyObject create_method) {
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

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject strict_load(ThreadContext context, IRubyObject self, IRubyObject[] args) throws Exception{

        ParseInfo pi = new StrictparserInfo();
        pi.setOptions(new Options());
        IRubyObject json = (IRubyObject) ojParsePi(context, args, pi, null, 1);
        return json;
    }

    @JRubyMethod(module = true, required = 2, optional = 1)
    public static IRubyObject to_file(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("To File with options");
        return null;
    }

    @JRubyMethod(module = true, required = 2, optional = 1)
    public static IRubyObject to_stream(ThreadContext context, IRubyObject self, IRubyObject[] args) {
        System.out.println("To stream with options");
        return null;
    }

    @JRubyMethod(module = true, required = 1, optional = 1)
    public static IRubyObject compat_load(ThreadContext context, IRubyObject self, IRubyObject[] args) {

        ParseInfo pi = new CompatParseInfo();
        pi.setOptions(new Options());
        return null;
    }

    private static Object ojParsePi(ThreadContext context, IRubyObject[] args, ParseInfo pi, String json, int
            yield0k) throws Exception{

        Object result;
        Object wrappedStack;
        if (args.length < 1) {
            throw context.runtime.newArgumentError("Wrong number of arguments to load().");
        }
        Object input = args[0];
        if (args.length == 2) {
            //Need to test if correct
            default_options(context, null, args[1]);
        }
        pi.setProc(null);
        if (json != null) {
            pi.setJson(json);
        } else if (input instanceof org.jruby.RubyString) {
            pi.setJson(((RubyString) input).asJavaString());
        } else if (input == null && pi.getOptions().getNilnil() == 'y') {
            return null;
        } else {
            //if()
        }
        if (pi.getOptions().getCircular() == 'y') {
            pi.setCircArray(new CircArray());
        }
        if (pi.getOptions().getAllow_gc() == 'y') {

        }
//        wrappedStack = pi.getStack();
//        rb_protect(protect_parse, (VALUE)pi, &line);
        Parse.ojParse2(pi);
        result = pi.getStack().peek();
//        DATA_PTR(wrapped_stack) = 0;
//        if (No == pi->options.allow_gc) {
//            rb_gc_enable();
//        }
//        if (!err_has(&pi->err)) {
//            // If the stack is not empty then the JSON terminated early.
//            Val	v;
//
//            if (0 != (v = stack_peek(&pi->stack))) {
//                switch (v->next) {
//                    case NEXT_ARRAY_NEW:
//                    case NEXT_ARRAY_ELEMENT:
//                    case NEXT_ARRAY_COMMA:
//                        oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "Array not terminated");
//                        break;
//                    case NEXT_HASH_NEW:
//                    case NEXT_HASH_KEY:
//                    case NEXT_HASH_COLON:
//                    case NEXT_HASH_VALUE:
//                    case NEXT_HASH_COMMA:
//                        oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "Hash/Object not terminated");
//                        break;
//                    default:
//                        oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "not terminated");
//                }
//            }
//        }


            pi.setCircArray(null);
            pi.getStack().removeAllElements();
//        if (0 != line) {
//            rb_jump_tag(line);
//        }
//        if (err_has(&pi->err)) {
//            oj_err_raise(&pi->err);
//        }
//        if (pi->options.quirks_mode == No) {
//            switch (rb_type(result)) {
//                case T_NIL:
//                case T_TRUE:
//                case T_FALSE:
//                case T_FIXNUM:
//                case T_FLOAT:
//                case T_CLASS:
//                case T_SYMBOL:
//                    rb_raise(oj_parse_error_class, "unexpected non-document value");
//                    break;
//                default:
//                    // okay
//                    break;
//            }
//        }
        return result;
    }

    private static void oj_pi_set_input_str(ParseInfo pi, Object input) {


    }




}
