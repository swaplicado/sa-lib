/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.KeyStroke;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGuiUtils {

    public static void setCursorWait(final SGuiClient client) {
        client.getFrame().setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    public static void setCursorDefault(final SGuiClient client) {
        client.getFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public static void setWindowBounds(final Window window, final int width, final int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
    }

    /**
     * @param pattern The following characters can be specified:
     *  y   Any number (Character.isDigit).
     *  M   Any number (Character.isDigit).
     *  d   Any number (Character.isDigit).
     *  H   Any number (Character.isDigit).
     *  m   Any number (Character.isDigit).
     *  s   Any number (Character.isDigit).
     */
    public static String createMaskFormatterDatetime(final String pattern) {
        String maskFormatter = SLibUtils.textTrim(pattern);

        maskFormatter = maskFormatter.replaceAll("y", "#");
        maskFormatter = maskFormatter.replaceAll("M", "#");
        maskFormatter = maskFormatter.replaceAll("d", "#");
        maskFormatter = maskFormatter.replaceAll("H", "#");
        maskFormatter = maskFormatter.replaceAll("m", "#");
        maskFormatter = maskFormatter.replaceAll("s", "#");

        return maskFormatter;
    }

    public static void locateItem(final JComboBox comboBox, final int[] key) {
        boolean located = false;
        SGuiItem item = null;

        if (comboBox.getItemCount() > 0) {
            if (key != null) {
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    item = (SGuiItem) comboBox.getItemAt(i);

                    if (SLibUtils.compareKeys(key, item.getPrimaryKey())) {
                        comboBox.setSelectedIndex(i);
                        located = true;
                        break;
                    }
                }
            }

            if (!located) {
                comboBox.setSelectedIndex(0);
            }
        }
    }

    public static void locateItem(final JComboBox comboBox, final String value) {
        boolean located = false;
        SGuiItem item = null;

        if (comboBox.getItemCount() > 0) {
            if (!value.isEmpty()) {
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    item = (SGuiItem) comboBox.getItemAt(i);

                    if (value.compareTo(item.getItem()) == 0) {
                        comboBox.setSelectedIndex(i);
                        located = true;
                        break;
                    }
                }
            }

            if (!located) {
                comboBox.setSelectedIndex(0);
            }
        }
    }

    public static void locateItem(final JList list, final int[] key) {
        boolean located = false;
        SGuiItem item = null;

        if (list.getModel().getSize() > 0) {
            if (key != null) {
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    item = (SGuiItem) list.getModel().getElementAt(i);

                    if (SLibUtils.compareKeys(key, item.getPrimaryKey())) {
                        list.setSelectedIndex(i);
                        located = true;
                        break;
                    }
                }
            }

            if (!located) {
                list.setSelectedIndex(0);
            }
        }
    }

    /**
     * Maps provided action in component.
     *
     * @param component Root component.
     * @param action Action to be mapped. Must implement actionPerformed(ActionEvent e) method.
     * @param map Map's name.
     * @param keyCode Key code.
     */
    public static void putActionMap(final JComponent component, final AbstractAction action, final String map, final int keyCode) {
        putActionMap(component, action, map, keyCode, 0);
    }

    /**
     * Maps provided action in component.
     *
     * @param component Root component.
     * @param action Action to be mapped. Must implement actionPerformed(ActionEvent e) method.
     * @param map Map's name.
     * @param keyCode Key code.
     * @param modifiers Key code's modifiers.
     */
    public static void putActionMap(final JComponent component, final AbstractAction action, final String map, final int keyCode, final int modifiers) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, modifiers), map);
        component.getActionMap().put(map, action);
    }

    /**
     * Creates and maps action in component.
     *
     * @param component Root component.
     * @param target Target object that will be used for method invocation.
     * @param method Method's name.
     * @param map Map's name.
     * @param keyCode Key code.
     */
    public static void createActionMap(final JComponent component, final Object target, final String method, final String map, final int keyCode) {
        createActionMap(component, target, method, null, null, map, keyCode, 0);
    }

    /**
     * Creates and maps action in component.
     *
     * @param component Root component.
     * @param target Target object that will be used for method invocation.
     * @param method Method's name.
     * @param map Map's name.
     * @param keyCode Key code.
     * @param modifiers Key code's modifiers.
     */
    public static void createActionMap(final JComponent component, final Object target, final String method, final String map, final int keyCode, final int modifiers) {
        createActionMap(component, target, method, null, null, map, keyCode, modifiers);
    }

    /**
     * Creates and maps action in panel.
     *
     * @param component Root component.
     * @param target Target object that will be used for method invocation.
     * @param method Method's name.
     * @param argClass Argument class (when not needed provide null).
     * @param arg Argument (when not needed provide null).
     * @param map Map's name.
     * @param keyCode Key code.
     */
    public static void createActionMap(final JComponent component, final Object target, final String method, final Class<?> argClass, final Object arg, final String map, final int keyCode) {
        createActionMap(component, target, method, argClass, arg, map, keyCode, 0);
    }

    /**
     * Creates and maps action in panel.
     *
     * @param component Root component.
     * @param target Target object that will be used for method invocation.
     * @param method Method's name.
     * @param argClass Argument class (when not needed provide null).
     * @param arg Argument (when not needed provide null).
     * @param map Map's name.
     * @param keyCode Key code.
     * @param modifiers Key code's modifiers.
     */
    public static void createActionMap(final JComponent component, final Object target, final String method, final Class<?> argClass, final Object arg, final String map, final int keyCode, final int modifiers) {
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if (argClass == null) {
                        target.getClass().getMethod(method).invoke(target);
                    }
                    else {
                        target.getClass().getMethod(method, argClass).invoke(target, arg);
                    }
                }
                catch (NoSuchMethodException exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
                catch (SecurityException exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
                catch (IllegalAccessException exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
                catch (IllegalArgumentException exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
                catch (InvocationTargetException exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
                catch (Exception exc) {
                    SLibUtils.printException(this.getClass().getName(), exc);
                }
            }
        };

        putActionMap(component, action, map, keyCode, modifiers);
    }

    public static String getLabelDatetime(final int guiType) {
        String label = "";

        switch (guiType) {
            case SGuiConsts.GUI_TYPE_DATE:
                label = "fecha";
                break;
            case SGuiConsts.GUI_TYPE_DATE_DATETIME:
                label = "fecha-hora";
                break;
            case SGuiConsts.GUI_TYPE_DATE_TIME:
                label = "hora";
                break;
            default:
                label = "?";
        }

        return label;
    }

    public static String getLabelName(final JLabel label) {
        return getLabelName(label.getText());
    }

    public static String getLabelName(final String label) {
        String name = label.toLowerCase();

        if (name.endsWith(":")) {
            name = name.substring(0, name.length() - 1);
        }
        else if (name.endsWith(":*")) {
            name = name.substring(0, name.length() - 2);
        }
        else if (name.endsWith(": *")) {
            name = name.substring(0, name.length() - 3);
        }

        return name;
    }

    public static void pickDate(final SGuiDatePicker picker, final Date date, final SGuiFieldDate field) {
        picker.resetPicker();
        picker.setOption(date);
        picker.setPickerVisible(true);

        if (picker.getPickerResult() == SGuiConsts.FORM_RESULT_OK) {
            field.setValue(picker.getOption());
            field.getComponent().requestFocus();
        }
    }

    private static SGuiValidation validateDateRange(final SGuiFieldDate fieldDateStart, final SGuiFieldDate fieldDateEnd, final boolean validateYears) {
        Date dateStart = fieldDateStart.getValue();
        Date dateEnd = fieldDateEnd.getValue();
        SGuiValidation validation = new SGuiValidation();

        if (dateStart == null) {
            validation.setMessage("No se ha especificado un valor para la fecha inicial.");
            validation.setComponent(fieldDateStart.getComponent());
        }
        else if (dateEnd == null) {
            validation.setMessage("No se ha especificado un valor para la fecha final.");
            validation.setComponent(fieldDateEnd.getComponent());
        }
        else if (!validateYears && SLibTimeUtils.digestYear(dateStart)[0] != SLibTimeUtils.digestYear(dateEnd)[0]) {
            validation.setMessage("El año de las fechas inicial y final debe ser el mismo.");
            validation.setComponent(fieldDateStart.getComponent());
        }
        else if (dateEnd.before(dateStart)) {
            validation.setMessage("La fecha inicial debe ser anterior a la fecha final.");
            validation.setComponent(fieldDateStart.getComponent());
        }

        return validation;
    }

    public static SGuiValidation validateDateRange(final SGuiFieldDate fieldDateStart, final SGuiFieldDate fieldDateEnd) {
        return validateDateRange(fieldDateStart, fieldDateEnd, true);
    }

    public static SGuiValidation validateDateRangeIgnoreYears(final SGuiFieldDate fieldDateStart, final SGuiFieldDate fieldDateEnd) {
        return validateDateRange(fieldDateStart, fieldDateEnd, false);
    }

    public static boolean computeValidation(final SGuiClient client, final SGuiValidation validation) {
        if (!validation.isValid()) {
            client.showMsgBoxWarning(validation.getMessage());

            if (validation.getComponent() != null) {
                if (validation.getTabbedPane() != null && validation.getTab() != -1) {
                    validation.getTabbedPane().setSelectedIndex(validation.getTab());
                }
                validation.getComponent().requestFocus();
            }
        }

        return validation.isValid();
    }
}
