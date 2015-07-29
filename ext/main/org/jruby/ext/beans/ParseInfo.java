package org.jruby.ext.beans;

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

    public void colon() throws Exception{
        Val parent = this.getStack().peek();
        if(parent!=null && parent.getNext() == ':'){
            parent.setNext('v');
        } else{
            throw new Exception("Unexpected Colon");
        }
    }

    abstract public Object startHash();

    abstract public void endHash();

    abstract public Object hashKey(String key, int klen);

    abstract public void hashSetCstr(Val kval, String str, int len, String orig);

    abstract public void hashSetNum(Val kval, NumInfo ni);

    abstract public void hashSetvalue(Val kval, Object value);

    abstract public ArrayList startArray();

    abstract public void endArray();

    abstract public void arrayAppendCstr(String str, int len, String orig);

    abstract public void arrayAppendNum(NumInfo ni);

    abstract public void arrayAppendValue(Object value);

    abstract public void addCstr(String str, int len, String orig);

    abstract public void addNum(NumInfo ni);

    abstract public void addValue(Object value);

}
