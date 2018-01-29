/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib;

/**
 *
 * @author Sergio Flores
 */
public class SLibResult {

    protected int mnResult;
    protected String msMessage;

    public SLibResult() {
        this(SLibConsts.UNDEFINED, "");
    }

    public SLibResult(int result, String message) {
        mnResult = result;
        msMessage = message;
    }

    public void setResult(int n) { mnResult = n; }
    public void setMessage(String s) { msMessage = s; }

    public int getResult() { return mnResult; }
    public String getMessage() { return msMessage; }
}
