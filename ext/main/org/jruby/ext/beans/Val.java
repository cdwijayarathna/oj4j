package org.jruby.ext.beans;

import java.util.Hashtable;

/**
 * Created by chamila on 6/6/15.
 */
public class Val {

    private Object val; //ToDo: declared volatile in original oj, so check more
    private String key;
    private String keyArray;
    private Object keyVal;
    private String className;
    private char next;


    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyArray() {
        return keyArray;
    }

    public void setKeyArray(String keyArray) {
        this.keyArray = keyArray;
    }

    public Object getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(Object keyVal) {
        this.keyVal = keyVal;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public char getNext() {
        return next;
    }

    public void setNext(char next) {
        this.next = next;
    }
}
