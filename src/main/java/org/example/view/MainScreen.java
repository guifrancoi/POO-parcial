package org.example.view;

import org.example.model.Transaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {

    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JLabel saldo;
    private JLabel totalReceita;
    private JLabel totalDespesa;
    private List<Transaction> transactions = new ArrayList<>();

    public MainScreen() {
        setTitle("Gerenciador Financeiro");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Criar modelo da tabela
        String[] columnNames = {"Data", "Categoria", "Descrição", "Valor", "Tipo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);

        // Painel superior com resumo financeiro
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3));
        saldo = new JLabel("Saldo: R$ 0,00");
        totalReceita = new JLabel("Receitas: R$ 0,00");
        totalDespesa = new JLabel("Despesas: R$ 0,00");
        summaryPanel.add(saldo);
        summaryPanel.add(totalReceita);
        summaryPanel.add(totalDespesa);

        // Painel inferior com botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");
        JButton filterButton = new JButton("Filtrar");
        filterButton.addActionListener(e -> {
            TransactionFilterDialog filterDialog = new TransactionFilterDialog(this); // Aqui this é MainScreen
            filterDialog.setVisible(true);
        });

        addButton.addActionListener(e -> addTransaction());
        editButton.addActionListener(e -> editTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(filterButton);

        // Estilização do painel de resumo financeiro com margem
        summaryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Estilização do painel de botões com margem
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(new JScrollPane(transactionTable), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(summaryPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);


        add(panel);
        setVisible(true);
    }

    // Método para adicionar transações
    private void addTransaction() {
        TransactionForm form = new TransactionForm(this);
        form.setVisible(true);
    }

    // Método para editar transações
    private void editTransaction() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String date = (String) tableModel.getValueAt(selectedRow, 0);
        String category = (String) tableModel.getValueAt(selectedRow, 1);
        String description = (String) tableModel.getValueAt(selectedRow, 2);
        double value = (Double) tableModel.getValueAt(selectedRow, 3);
        String type = (String) tableModel.getValueAt(selectedRow, 4);

        TransactionForm form = new TransactionForm(this, selectedRow, date, category, description, value, type);
        form.setVisible(true);
    }

    // Método para excluir transações
    private void deleteTransaction() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            updateSummary();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Atualizar os valores do resumo financeiro (RF003)
    public void updateSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double value = (Double) tableModel.getValueAt(i, 3);
            String type = (String) tableModel.getValueAt(i, 4);

            if (type.equals("Receita")) {
                totalIncome += value;
            } else {
                totalExpense += value;
            }
        }

        double balance = totalIncome - totalExpense;
        saldo.setText(String.format("Saldo: R$ %.2f", balance));
        totalReceita.setText(String.format("Receitas: R$ %.2f", totalIncome));
        totalDespesa.setText(String.format("Despesas: R$ %.2f", totalExpense));
    }

    public void addTransactionToTable(String date, String category, String description, double value, String type) {
        Transaction transaction = new Transaction(date, category, description, value, type);
        transactions.add(transaction);
        tableModel.addRow(new Object[]{date, category, description, value, type});
        updateSummary();
    }

    // Atualiza uma transação existente na tabela
    public void updateTransaction(int rowIndex, String date, String category, String description, double value, String type) {
        tableModel.setValueAt(date, rowIndex, 0);
        tableModel.setValueAt(category, rowIndex, 1);
        tableModel.setValueAt(description, rowIndex, 2);
        tableModel.setValueAt(value, rowIndex, 3);
        tableModel.setValueAt(type, rowIndex, 4);
        updateSummary(); // Atualiza o resumo financeiro após a edição
    }

    public void updateTableWithFilteredTransactions(List<Transaction> filtered) {
        tableModel.setRowCount(0); // Limpa a tabela
        for (Transaction t : filtered) {
            tableModel.addRow(new Object[]{t.getDate(), t.getCategory(), t.getDescription(), t.getValue(), t.getType()});
        }
        updateSummary();
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

}
