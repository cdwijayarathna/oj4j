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
                    pi.incrementCurrentIndex();
                    pi.startHash();
                    break;
                case  '}':
                    pi.incrementCurrentIndex();
                    pi.endHash();
                    break;
                case ':':
                    pi.incrementCurrentIndex();
                    pi.colon();
                    break;
                case '[':
                    pi.incrementCurrentIndex();
                    pi.startArray();
                    break;
                case ']':
                    pi.incrementCurrentIndex();
                    pi.endArray();
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
//                case '/':
//                    skip_comment(pi);
//                    break;
//                case '\0':
//                    pi->cur--;
//                    return;
//                default:
//                    oj_set_error_at(pi, oj_parse_error_class, __FILE__, __LINE__, "unexpected character");
//                    return;





            }
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
