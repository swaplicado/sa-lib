/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

/**
 *
 * @author Sergio Flores
 */
public class SGuiFields {

    protected JTabbedPane moTabbedPane;
    protected Vector<SGuiField> mvFields;

    public SGuiFields() {
        this(null);
    }

    public SGuiFields(JTabbedPane tabbedPane) {
        moTabbedPane = tabbedPane;
        mvFields = new Vector<SGuiField>();
    }

    public void setTabbedPane(JTabbedPane o) { moTabbedPane = o; }

    public JTabbedPane getTabbedPane() { return moTabbedPane; }
    public Vector<SGuiField> getFields() { return mvFields; }

    public void addField(SGuiField field) {
        addField(field, -1);
    }

    public void addField(SGuiField field, int tab) {
        int size = mvFields.size();

        if (tab != -1) {
            field.setTab(tab);
        }

        mvFields.add(field);

        if (size > 0) {
            mvFields.get(size - 1).setNextField(field);
        }
    }

    public void setFormButton(JButton button) {
        int size = mvFields.size();

        if (size > 0) {
            mvFields.get(size - 1).setFormButton(button);
        }
    }

    public void resetFields() {
        for (SGuiField field : mvFields) {
            field.resetField();
        }
    }

    public void setFieldsEnabled(final boolean enabled) {
        for (SGuiField field : mvFields) {
            field.setEnabled(enabled);
        }
    }

    public void setFieldsEditable(final boolean editable) {
        for (SGuiField field : mvFields) {
            field.setEditable(editable);
        }
    }

    public SGuiValidation validateFields() {
        SGuiValidation validation = new SGuiValidation();

        for (SGuiField field : mvFields) {
            if (field.isFocusable()) {
                validation = field.validateField();

                if (!validation.isValid()) {
                    validation.setTabbedPane(moTabbedPane);
                    break;
                }
            }
        }

        return validation;
    }
}
