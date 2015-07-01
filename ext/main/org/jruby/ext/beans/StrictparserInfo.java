package org.jruby.ext.beans;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by chamila on 6/6/15.
 */
public class StrictparserInfo extends ParseInfo {

    public StrictparserInfo() {
        super();
        this.setExpectValue(1);
    }

    @Override
    public String getCur() {
        return super.getCur();
    }

    @Override
    public void setCur(String cur) {
        super.setCur(cur);
    }

    @Override
    public String getJson() {
        return super.getJson();
    }

    @Override
    public void setJson(String json) {
        super.setJson(json);
    }

    @Override
    public String getEnd() {
        return super.getEnd();
    }

    @Override
    public void setEnd(String end) {
        super.setEnd(end);
    }

    @Override
    public Reader getReader() {
        return super.getReader();
    }

    @Override
    public void setReader(Reader reader) {
        super.setReader(reader);
    }

    @Override
    public Options getOptions() {
        return super.getOptions();
    }

    @Override
    public void setOptions(Options options) {
        super.setOptions(options);
    }

    @Override
    public Object getHandler() {
        return super.getHandler();
    }

    @Override
    public void setHandler(Object handler) {
        super.setHandler(handler);
    }

    @Override
    public int getExpectValue() {
        return super.getExpectValue();
    }

    @Override
    public void setExpectValue(int expectValue) {
        super.setExpectValue(expectValue);
    }

    @Override
    public Stack<Val> getStack() {
        return super.getStack();
    }

    public void setStack(Stack<Val> stack) {
        super.setStack(stack);
    }

    @Override
    public Object startHash(ParseInfo pi) {
        return new Hashtable<>();
    }

    @Override
    public void endHash(ParseInfo pi) {
        //not implemented
    }

    @Override
    public Object hashKey(ParseInfo pi, String key, int klen) {
        return null;
    }

    @Override
    public void hashSetCstr(ParseInfo pi, Val parent, String str, int len, String orig) {
        ((Hashtable)pi.getStack().peek().getVal()).put(parent.getKeyVal(), str);
    }

    @Override
    public void hashSetNum(ParseInfo pi, Val parent, NumInfo ni) {

        ((Hashtable)pi.getStack().peek().getVal()).put(parent.getKeyVal(), ni.getNum());
    }

    @Override
    public void hashSetvalue(ParseInfo pi, Val parent, Object value) {

        ((Hashtable)pi.getStack().peek().getVal()).put(parent.getKeyVal(), value);

    }

    @Override
    public ArrayList startArray(ParseInfo pi) {
        return new ArrayList();
    }

    @Override
    public void endArray(ParseInfo pi) {
        //not implemented
    }

    @Override
    public void arrayAppendCstr(ParseInfo pi, String str, int len, String orig) {
        ((ArrayList)pi.getStack().peek().getVal()).add(str);
    }

    @Override
    public void arrayAppendNum(ParseInfo pi, NumInfo ni) {
        ((ArrayList)pi.getStack().peek().getVal()).add(ni.getNum());
    }

    @Override
    public void arrayAppendValue(ParseInfo pi, Object value) {
        ((ArrayList)pi.getStack().peek().getVal()).add(value);
    }

    @Override
    public void addCstr(ParseInfo pi, String str, int len, String orig) {
        pi.getStack().get(0).setVal(str);
    }

    @Override
    public void addNum(ParseInfo pi, NumInfo ni) {
        pi.getStack().get(0).setVal(ni.getNum());
    }

    @Override
    public void addValue(ParseInfo pi, Object value) {
        pi.getStack().get(0).setVal(value);
    }
    
}
