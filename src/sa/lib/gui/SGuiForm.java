/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import sa.lib.db.SDbRegistry;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiForm {

    public int getBeanFormType();
    public int getFormType();
    public int getFormSubtype();
    public int getFormStatus();
    public int getFormResult();
    public boolean canShowForm();

    public void setFormSettings(final SGuiClient client, final int beanFormType, final int formType, final int formSubtype, final String title);
    public void setFormVisible(final boolean visible);
    public void setFormEditable(final boolean editable);
    public void updateFormControlStatus();

    public void addAllListeners();
    public void removeAllListeners();
    public void reloadCatalogues();

    public void setRegistry(final SDbRegistry registry) throws Exception;
    public SDbRegistry getRegistry() throws Exception;
    public SGuiValidation validateForm();

    public boolean isFormDataEdited();

    public void setValue(final int type, final Object value);
    public Object getValue(final int type);
    public SGuiFields getFields();

    public void actionEdit();
    public void actionReadInfo();
    public void actionSave();
    public void actionCancel();
}
