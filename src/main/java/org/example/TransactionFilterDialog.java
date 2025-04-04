package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionFilterDialog extends JDialog {

    private JTextField dateField;
    private JComboBox<String> categoryBox;
    private JComboBox<String> typeBox;
    private MainScreen mainScreen;

    public TransactionFilterDialog(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Filtrar Transações");
        setSize(300, 200);
        setLocationRelativeTo(mainScreen);
        setModal(true);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Data (opcional):"));
        dateField = DateFieldFactory.createDateField();
        add(dateField);

        add(new JLabel("Categoria:"));
        categoryBox = new JComboBox<>(new String[]{"", "Alimentação", "Transporte", "Lazer", "Saúde", "Outros"});
        add(categoryBox);

        add(new JLabel("Tipo:"));
        typeBox = new JComboBox<>(new String[]{"", "Receita", "Despesa"});
        add(typeBox);

        JButton filterButton = new JButton("Aplicar Filtro");
        add(filterButton);

        filterButton.addActionListener(e -> applyFilter());
    }

    private void applyFilter() {
        String date = dateField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();

        List<Transaction> filtered = mainScreen.getAllTransactions().stream()
                .filter(t -> (date.isEmpty() || t.getDate().equalsIgnoreCase(date)) &&
                        (category.isEmpty() || t.getCategory().equalsIgnoreCase(category)) &&
                        (type.isEmpty() || t.getType().equalsIgnoreCase(type)))
                .collect(Collectors.toList());

        mainScreen.updateTableWithFilteredTransactions(filtered);
        dispose();
    }
}
