package org.jruby.ext.beans;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by chamila on 6/6/15.
 */
public class StrictparserInfo extends ParseInfo {
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
    public ArrayList<Val> getStack(){
        return super.getStack();
    }

    public void setStack(ArrayList<Val> stack){
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
    public void hashSetCstr(ParseInfo pi, Val kval, String str, int len, String orig) {
        String rstr = str;
    }

    @Override
    public void hashSetNum(ParseInfo pi, Val kval, NumInfo ni) {

    }

    @Override
    public void hashSetvalue(ParseInfo pi, Val kval, Object value) {

    }

    @Override
    public Object startArray(ParseInfo pi) {
        return null;
    }

    @Override
    public void endArray(ParseInfo pi) {

    }

    @Override
    public void arrayAppendCstr(ParseInfo pi, String str, int len, String orig) {

    }

    @Override
    public void arrayAppendNum(ParseInfo pi, NumInfo ni) {

    }

    @Override
    public void arrayAppendValue(ParseInfo pi, Object value) {

    }

    @Override
    public void addCstr(ParseInfo pi, String str, int len, String orig) {

    }

    @Override
    public void addNum(ParseInfo pi, NumInfo ni) {

    }

    @Override
    public void addValue(ParseInfo pi, Object value) {

    }
}
