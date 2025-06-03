package org.example.view;

import org.example.controller.MainScreenController;
import org.example.model.dao.TransacaoDAO;
import org.example.model.dao.TransacaoDAOImpl;
import org.example.model.entity.Transacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainScreen extends JFrame {

    private JTable transactionTable;
    private DefaultTableModel tableModel;
    private JLabel saldo;
    private JLabel totalReceita;
    private JLabel totalDespesa;
    private final MainScreenController controller = new MainScreenController();
    private final TransacaoDAO transacaoDAO = new TransacaoDAOImpl();

    public MainScreen() {
        setTitle("Gerenciador Financeiro");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Criar modelo da tabela
        String[] columnNames = {"ID", "Data", "Categoria", "Descrição", "Valor", "Tipo"};
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
            TransacaoFilterDialog filterDialog = new TransacaoFilterDialog(this);
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
        loadAllTransacoes();
        setVisible(true);
    }

    public void loadAllTransacoes() {
        List<Transacao> transacoes = controller.buscarTodasTransacoes();
        updateTableWithFilteredTransacoes(transacoes);
    }

    public void addTransactionToTable(String date, String category, String description, double value, String type) {
        Transacao transacao = new Transacao(date, category, description, value, type);
        controller.salvarTransacao(transacao);
        loadAllTransacoes();
    }

    public void updateTransaction(int rowIndex, String date, String category, String description, double value, String type) {
        Long id = getIdFromRow(rowIndex);
        Transacao transacao = new Transacao(id, date, category, description, value, type);
        controller.atualizarTransacao(transacao);
        loadAllTransacoes();
    }

    private Long getIdFromRow(int row) {
        return (Long) tableModel.getValueAt(row, 0);
    }

    // Metodo para adicionar transações
    private void addTransaction() {
        TransacaoForm form = new TransacaoForm(this);
        form.setVisible(true);
    }

    // Metodo para editar transações
    private void editTransaction() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object dataObj = tableModel.getValueAt(selectedRow, 1);
        Object categoriaObj = tableModel.getValueAt(selectedRow, 2);
        Object descricaoObj = tableModel.getValueAt(selectedRow, 3);
        Object valorObj = tableModel.getValueAt(selectedRow, 4);
        Object tipoObj = tableModel.getValueAt(selectedRow, 5);

        String date = dataObj != null ? dataObj.toString() : "";
        String category = categoriaObj != null ? categoriaObj.toString() : "";
        String description = descricaoObj != null ? descricaoObj.toString() : "";
        double value = valorObj instanceof Double ? (Double) valorObj : 0.0;
        String type = tipoObj != null ? tipoObj.toString() : "";

        TransacaoForm form = new TransacaoForm(this, selectedRow, date, category, description, value, type);
        form.setVisible(true);
    }


    private void deleteTransaction() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Long id = getIdFromRow(selectedRow);
        controller.excluirTransacaoPorId(id);
        loadAllTransacoes();
    }


    // Atualizar os valores do resumo financeiro (RF003)
    public void updateSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            // Coluna 3: valor (Double ou String)
            Object valueObj = tableModel.getValueAt(i, 3);
            double value;

            if (valueObj instanceof Double) {
                value = (Double) valueObj;
            } else if (valueObj instanceof String) {
                try {
                    value = Double.parseDouble((String) valueObj);
                } catch (NumberFormatException e) {
                    value = 0;
                }
            } else {
                value = 0;
            }

            // Coluna 4: tipo (Receita ou Despesa)
            Object typeObj = tableModel.getValueAt(i, 4);
            String type = "";

            if (typeObj instanceof String) {
                type = (String) typeObj;
            } else {
                type = String.valueOf(typeObj);
            }

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


    public void updateTableWithFilteredTransacoes(List<Transacao> transacoes) {
        tableModel.setRowCount(0);
        for (Transacao t : transacoes) {
            tableModel.addRow(new Object[]{t.getId(), t.getData(), t.getCategoria(), t.getDescricao(), t.getValor(), t.getTipo()});
        }
        updateSummary();
    }

}
