package org.jruby.ext.beans;

import org.jruby.ext.Resolve;

/**
 * Created by chamila on 7/18/15.
 */
public class CompatParseInfo extends StrictparserInfo {

    @Override
    public void endHash() {

        Val parent = getStack().peek();
        if(parent.getClassName() == null){
            Object clas = Resolve.name2Class(this, parent.getClassName(), 0);
        }
        //not implemented
    }

}
