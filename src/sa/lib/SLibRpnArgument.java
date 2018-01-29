/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib;

/**
 *
 * @author Sergio Flores
 */
public final class SLibRpnArgument {

    private Object moArgument;
    private SLibRpnArgumentType meArgumentType;

    /**
     * @param argument SLibRpnOperator (+, -, *, /) or operand name as String.
     * @param argumentType SLibRpnArgumentType (OPERAND or OPERATOR).
     */
    public SLibRpnArgument(Object argument, SLibRpnArgumentType argumentType) {
        if (argument instanceof SLibRpnOperator || argument instanceof String) {
            moArgument = argument;
            meArgumentType = argumentType;
        }
        else {
            moArgument = null;
            meArgumentType = SLibRpnArgumentType.UNDEFINED;
        }

    }

    public Object getArgument() {
        return moArgument;
    }

    public SLibRpnArgumentType getArgumentType() {
        return meArgumentType;
    }
}
