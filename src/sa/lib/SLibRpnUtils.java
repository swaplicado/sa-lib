/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib;

/**
 *
 * @author Sergio Flores
 */
public abstract class SLibRpnUtils {

    public static SLibRpnArgumentType getArgumentType(final String str) {
        SLibRpnArgumentType argumentType = SLibRpnArgumentType.UNDEFINED;

        if (str.compareTo(SLibRpnArgumentType.OPERAND.toString()) == 0) {
            argumentType = SLibRpnArgumentType.OPERAND;
        }
        else if (str.compareTo(SLibRpnArgumentType.OPERATOR.toString()) == 0) {
            argumentType = SLibRpnArgumentType.OPERATOR;
        }

        return argumentType;
    }

    public static SLibRpnOperator getOperator(final String str) {
        SLibRpnOperator operator = SLibRpnOperator.UNDEFINED;

        if (str.compareTo(SLibRpnOperator.ADDITION.toString()) == 0) {
            operator = SLibRpnOperator.ADDITION;
        }
        else if (str.compareTo(SLibRpnOperator.SUBTRACTION.toString()) == 0) {
            operator = SLibRpnOperator.SUBTRACTION;
        }
        else if (str.compareTo(SLibRpnOperator.MULTIPLICATION.toString()) == 0) {
            operator = SLibRpnOperator.MULTIPLICATION;
        }
        else if (str.compareTo(SLibRpnOperator.DIVISION.toString()) == 0) {
            operator = SLibRpnOperator.DIVISION;
        }

        return operator;
    }
}
