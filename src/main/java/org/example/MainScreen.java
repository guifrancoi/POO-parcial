package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainScreen extends JFrame {

    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JLabel totalBalanceLabel;
    private JLabel totalIncomeLabel;
    private JLabel totalExpenseLabel;

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
        totalBalanceLabel = new JLabel("Saldo: R$ 0,00");
        totalIncomeLabel = new JLabel("Receitas: R$ 0,00");
        totalExpenseLabel = new JLabel("Despesas: R$ 0,00");
        summaryPanel.add(totalBalanceLabel);
        summaryPanel.add(totalIncomeLabel);
        summaryPanel.add(totalExpenseLabel);

        // Painel inferior com botões
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");
        JButton filterButton = new JButton("Filtrar");

        addButton.addActionListener(e -> addTransaction());
        editButton.addActionListener(e -> editTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());
        filterButton.addActionListener(e -> filterTransactions());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(filterButton);

        // Adicionar componentes ao painel principal
        panel.add(summaryPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(transactionTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

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

    // Método para filtrar transações (RF002)
    private void filterTransactions() {
        JOptionPane.showMessageDialog(this, "Funcionalidade de filtro em desenvolvimento.");
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
        totalBalanceLabel.setText(String.format("Saldo: R$ %.2f", balance));
        totalIncomeLabel.setText(String.format("Receitas: R$ %.2f", totalIncome));
        totalExpenseLabel.setText(String.format("Despesas: R$ %.2f", totalExpense));
    }

    // Adicionar nova transação à tabela
    public void addTransactionToTable(String date, String category, String description, double value, String type) {
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

}
