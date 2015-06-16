package org.jruby.ext.beans;

import java.util.ArrayList;

public abstract class ParseInfo {

    private String json;
    private String cur;
    private String end;
    private Reader reader;
    private Options options;
    private Object handler;
    private int expectValue;

    private ArrayList<Val> stack;

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
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

    public ArrayList<Val> getStack() {
        return stack;
    }

    public void setStack(ArrayList<Val> stack) {
        this.stack = stack;
    }

    abstract public Object startHash(ParseInfo pi);

    abstract public void endHash(ParseInfo pi);

    abstract public Object hashKey(ParseInfo pi, String key, int klen);

    abstract public void hashSetCstr(ParseInfo pi, Val kval, String str, int len, String orig);

    abstract public void hashSetNum(ParseInfo pi, Val kval, NumInfo ni);

    abstract public void hashSetvalue(ParseInfo pi, Val kval, Object value);

    abstract public Object startArray(ParseInfo pi);

    abstract public void endArray(ParseInfo pi);

    abstract public void arrayAppendCstr(ParseInfo pi, String str, int len, String orig);

    abstract public void arrayAppendNum(ParseInfo pi, NumInfo ni);

    abstract public void arrayAppendValue(ParseInfo pi, Object value);

    abstract public void addCstr(ParseInfo pi, String str, int len, String orig);

    abstract public void addNum(ParseInfo pi, NumInfo ni);

    abstract public void addValue(ParseInfo pi, Object value);

}
