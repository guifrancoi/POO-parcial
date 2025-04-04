package org.example.view;

import org.example.util.DateFieldFactory;

import javax.swing.*;
import java.awt.*;

public class TransactionForm extends JFrame {

    private JTextField dateField, descriptionField, valueField;
    private JComboBox<String> categoryComboBox, typeComboBox;
    private MainScreen mainScreen;
    private int editingRow = -1;

    public TransactionForm(MainScreen mainScreen) {
        this(mainScreen, -1, "", "", "", 0.0, "Receita");
    }

    public TransactionForm(MainScreen mainScreen, int editingRow, String date, String category, String description, double value, String type) {
        this.mainScreen = mainScreen;
        this.editingRow = editingRow;

        setTitle(editingRow == -1 ? "Nova Transação" : "Editar Transação");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Data:"));
        dateField = DateFieldFactory.createDateField();
        add(dateField);

        add(new JLabel("Categoria:"));
        categoryComboBox = new JComboBox<>(new String[]{"Alimentação", "Transporte", "Lazer", "Saúde", "Outros"});
        categoryComboBox.setSelectedItem(category);
        add(categoryComboBox);

        add(new JLabel("Descrição:"));
        descriptionField = new JTextField(description);
        add(descriptionField);

        add(new JLabel("Valor:"));
        valueField = new JTextField(String.valueOf(value));
        add(valueField);

        add(new JLabel("Tipo:"));
        typeComboBox = new JComboBox<>(new String[]{"Receita", "Despesa"});
        typeComboBox.setSelectedItem(type);
        add(typeComboBox);

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(e -> saveTransaction());
        add(saveButton);
    }

    private void saveTransaction() {
        String date = dateField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String description = descriptionField.getText();
        double value = Double.parseDouble(valueField.getText());
        String type = (String) typeComboBox.getSelectedItem();

        if (editingRow == -1) {
            mainScreen.addTransactionToTable(date, category, description, value, type);
        } else {
            mainScreen.updateTransaction(editingRow, date, category, description, value, type);
        }

        dispose();
    }
}
