package org.example.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class TransacaoFilterDialog extends JDialog {

    private DatePicker datePicker;
    private JComboBox<String> categoryBox;
    private JComboBox<String> typeBox;
    private boolean confirmed = false;

    public TransacaoFilterDialog(JFrame parent) {
        super(parent, "Filtrar Transações", true);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Data (opcional):"));
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        datePicker = new DatePicker(settings);
        add(datePicker);

        add(new JLabel("Categoria:"));
        categoryBox = new JComboBox<>(new String[]{"", "Alimentação", "Transporte", "Lazer", "Saúde", "Outros"});
        add(categoryBox);

        add(new JLabel("Tipo:"));
        typeBox = new JComboBox<>(new String[]{"", "Receita", "Despesa"});
        add(typeBox);

        JButton filterButton = new JButton("Aplicar Filtro");
        filterButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        add(filterButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    // Retorna data como String no formato dd/MM/yyyy, ou "" se não houver seleção
    public String getData() {
        if (datePicker.getDate() == null) {
            return "";
        }
        return datePicker.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getCategoria() {
        return (String) categoryBox.getSelectedItem();
    }

    public String getTipo() {
        return (String) typeBox.getSelectedItem();
    }
}
