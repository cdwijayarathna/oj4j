package org.jruby.ext.beans;

/**
 * Created by chamila on 7/19/15.
 */
public class Parse {

    public static Object protect_parse(ParseInfo pi){

        return null;
    }

    public static void ojParse2(ParseInfo pi) throws Exception {
        //pi.setCur(pi.getCur());
        int currentIndex = 0;
        while(true){
            nextNonWhite(pi);
            switch (pi.getCurrentChar()){
                case '{':
                    pi.startHash();
                    break;
                case  '}':
                    pi.endHash();
                    break;
                case ':':
                    pi.colon();
                    break;
                case '[':
                    pi.startArray();
                    break;
                case ']':
                    pi.endArray();
                    break;
                case ',':
                    pi.comma();
                    break;
                case '"':




            }
            pi.incrementCurrentIndex();
        }

    }

    public static void nextNonWhite(ParseInfo pi){
        for (;true;){
            switch (pi.getCurrentChar()){
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

}
