package org.example;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class DateFieldFactory {

    public static JFormattedTextField createDateField() {
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            return new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField(); // fallback
        }
    }

}
