/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib;

import java.lang.reflect.Method;

/**
 *
 * @author Sergio Flores
 */
public class SLibMethod {

    protected Object moTarget;
    protected Method moMethod;
    protected Object[] maoMethodArgs;

    public SLibMethod(Object target, Method method, Object[] args) {
        moTarget = target;
        moMethod = method;
        maoMethodArgs = args;
    }

    public Object getTarget() {
        return moTarget;
    }

    public Method getMethod() {
        return moMethod;
    }

    public Object[] getMethodArgs() {
        return maoMethodArgs;
    }
}
