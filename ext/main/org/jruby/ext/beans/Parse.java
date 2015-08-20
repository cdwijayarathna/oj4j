package org.jruby.ext.beans;

import org.jruby.ext.constants.OjConstants;

public class Parse {

    public static Object protect_parse(ParseInfo pi){

        return null;
    }

    public static void ojParse2(ParseInfo pi) throws Exception {
        pi.setCur(pi.getJson());
        while(true){
            System.out.println(pi.getCurrentChar() + " -- " + pi.getStack());
            nextNonWhite(pi);
            switch (pi.getCurrentChar()) {
                case '{':
                    pi.incrementCurrentIndex();
                    startHash(pi);
                    break;
                case '}':
                    pi.incrementCurrentIndex();
                    endHash(pi);
                    break;
                case ':':
                    pi.incrementCurrentIndex();
                    pi.colon();
                    break;
                case '[':
                    pi.incrementCurrentIndex();
                    startArray(pi);
                    break;
                case ']':
                    pi.incrementCurrentIndex();
                    endArray(pi);
                    break;
                case ',':
                    pi.incrementCurrentIndex();
                    pi.comma();
                    break;
                case '"':
                    pi.incrementCurrentIndex();
                    pi.readStr();
                    break;
                case '+':
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'I':
                case 'N':
                    pi.readNum();
                    break;
                case 't':
                    pi.incrementCurrentIndex();
                    pi.readTrue();
                    break;
                case 'f':
                    pi.incrementCurrentIndex();
                    pi.readFalse();
                    break;
                case 'n':
                    pi.incrementCurrentIndex();
                    if (pi.getCurrentChar() == 'u') {
                        pi.readNull();
                    } else {
                        pi.decrementCurrentIndex();
                        pi.readNum();
                    }
                    break;
                case '/':
                    pi.incrementCurrentIndex();
                    pi.skipComment();
                    break;
                case '\0':
                    pi.decrementCurrentIndex();
                default:
                    throw new Exception("unexpected character");
            }
            //ToDo
            if (!(pi.getProc() == null) && pi.getStack().empty()) {

            } else if (pi.getProc() == null) {

            }
        }

    }

    public static void nextNonWhite(ParseInfo pi){
        for (;true;){
            switch (pi.getCurrentChar()){
                case ' ':
                case '\t':
                case '\f':
                case '\n':
                case '\r':
                    pi.incrementCurrentIndex();
                    break;
                default:
                    return;
            }
        }
    }

    public static void startHash (ParseInfo pi) {

        Object v = pi.startHash();
        Val val = new Val(v);
        val.setNext(OjConstants.NEXT_HASH_NEW);
        pi.getStack().push(val);
    }

    public static void endHash (ParseInfo pi) throws Exception{

        Val hash = pi.getStack().peek();

        if (hash == null) {
            throw new Exception("unexpected hash close");
        } else if (hash.getNext() != OjConstants.NEXT_HASH_COMMA && hash.getNext() != OjConstants.NEXT_HASH_NEW) {
            throw new Exception("expected " + hash.staclNextString() + ", not a hash close");
        } else {
            pi.endHash();
            pi.getStack().pop();
            pi.parseAddValue(hash.getVal());
        }
    }

    public static void startArray (ParseInfo pi) {

        Object v = pi.startArray();
        Val val = new Val(v);
        val.setNext(OjConstants.NEXT_HASH_NEW);
        pi.getStack().push(val);
    }

    public static void endArray (ParseInfo pi) throws Exception{

        Val array = pi.getStack().peek();

        if (array == null) {
            throw new Exception("unexpected array close");
        } else if (array.getNext() != OjConstants.NEXT_ARRAY_COMMA && array.getNext() != OjConstants.NEXT_ARRAY_NEW) {
            throw new Exception("expected " + array.staclNextString() + ", not a hash close");
        } else {
            pi.endArray();
            pi.parseAddValue(array.getVal());
        }
    }
}
