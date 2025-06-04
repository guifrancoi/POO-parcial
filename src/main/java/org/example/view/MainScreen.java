// MainScreen.java
package org.example.view;

import org.example.model.entity.Transacao;
import org.example.model.entity.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainScreen extends JFrame {

    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JLabel saldo;
    private JLabel totalReceita;
    private JLabel totalDespesa;
    private JButton addButton, editButton, deleteButton, filterButton;
    private Usuario usuario; // Armazena o usuário logado

    public MainScreen(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Gerenciador Financeiro");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Data", "Categoria", "Descrição", "Valor", "Tipo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);

        JPanel summaryPanel = new JPanel(new GridLayout(1, 3));
        saldo = new JLabel("Saldo: R$ 0,00");
        totalReceita = new JLabel("Receitas: R$ 0,00");
        totalDespesa = new JLabel("Despesas: R$ 0,00");
        summaryPanel.add(saldo);
        summaryPanel.add(totalReceita);
        summaryPanel.add(totalDespesa);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Excluir");
        filterButton = new JButton("Filtrar");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(filterButton);

        summaryPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(new JScrollPane(transactionTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(summaryPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
    }

    // Métodos públicos para acesso ao controller
    public void setAddButtonListener(ActionListener l) {
        addButton.addActionListener(l);
    }

    public void setEditButtonListener(ActionListener l) {
        editButton.addActionListener(l);
    }

    public void setDeleteButtonListener(ActionListener l) {
        deleteButton.addActionListener(l);
    }

    public void setFilterButtonListener(ActionListener l) {
        filterButton.addActionListener(l);
    }

    public int getSelectedRow() {
        return transactionTable.getSelectedRow();
    }

    public Long getIdFromRow(int row) {
        return (Long) tableModel.getValueAt(row, 0);
    }

    public void updateTableWithTransacoes(List<Transacao> transacoes) {
        tableModel.setRowCount(0);
        for (Transacao t : transacoes) {
            tableModel.addRow(new Object[]{t.getIdTransacao(), t.getData(), t.getCategoria(), t.getDescricao(), t.getValor(), t.getTipo()});
        }
        updateSummary();
    }

    public void updateSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            double value = Double.parseDouble(tableModel.getValueAt(i, 4).toString());
            String type = tableModel.getValueAt(i, 5).toString();
            if ("Receita".equals(type)) totalIncome += value;
            else totalExpense += value;
        }

        double balance = totalIncome - totalExpense;
        saldo.setText(String.format("Saldo: R$ %.2f", balance));
        totalReceita.setText(String.format("Receitas: R$ %.2f", totalIncome));
        totalDespesa.setText(String.format("Despesas: R$ %.2f", totalExpense));
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public TransacaoForm showAddForm() {
        TransacaoForm form = new TransacaoForm(this);
        form.setVisible(true);
        return form;
    }

    public TransacaoForm showEditForm(int row, Transacao t) {
        TransacaoForm form = new TransacaoForm(this, row, t.getData(), t.getCategoria(), t.getDescricao(), t.getValor(), t.getTipo());
        form.setVisible(true);
        return form;
    }
}
