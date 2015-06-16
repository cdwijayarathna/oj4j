package org.jruby.ext.beans;

public class Options {
	
	//options
	private int indent;
	private char circular; 			//Yes = y, No = n
	private char auto_define;		//Yes = y, No = n
	private char sym_key;			//Yes = y, No = n
	private char escape_mode;		//NLEsc	= 'n',JSONEsc = 'j',XSSEsc = 'x',ASCIIEsc = 'a'
	private char mode;				// StrictMode = 's',ObjectMode = 'o', NullMode = 'n', CompatMode = 'c'		
	private char class_cache;		//Yes = y, No = n
	private char time_format;		// UnixTime	= 'u', UnixZTime = 'z', XmlTime	= 'x', RubyTime	= 'r'
	private char bigdec_as_num;		//Yes = y, No = n
	private char bigdec_load;		//BigDec = 'b', FloatDec = 'f', AutoDec	= 'a'
	private char to_json;			//Yes = y, No = n
	private char nilnil;			//Yes = y, No = n
	private char allow_gc;			//Yes = y, No = n
	private char quirks_mode;		//Yes = y, No = n
	private String create_id;
	private int create_id_len;
	private int sec_prec;
	private int float_prec;
	private String float_fmt;
	
	public Options(){
		
		this.indent = 0;
		this.circular = 'n';
		this.auto_define = 'n';
		this.sym_key = 'n';
		this.escape_mode = 'j';
		this.mode = 'o';
		this.class_cache = 'y';
		this.time_format =  'z';
		this.bigdec_as_num = 'y';
		this.bigdec_load = 'a';
		this.to_json = 'y';
		this.nilnil = 'n';
		this.allow_gc = 'y';
		this.quirks_mode = 'y';
		this.create_id = "json_class";
		this.create_id_len = 10;
		this.sec_prec = 9;
		this.float_prec = 15;
		this.float_fmt = "%0.15g";
		
	}
	
	public int getIndent() {
		return indent;
	}

	public char getCircular() {
		return circular;
	}

	public char getAuto_define() {
		return auto_define;
	}

	public char getSym_key() {
		return sym_key;
	}

	public char getEscape_mode() {
		return escape_mode;
	}

	public char getMode() {
		return mode;
	}

	public char getClass_cache() {
		return class_cache;
	}

	public char getTime_format() {
		return time_format;
	}

	public char getBigdec_as_num() {
		return bigdec_as_num;
	}

	public char getBigdec_load() {
		return bigdec_load;
	}

	public char getTo_json() {
		return to_json;
	}

	public char getNilnil() {
		return nilnil;
	}

	public char getAllow_gc() {
		return allow_gc;
	}

	public char getQuirks_mode() {
		return quirks_mode;
	}

	public String getCreate_id() {
		return create_id;
	}

	public int getCreate_id_len() {
		return create_id_len;
	}

	public int getSec_prec() {
		return sec_prec;
	}

	public int getFloat_prec() {
		return float_prec;
	}

	public String getFloat_fmt() {
		return float_fmt;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public void setCircular(char circular) {
		this.circular = circular;
	}

	public void setAuto_define(char auto_define) {
		this.auto_define = auto_define;
	}

	public void setSym_key(char sym_key) {
		this.sym_key = sym_key;
	}

	public void setEscape_mode(char escape_mode) {
		this.escape_mode = escape_mode;
	}

	public void setMode(char mode) {
		this.mode = mode;
	}

	public void setClass_cache(char class_cache) {
		this.class_cache = class_cache;
	}

	public void setTime_format(char time_format) {
		this.time_format = time_format;
	}

	public void setBigdec_as_num(char bigdec_as_num) {
		this.bigdec_as_num = bigdec_as_num;
	}

	public void setBigdec_load(char bigdec_load) {
		this.bigdec_load = bigdec_load;
	}

	public void setTo_json(char to_json) {
		this.to_json = to_json;
	}

	public void setNilnil(char nilnil) {
		this.nilnil = nilnil;
	}

	public void setAllow_gc(char allow_gc) {
		this.allow_gc = allow_gc;
	}

	public void setQuirks_mode(char quirks_mode) {
		this.quirks_mode = quirks_mode;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}

	public void setCreate_id_len(int create_id_len) {
		this.create_id_len = create_id_len;
	}

	public void setSec_prec(int sec_prec) {
		this.sec_prec = sec_prec;
	}

	public void setFloat_prec(int float_prec) {
		this.float_prec = float_prec;
	}

	public void setFloat_fmt(String float_fmt) {
		this.float_fmt = float_fmt;
	}
	
}