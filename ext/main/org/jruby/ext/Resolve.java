package org.jruby.ext;

import org.jruby.ext.beans.ParseInfo;
import org.jruby.ext.beans.StrictparserInfo;

/**
 * Created by chamila on 7/18/15.
 */
public class Resolve {

    public static Object name2Class(ParseInfo pi, String name, int autoDefine){

        if(pi.getOptions().getClass_cache() == 'n'){

        }
        return null;

    }

    public static Object resolveClassPath(ParseInfo pi, String name, int autoDefine){
        //ToDo : Implement this
        String	class_name;
        Object	clas;
        String s;
        String n = name;


        return null;
    }

}
