package org.example.view;

import org.example.model.dao.TransacaoDAO;
import org.example.util.DateFieldFactory;
import org.example.model.entity.Transacao;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoFilterDialog extends JDialog {

    private JTextField dateField;
    private JComboBox<String> categoryBox;
    private JComboBox<String> typeBox;
    private MainScreen mainScreen;
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();

    public TransacaoFilterDialog(MainScreen mainScreen) {
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

        boolean isDataVazia = data.isEmpty() || data.contains("_");
        boolean isCategoriaVazia = categoria == null || categoria.isBlank();
        boolean isTipoVazio = tipo == null || tipo.isBlank();

        List<Transacao> todas = transacaoDAO.findAll();

        if (isDataVazia && isCategoriaVazia && isTipoVazio) {
            mainScreen.updateTableWithFilteredTransacoes(todas);
            dispose();
            return;
        }

        List<Transacao> filtradas = new ArrayList<>();
        for (Transacao t : todas) {
            boolean corresponde = true;

            if (!isDataVazia && !t.getData().equals(data)) {
                corresponde = false;
            }

            if (!isCategoriaVazia && !t.getCategoria().equals(categoria)) {
                corresponde = false;
            }

            if (!isTipoVazio && !t.getTipo().equals(tipo)) {
                corresponde = false;
            }

            if (corresponde) {
                filtradas.add(t);
            }
        }

        mainScreen.updateTableWithFilteredTransacoes(filtradas);
        dispose();
    }
}
