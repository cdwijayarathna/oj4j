package org.jruby.ext.beans;

import org.jruby.ext.constants.OjConstants;

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
    private char kl;


    public Val (Object val) {
        this.val = val;
    }

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

    public char getKl() {
        return kl;
    }

    public void setKl(char kl) {
        this.kl = kl;
    }

    public String staclNextString() {
        switch (next) {
            case OjConstants.NEXT_ARRAY_NEW:	return "array element or close";
            case OjConstants.NEXT_ARRAY_ELEMENT:	return "array element";
            case OjConstants.NEXT_ARRAY_COMMA:	return "comma";
            case OjConstants.NEXT_HASH_NEW:		return "hash pair or close";
            case OjConstants.NEXT_HASH_KEY:		return "hash key";
            case OjConstants.NEXT_HASH_COLON:	return "colon";
            case OjConstants.NEXT_HASH_VALUE:	return "hash value";
            case OjConstants.NEXT_HASH_COMMA:	return "comma";
            case OjConstants.NEXT_NONE:		break;
            default:			break;
        }
        return "nothing";
    }
}
