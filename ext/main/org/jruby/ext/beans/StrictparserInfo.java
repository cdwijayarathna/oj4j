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

    public CircArray getCircArray() {
        return super.getCircArray();
    }

    public void setCircArray(CircArray circArray) {
        super.setCircArray(circArray);
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
    public Object startHash() {
        return new Hashtable<>();
    }

    @Override
    public void endHash() {
        //not implemented
    }

    public Object getProc() {
        return super.getProc();
    }

    public void setProc(Object proc) {
        super.setProc(proc);
    }

    @Override
    public Object hashKey(String key, int klen) {
        return null;
    }

    @Override
    public void hashSetCstr(Val parent, String str, int len, String orig) {
        ((Hashtable)this.getStack().peek().getVal()).put(parent.getKeyVal(), str);
    }

    @Override
    public void hashSetNum(Val parent, NumInfo ni) {

        ((Hashtable)this.getStack().peek().getVal()).put(parent.getKeyVal(), ni.getNum());
    }

    @Override
    public void hashSetvalue(Val parent, Object value) {

        ((Hashtable)this.getStack().peek().getVal()).put(parent.getKeyVal(), value);

    }

    @Override
    public ArrayList startArray() {
        return new ArrayList();
    }

    @Override
    public void endArray() {
        //not implemented
    }

    @Override
    public void arrayAppendCstr(String str, int len, String orig) {
        ((ArrayList)this.getStack().peek().getVal()).add(str);
    }

    @Override
    public void arrayAppendNum(NumInfo ni) {
        ((ArrayList)this.getStack().peek().getVal()).add(ni.getNum());
    }

    @Override
    public void arrayAppendValue(Object value) {
        ((ArrayList)this.getStack().peek().getVal()).add(value);
    }

    @Override
    public void addCstr(String str, int len, String orig) {
        this.getStack().get(0).setVal(str);
    }

    @Override
    public void addNum(NumInfo ni) {
        this.getStack().get(0).setVal(ni.getNum());
    }

    @Override
    public void addValue(Object value) {
        this.getStack().get(0).setVal(value);
    }

}
