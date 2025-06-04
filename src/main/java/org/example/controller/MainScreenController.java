package org.example.controller;

import org.example.model.dao.TransacaoDAO;
import org.example.model.dao.TransacaoDAOImpl;
import org.example.model.entity.Transacao;
import org.example.model.entity.Usuario;
import org.example.view.MainScreen;
import org.example.view.TransacaoFilterDialog;
import org.example.view.TransacaoForm;

import java.util.List;

public class MainScreenController {

    private final MainScreen view;
    private final TransacaoDAO transacaoDAO;
    private final Usuario usuarioLogado;

    public MainScreenController(MainScreen view, Usuario usuarioLogado) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
        this.transacaoDAO = new TransacaoDAOImpl();

        this.view.setAddButtonListener(e -> {
            onAdd();
        });
        this.view.setEditButtonListener(e -> onEdit());
        this.view.setDeleteButtonListener(e -> onDelete());
        this.view.setFilterButtonListener(e -> onFilter());

        carregarTransacoes();
    }

    private void carregarTransacoes() {
        System.out.println("dentro do carregarTransacoes");
        List<Transacao> transacoes = transacaoDAO.findByUsuario(usuarioLogado);
        view.updateTableWithTransacoes(transacoes);
    }

    private void onAdd() {
        TransacaoForm form = new TransacaoForm(view);
        form.setVisible(true);
        System.out.println("dentro do onAdd");

        if (form.isConfirmed()) {
            System.out.println("Confirmed");
            Transacao transacaoNova = form.getTransacaoFromForm();
            transacaoNova.setUsuario(usuarioLogado);
            transacaoDAO.save(transacaoNova);
            carregarTransacoes();
        }
    }

    private void onEdit() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.showError("Selecione uma transação para editar.");
            return;
        }

        Long id = view.getIdFromRow(selectedRow);
        Transacao transacaoExistente = transacaoDAO.findById(id);
        if (transacaoExistente == null || !transacaoExistente.getUsuario().equals(usuarioLogado)) {
            view.showError("Transação não encontrada ou pertence a outro usuário.");
            return;
        }

        TransacaoForm form = new TransacaoForm(
                view,
                transacaoExistente.getIdTransacao(),
                transacaoExistente.getData(),
                transacaoExistente.getCategoria(),
                transacaoExistente.getDescricao(),
                transacaoExistente.getValor(),
                transacaoExistente.getTipo()
        );
        form.setVisible(true);

        if (form.isConfirmed()) {
            Transacao atualizada = form.getTransacaoFromForm();
            atualizada.setId(id);
            atualizada.setUsuario(usuarioLogado);
            transacaoDAO.update(atualizada);
            carregarTransacoes();
        }
    }

    private void onDelete() {
        int selectedRow = view.getSelectedRow();
        if (selectedRow == -1) {
            view.showError("Selecione uma transação para excluir.");
            return;
        }

        Long id = view.getIdFromRow(selectedRow);
        Transacao existente = transacaoDAO.findById(id);
        if (existente == null || !existente.getUsuario().equals(usuarioLogado)) {
            view.showError("Transação não encontrada ou pertence a outro usuário.");
            return;
        }

        transacaoDAO.deleteById(id);
        carregarTransacoes();
    }

    private void onFilter() {
        TransacaoFilterDialog dialog = new TransacaoFilterDialog(view);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) return;

        String data = dialog.getData();
        String categoria = dialog.getCategoria();
        String tipo = dialog.getTipo();

        List<Transacao> resultados = transacaoDAO.buscarPorFiltros(usuarioLogado, data, categoria, tipo);
        view.updateTableWithTransacoes(resultados);
    }
}
