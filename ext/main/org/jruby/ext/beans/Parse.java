package org.jruby.ext.beans;

/**
 * Created by chamila on 7/19/15.
 */
public class Parse {

    public static Object protect_parse(ParseInfo pi){

        return null;
    }

    public static void ojParse2(ParseInfo pi) throws Exception {
        pi.setCur(pi.getCur());
        int currentIndex = 0;
        while(true){
            currentIndex = nextNonWhite(pi,currentIndex);
            switch (pi.getCur().charAt(currentIndex)){
                case '{':
                    pi.startHash();
                    break;
                case  '}':
                    pi.endHash();
                    break;
                case ':':
                    pi.colon();
                    break;

            }
        }

    }

    public static int nextNonWhite(ParseInfo pi, int currentIndex){
        for (;true;currentIndex++){
            switch (pi.getCur().charAt(currentIndex)){
                case '\t':
                case '\f':
                case '\n':
                case '\r':
                    break;
                default:
                    return currentIndex;
            }
        }
    }

}
