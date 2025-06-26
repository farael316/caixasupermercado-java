package com.supermercado.view;

import com.supermercado.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CaixaMercadoGUI extends JFrame {
    private Estoque estoque;
    private Carrinho carrinho;
    private JTextField txtCodigo;
    private JTextField txtQuantidade;
    private JTable tblCarrinho;
    private DefaultTableModel modelCarrinho;
    private JLabel lblTotal;
    private JTextArea txtAreaProdutos;
    private DecimalFormat df;

    public CaixaMercadoGUI() {
        estoque = new Estoque();
        carrinho = new Carrinho();
        df = new DecimalFormat("#,##0.00");
        
        configurarJanela();
        criarComponentes();
        organizarLayout();
        configurarEventos();
        atualizarListaProdutos();
    }

    private void configurarJanela() {
        setTitle("Caixa de Supermercado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {
        // Painel de entrada de produtos
        JPanel painelEntrada = new JPanel();
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Adicionar Produto"));
        painelEntrada.setLayout(new GridLayout(3, 2, 5, 5));

        painelEntrada.add(new JLabel("Código do Produto:"));
        txtCodigo = new JTextField();
        painelEntrada.add(txtCodigo);
        
        // Adiciona ação de Enter no campo de código
        txtCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        painelEntrada.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField("1");
        painelEntrada.add(txtQuantidade);

        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnAdicionar.setBackground(new Color(46, 204, 113));
        btnAdicionar.setForeground(Color.WHITE);
        painelEntrada.add(btnAdicionar);

        JButton btnLimpar = new JButton("Limpar Carrinho");
        btnLimpar.setBackground(new Color(231, 76, 60));
        btnLimpar.setForeground(Color.WHITE);
        painelEntrada.add(btnLimpar);

        // Tabela do carrinho
        String[] colunas = {"Produto", "Quantidade", "Preço Unit.", "Subtotal"};
        modelCarrinho = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCarrinho = new JTable(modelCarrinho);
        tblCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollCarrinho = new JScrollPane(tblCarrinho);
        scrollCarrinho.setBorder(BorderFactory.createTitledBorder("Carrinho de Compras"));

        // Painel de total e finalização
        JPanel painelTotal = new JPanel();
        painelTotal.setBorder(BorderFactory.createTitledBorder("Resumo da Compra"));
        painelTotal.setLayout(new GridLayout(2, 2, 10, 5));

        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        painelTotal.add(lblTotal);

        JButton btnFinalizar = new JButton("Finalizar Compra");
        btnFinalizar.setBackground(new Color(52, 152, 219));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        painelTotal.add(btnFinalizar);

        // Lista de produtos disponíveis
        txtAreaProdutos = new JTextArea();
        txtAreaProdutos.setEditable(false);
        txtAreaProdutos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollProdutos = new JScrollPane(txtAreaProdutos);
        scrollProdutos.setBorder(BorderFactory.createTitledBorder("Produtos Disponíveis"));
        scrollProdutos.setPreferredSize(new Dimension(300, 400));

        // Configurar eventos
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCarrinho();
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });

        // Adicionar componentes ao frame
        setLayout(new BorderLayout(10, 10));
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollCarrinho, BorderLayout.CENTER);
        add(painelTotal, BorderLayout.SOUTH);
        add(scrollProdutos, BorderLayout.EAST);
    }

    private void organizarLayout() {
        // Layout já configurado no método criarComponentes
    }

    private void configurarEventos() {
        // Eventos já configurados no método criarComponentes
    }

    private void atualizarListaProdutos() {
        StringBuilder sb = new StringBuilder();
        sb.append("CÓDIGO | PRODUTO                    | PREÇO    | ESTOQUE\n");
        sb.append("--------|----------------------------|----------|--------\n");
        
        for (Produto produto : estoque.listarProdutos()) {
            sb.append(String.format("%-7s | %-26s | R$ %-6s | %-7d\n",
                    produto.getCodigo(),
                    produto.getNome(),
                    df.format(produto.getPreco()),
                    produto.getQuantidadeEstoque()));
        }
        
        txtAreaProdutos.setText(sb.toString());
    }

    private void adicionarProduto() {
        try {
            String codigo = txtCodigo.getText().trim();
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o código do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto produto = estoque.buscarPorCodigo(codigo);
            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!estoque.verificarDisponibilidade(codigo, quantidade)) {
                JOptionPane.showMessageDialog(this, "Quantidade insuficiente em estoque!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            carrinho.adicionarItem(produto, quantidade);
            atualizarTabelaCarrinho();
            atualizarTotal();
            limparCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaCarrinho() {
        modelCarrinho.setRowCount(0);
        
        for (ItemCompra item : carrinho.getItens()) {
            Object[] row = {
                item.getProduto().getNome(),
                item.getQuantidade(),
                "R$ " + df.format(item.getProduto().getPreco()),
                "R$ " + df.format(item.getSubtotal())
            };
            modelCarrinho.addRow(row);
        }
    }

    private void atualizarTotal() {
        lblTotal.setText("Total: R$ " + df.format(carrinho.getTotal()));
    }

    private void limparCarrinho() {
        carrinho.limparCarrinho();
        atualizarTabelaCarrinho();
        atualizarTotal();
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtQuantidade.setText("1");
        txtCodigo.requestFocus();
    }

    private void finalizarCompra() {
        if (carrinho.estaVazio()) {
            JOptionPane.showMessageDialog(this, "Carrinho vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Atualizar estoque
        for (ItemCompra item : carrinho.getItens()) {
            estoque.decrementarEstoque(item.getProduto().getCodigo(), item.getQuantidade());
        }

        // Mostrar recibo
        mostrarRecibo();

        // Limpar carrinho
        limparCarrinho();
        atualizarListaProdutos();
    }

    private void mostrarRecibo() {
        StringBuilder recibo = new StringBuilder();
        recibo.append("=== RECIBO DE COMPRA ===\n\n");
        
        for (ItemCompra item : carrinho.getItens()) {
            recibo.append(String.format("%s x%d - R$ %s\n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    df.format(item.getSubtotal())));
        }
        
        recibo.append("\n" + "=".repeat(30) + "\n");
        recibo.append(String.format("TOTAL: R$ %s\n", df.format(carrinho.getTotal())));
        recibo.append("=".repeat(30) + "\n");
        recibo.append("Obrigado pela compra!\n");
        recibo.append("Volte sempre!");

        JTextArea areaRecibo = new JTextArea(recibo.toString());
        areaRecibo.setEditable(false);
        areaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JOptionPane.showMessageDialog(this, new JScrollPane(areaRecibo), 
                "Recibo de Compra", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CaixaMercadoGUI().setVisible(true);
            }
        });
    }
} 