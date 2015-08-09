package org.jruby.ext.beans;

import org.jruby.ext.constants.OjConstants;

import java.util.ArrayList;
import java.util.Stack;

public abstract class ParseInfo {

    private String json;
    private String cur;
    private String end;
    private Reader reader;
    private Options options;
    private Object handler;
    private int expectValue;
    private Stack<Val> stack;
    private Object proc;
    private CircArray circArray;
    private int currentIndex;

    public ParseInfo() {
        this.json = "";
        this.stack = new Stack<>();
    }

    public CircArray getCircArray() {
        return circArray;
    }

    public void setCircArray(CircArray circArray) {
        this.circArray = circArray;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
        this.currentIndex = 0;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public int getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(int expectValue) {
        this.expectValue = expectValue;
    }

    public Stack<Val> getStack() {
        return stack;
    }

    public void setStack(Stack<Val> stack) {
        this.stack = stack;
    }


    public Object getProc() {
        return proc;
    }

    public void setProc(Object proc) {
        this.proc = proc;
    }

    public void incrementCurrentIndex(){
        currentIndex++;
    }

    public void decrementCurrentIndex(){
        currentIndex--;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public char getCurrentChar (){
        return cur.charAt(currentIndex);
    }

    public void colon() throws Exception{
        Val parent = this.getStack().peek();
        if(parent!=null && parent.getNext() == OjConstants.NEXT_HASH_COLON){
            parent.setNext(OjConstants.NEXT_HASH_VALUE);
        } else{
            throw new Exception("Unexpected Colon");
        }
    }

    public void comma() throws Exception{
        Val parent = this.getStack().peek();
        if(parent == null){
            throw new Exception("Unexpected Comma");
        } else if( parent.getNext() == OjConstants.NEXT_ARRAY_COMMA){
            parent.setNext(OjConstants.NEXT_ARRAY_ELEMENT);
        } else if( parent.getNext() == OjConstants.NEXT_HASH_COMMA){
            parent.setNext(OjConstants.NEXT_HASH_KEY);
        } else {
            throw new Exception("Unexpected Comma");
        }
    }

    public void readStr() throws Exception{
        int	str = getCurrentIndex();
        Val		parent = this.getStack().peek();

        for (; '"' != getCurrentChar(); incrementCurrentIndex()) {
            if (currentIndex > cur.length()) {
                throw new Exception("quoted string not terminated");
            } else if (getCurrentChar() == '\0' ) {
                throw new Exception("NULL byte in string");
            } else if (getCurrentChar() == '\\') {
                readEscapedStr(str);
            }
        }
        if (parent==null) { // simple add
            addCstr(cur.substring(str));
        }
        else {
            switch (parent.getNext()) {
                case OjConstants.NEXT_ARRAY_NEW:
                case OjConstants.NEXT_ARRAY_ELEMENT:
                    arrayAppendCstr(cur.substring(str));
                    parent.setNext(OjConstants.NEXT_ARRAY_COMMA);
                    break;
                case OjConstants.NEXT_HASH_NEW:
                case OjConstants.NEXT_HASH_KEY:
                    parent.setKeyVal(hashKey(cur.substring(str)));
                    if (parent.getKeyVal()==null){
                        parent.setKey(cur.substring(str));
                    }
                    else {
                        parent.setKey("");
                    }
                    parent.setKl(cur.charAt(str));
                    parent.setNext(OjConstants.NEXT_HASH_COLON);
                    break;
                case OjConstants.NEXT_HASH_VALUE:
                    hashSetCstr(parent,cur.substring(str));
                    if(parent.getKey() == null && parent.getKey().length() >0 && (parent.getKey().length()< json
                            .length() || cur.length() - currentIndex < parent.getKey().length())){

                        parent.setKey("");
                    }
                    parent.setNext(OjConstants.NEXT_HASH_COMMA);
                    break;
                case OjConstants.NEXT_HASH_COMMA:
                case OjConstants.NEXT_NONE:
                case OjConstants.NEXT_ARRAY_COMMA:
                case OjConstants.NEXT_HASH_COLON:
                default:
                    throw new Exception("expected " + parent.staclNextString() + ", not a string");
            }
        }
        currentIndex++;
    }

    public void readEscapedStr(int start) throws Exception {

        StringBuffer buf = new StringBuffer();

        int code;
        Val parent = this.getStack().peek();
        int cnt = currentIndex - start;
        if (0 < cnt) {
            buf.append(cur, start, currentIndex);
        }
        for (char s = cur.charAt(currentIndex); s != '"'; s = cur.charAt(++currentIndex)) {
            if (currentIndex > cur.length()) {
                throw new Exception("quoted string not terminated");
            } else if ('\\' == s) {
                currentIndex++;
                switch (s) {
                    case 'n':
                        buf.append('\n');
                        break;
                    case 'r':
                        buf.append('\r');
                        break;
                    case 't':
                        buf.append('\t');
                        break;
                    case 'f':
                        buf.append('\f');
                        break;
                    case 'b':
                        buf.append('\b');
                        break;
                    case '"':
                        buf.append('"');
                        break;
                    case '/':
                        buf.append('/');
                        break;
                    case '\\':
                        buf.append('\\');
                        break;
                    case 'u':
                        currentIndex++;
                        code = readHex();
                        if (0x0000D800 <= code && code <= 0x0000DFFF) {
                            int c1 = (code - 0x0000D800) & 0x000003FF;
                            int c2;
                            if ('\\' != cur.charAt(currentIndex) || 'u' != cur.charAt(currentIndex + 1)) {
                                throw new Exception("invalid escaped character");
                            }
                            currentIndex += 2;
                            c2 = readHex();
                            c2 = (c2 - 0x0000DC00) & 0x000003FF;
                            code = ((c1 << 10) | c2) + 0x00010000;
                        }
                        buf = unicodeToChars(buf, code);
                        break;
                    default:
                        throw new Exception("invalid escaped character");
                }
            } else {
                buf.append(cur.charAt(currentIndex));
            }
        }
        if (parent == null) {
            addCstr(buf.toString());
        } else {
            switch (parent.getNext()) {
                case OjConstants.NEXT_ARRAY_NEW:
                case OjConstants.NEXT_ARRAY_ELEMENT:
                    this.arrayAppendCstr(buf.toString());
                    parent.setNext(OjConstants.NEXT_ARRAY_COMMA);
                    break;
                case OjConstants.NEXT_HASH_NEW:
                case OjConstants.NEXT_HASH_KEY:
                    parent.setKeyVal(this.hashKey(buf.toString()));
                    if (parent.getKeyVal() == null) {
                        parent.setKey(buf.toString());
                    } else {
                        parent.setKey("");
                    }
                    parent.setKl(getCur().charAt(start));
                    parent.setNext(OjConstants.NEXT_HASH_COLON);
                    break;
                case OjConstants.NEXT_HASH_VALUE:
                    this.hashSetCstr(parent, buf.toString());
                    if (parent.getKey() != null && parent.getKey().length() > 0 && (parent.getKey().length() < json
                            .length() || cur.length() - currentIndex < parent.getKey().length())) {

                        parent.setKey("");
                    }
                    parent.setNext(OjConstants.NEXT_HASH_COMMA);
                    break;
                case OjConstants.NEXT_HASH_COMMA:
                case OjConstants.NEXT_NONE:
                case OjConstants.NEXT_ARRAY_COMMA:
                case OjConstants.NEXT_HASH_COLON:
                default:
                    throw new Exception("expected " + parent.staclNextString() + ", not a string");
            }
            incrementCurrentIndex();
        }
    }

    public int readHex() throws Exception {

        int b = 0;
        int i;

        for (i = 0; i < 4; i++, currentIndex++) {
            b = b << 4;
            char current = getCurrentChar();
            if (current == '0' || current == '1' || current == '2' || current == '3' || current == '4' || current == '5'
                    || current == '6' || current == '7' || current == '8' || current == '9') {
                b += Integer.parseInt(current + "");
            } else if (current == 'A' || current == 'a') {
                b += 10;
            } else if (current == 'B' || current == 'b') {
                b += 11;
            } else if (current == 'C' || current == 'c') {
                b += 12;
            } else if (current == 'D' || current == 'd') {
                b += 13;
            } else if (current == 'E' || current == 'e') {
                b += 14;
            } else if (current == 'F' || current == 'f') {
                b += 15;
            } else {
                throw new Exception("invalid hex character");
            }
        }
        return b;
    }

    public StringBuffer unicodeToChars(StringBuffer buffer, int code) throws Exception{

        if (0x0000007F >= code) {
            buffer.append((char)code);
        } else if (0x000007FF >= code) {
            buffer.append(0xC0 | (code >> 6));
            buffer.append(0x80 | (0x3F & code));
        } else if (0x0000FFFF >= code) {
            buffer.append(0xE0 | (code >> 12));
            buffer.append(0x80 | ((code >> 6) & 0x3F));
            buffer.append(0x80 | (0x3F & code));
        } else if (0x001FFFFF >= code) {
            buffer.append(0xF0 | (code >> 18));
            buffer.append(0x80 | ((code >> 12) & 0x3F));
            buffer.append(0x80 | ((code >> 6) & 0x3F));
            buffer.append(0x80 | (0x3F & code));
        } else if (0x03FFFFFF >= code) {
            buffer.append(0xF8 | (code >> 24));
            buffer.append(0x80 | ((code >> 18) & 0x3F));
            buffer.append(0x80 | ((code >> 12) & 0x3F));
            buffer.append(0x80 | ((code >> 6) & 0x3F));
            buffer.append(0x80 | (0x3F & code));
        } else if (0x7FFFFFFF >= code) {
            buffer.append(0xFC | (code >> 30));
            buffer.append(0x80 | ((code >> 24) & 0x3F));
            buffer.append(0x80 | ((code >> 18) & 0x3F));
            buffer.append(0x80 | ((code >> 12) & 0x3F));
            buffer.append(0x80 | ((code >> 6) & 0x3F));
            buffer.append(0x80 | (0x3F & code));
        } else {
            throw new Exception("invalid Unicode character");
        }
        return buffer;
    }

    public void readNum(){


//        struct _NumInfo	ni;
//        Val			parent = stack_peek(&pi->stack);
//        int			zero_cnt = 0;
//
//        ni.str = pi->cur;
//        ni.i = 0;
//        ni.num = 0;
//        ni.div = 1;
//        ni.len = 0;
//        ni.exp = 0;
//        ni.dec_cnt = 0;
//        ni.big = 0;
//        ni.infinity = 0;
//        ni.nan = 0;
//        ni.neg = 0;
//        ni.hasExp = 0;
//        ni.no_big = (FloatDec == pi->options.bigdec_load);
//
//        if ('-' == *pi->cur) {
//            pi->cur++;
//            ni.neg = 1;
//        } else if ('+' == *pi->cur) {
//            pi->cur++;
//        }
//        if ('I' == *pi->cur) {
//            if (0 != strncmp("Infinity", pi->cur, 8)) {
//                oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "not a number or other value");
//                return;
//            }
//            pi->cur += 8;
//            ni.infinity = 1;
//        } else if ('N' == *pi->cur || 'n' == *pi->cur) {
//            if ('a' != pi->cur[1] || ('N' != pi->cur[2] && 'n' != pi->cur[2])) {
//                oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "not a number or other value");
//                return;
//            }
//            pi->cur += 3;
//            ni.nan = 1;
//        } else {
//            for (; '0' <= *pi->cur && *pi->cur <= '9'; pi->cur++) {
//                ni.dec_cnt++;
//                if (ni.big) {
//                    ni.big++;
//                } else {
//                    int	d = (*pi->cur - '0');
//
//                    if (0 == d) {
//                        zero_cnt++;
//                    } else {
//                        zero_cnt = 0;
//                    }
//                    // TBD move size check here
//                    ni.i = ni.i * 10 + d;
//                    if (LONG_MAX <= ni.i || DEC_MAX < ni.dec_cnt - zero_cnt) {
//                        ni.big = 1;
//                    }
//                }
//            }
//            if ('.' == *pi->cur) {
//                pi->cur++;
//                for (; '0' <= *pi->cur && *pi->cur <= '9'; pi->cur++) {
//                    int	d = (*pi->cur - '0');
//
//                    if (0 == d) {
//                        zero_cnt++;
//                    } else {
//                        zero_cnt = 0;
//                    }
//                    ni.dec_cnt++;
//                    // TBD move size check here
//                    ni.num = ni.num * 10 + d;
//                    ni.div *= 10;
//                    if (LONG_MAX <= ni.div || DEC_MAX < ni.dec_cnt - zero_cnt) {
//                        ni.big = 1;
//                    }
//                }
//            }
//            if ('e' == *pi->cur || 'E' == *pi->cur) {
//                int	eneg = 0;
//
//                ni.hasExp = 1;
//                pi->cur++;
//                if ('-' == *pi->cur) {
//                    pi->cur++;
//                    eneg = 1;
//                } else if ('+' == *pi->cur) {
//                    pi->cur++;
//                }
//                for (; '0' <= *pi->cur && *pi->cur <= '9'; pi->cur++) {
//                    ni.exp = ni.exp * 10 + (*pi->cur - '0');
//                    if (EXP_MAX <= ni.exp) {
//                        ni.big = 1;
//                    }
//                }
//                if (eneg) {
//                    ni.exp = -ni.exp;
//                }
//            }
//            ni.dec_cnt -= zero_cnt;
//            ni.len = pi->cur - ni.str;
//        }
//        if (BigDec == pi->options.bigdec_load) {
//            ni.big = 1;
//        }
//        if (0 == parent) {
//            pi->add_num(pi, &ni);
//        } else {
//            switch (parent->next) {
//                case NEXT_ARRAY_NEW:
//                case NEXT_ARRAY_ELEMENT:
//                    pi->array_append_num(pi, &ni);
//                    parent->next = NEXT_ARRAY_COMMA;
//                    break;
//                case NEXT_HASH_VALUE:
//                    pi->hash_set_num(pi, parent, &ni);
//                    if (0 != parent->key && 0 < parent->klen && (parent->key < pi->json || pi->cur < parent->key)) {
//                        xfree((char*)parent->key);
//                        parent->key = 0;
//                    }
//                    parent->next = NEXT_HASH_COMMA;
//                    break;
//                default:
//                    oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "expected %s", oj_stack_next_string(parent->next));
//                    break;
//            }
//        }
    }

    abstract public Object startHash();

    abstract public void endHash();

    abstract public Object hashKey(String key);

    abstract public void hashSetCstr(Val kval, String str);

    abstract public void hashSetNum(Val kval, NumInfo ni);

    abstract public void hashSetvalue(Val kval, Object value);

    abstract public ArrayList startArray();

    abstract public void endArray();

    abstract public void arrayAppendCstr(String str);

    abstract public void arrayAppendNum(NumInfo ni);

    abstract public void arrayAppendValue(Object value);

    abstract public void addCstr(String str);

    abstract public void addNum(NumInfo ni);

    abstract public void addValue(Object value);

}
