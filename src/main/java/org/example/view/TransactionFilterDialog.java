package org.example.view;

import org.example.util.DateFieldFactory;
import org.example.model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        String data = dateField.getText().trim();
        String categoria = (String) categoryBox.getSelectedItem();
        String tipo = (String) typeBox.getSelectedItem();

        // Verifica se todos os campos estão vazios ou em branco
        boolean isDataVazia = data.isEmpty() || data.contains("_");
        boolean isCategoriaVazia = categoria == null || categoria.isBlank();
        boolean isTipoVazio = tipo == null || tipo.isBlank();

        List<Transaction> todas = mainScreen.getAllTransactions();

        if (isDataVazia && isCategoriaVazia && isTipoVazio) {
            mainScreen.updateTableWithFilteredTransactions(todas);
            dispose(); // fecha o dialog
            return;
        }

        // Aplica os filtros normalmente
        List<Transaction> filtradas = new ArrayList<>();
        for (Transaction t : todas) {
            boolean corresponde = true;

            if (!isDataVazia && !t.getDate().equals(data)) {
                corresponde = false;
            }

            if (!isCategoriaVazia && !t.getCategory().equals(categoria)) {
                corresponde = false;
            }

            if (!isTipoVazio && !t.getType().equals(tipo)) {
                corresponde = false;
            }

            if (corresponde) {
                filtradas.add(t);
            }
        }

        mainScreen.updateTableWithFilteredTransactions(filtradas);
        dispose(); // fecha o dialog
    }

}
