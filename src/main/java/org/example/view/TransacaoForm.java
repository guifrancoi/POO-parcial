package org.example.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.example.model.entity.Transacao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TransacaoForm extends JDialog   {

    private DatePicker datePicker;
    private JTextField descriptionField, valueField;
    private JComboBox<String> categoryComboBox, typeComboBox;
    private boolean confirmed = false;
    private Long transacaoId = null;

    public TransacaoForm(JFrame parent) {
        this(parent, null, null, "", "", 0.0, "Receita");
    }

    public TransacaoForm(JFrame parent, int editingRow, LocalDate date, String category, String description, double value, String type) {
        super(parent, true);
    }

    public TransacaoForm(JFrame parent, Long id, LocalDate date, String category, String description, double value, String type) {
        super(parent, true);
        this.transacaoId = id;

        setTitle(id == null ? "Nova Transação" : "Editar Transação");
        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Data:"));
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("dd/MM/yyyy");
        datePicker = new DatePicker(settings);
        if (date != null) datePicker.setDate(date);
        add(datePicker);

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
        saveButton.addActionListener(e -> onSave());
        add(saveButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    private void onSave() {
        LocalDate selectedDate = datePicker.getDate();

        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma data válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(valueField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        confirmed = true;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Transacao getTransacaoFromForm() {
        Transacao transacao = new Transacao();
        transacao.setId(transacaoId);
        transacao.setData(datePicker.getDate());
        transacao.setCategoria((String) categoryComboBox.getSelectedItem());
        transacao.setDescricao(descriptionField.getText());
        transacao.setValor(Double.parseDouble(valueField.getText()));
        transacao.setTipo((String) typeComboBox.getSelectedItem());
        return transacao;
    }
}
