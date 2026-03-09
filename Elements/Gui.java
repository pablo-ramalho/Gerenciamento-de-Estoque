package Elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.util.List;
import java.util.ArrayList;

public class Gui extends JFrame{

    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JPanel buttonSection;
    private JButton addButton;
    private JButton removeButton;
    private JButton importButton;
    private Font fonteBotao;
    private Font fonteCampo;
    private Font fonteTabela;
    private Color corBotao;

    private JPanel textFieldSection;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField weightField;
    private JTextField stockField;

    private JPanel productGridSection;
    private List<Produto> listaDeProdutos;
    private Tabela tableModel;
    private JTable productGrid;
    private JScrollPane scrollableTable;
    
    public Gui(){
        gbc = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        fonteBotao = new Font("Arial", Font.BOLD, 36);
        fonteCampo = new Font("Arial", Font.PLAIN, 24);
        fonteTabela = new Font("Arial", Font.BOLD, 20);
        corBotao = new Color(64, 224, 208);
        leftPanel = new JPanel(new GridLayout(2, 1));
        rightPanel = new JPanel(new BorderLayout());

        setSize(1520, 1000);
        setLayout(new BorderLayout());
        setTitle("Gerenciador de estoque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //  CONFIGURAÇÃO DOS PAINÉIS
        this.configurarPaineis();
        leftPanel.add(buttonSection, BorderLayout.NORTH);
        leftPanel.add(textFieldSection, BorderLayout.CENTER);
        rightPanel.add(productGridSection, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // CONFFIGURAÇÃO DOS COMPONENTES INTERNOS
        
        //CONFIGURAÇÃO DOS BOTÕES
        addButton = this.configurarBotao("Adicionar", 0);
        buttonSection.add(addButton, gbc);

        removeButton = this.configurarBotao("Remover", 1);
        buttonSection.add(removeButton, gbc);
        
        importButton = this.configurarBotao("Importar", 2);
        buttonSection.add(importButton, gbc);

        // CONFIGURAÇÃO DOS CAMPOS DE TEXTO
        nameField = this.configurarCampo("Nome do produto", 0);
        textFieldSection.add(nameField, gbc);

        priceField = this.configurarCampo("Preço unitário", 1);
        textFieldSection.add(priceField, gbc);

        weightField = this.configurarCampo("Peso por unidade", 2);
        textFieldSection.add(weightField, gbc);
        
        stockField = this.configurarCampo("Estoque disponível", 3);
        textFieldSection.add(stockField, gbc);
        
        scrollableTable = this.configurarTabela();
        add(scrollableTable);

        // TRATAMENTO DE EVENTOS
        addButton.addActionListener(e -> {
            System.out.println("Adicionar clicado");
            this.adicionarProduto();

        });

        removeButton.addActionListener(e -> {
           System.out.println("Remover clicado");
           this.removerProduto();
            
        });

        importButton.addActionListener(e -> {
            System.out.println("Importar clicado");
            this.importarDados();

        });

        setVisible(true);

    }

    private void configurarPaineis(){
        buttonSection = new JPanel(gridBagLayout);
        gbc.weightx = 0.25;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;

        textFieldSection = new JPanel(gridBagLayout);
        textFieldSection.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 2));
        gbc.weightx = gbc.weightx;
        gbc.weighty = 1.0 - gbc.weighty;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        productGridSection = new JPanel(new FlowLayout());
        productGridSection.setBackground(Color.BLACK);
        gbc.weightx = 1.0 - gbc.weightx;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
    }
    
    public JButton configurarBotao(String buttonLabel, int gridy){
        JButton button;

        button = new JButton(buttonLabel);
        button.setFont(fonteBotao);
        button.setBackground(corBotao);

        gbc.weighty = 0.33;
        gbc.gridx = 0;
        gbc.gridy = gridy;
        
        gbc.fill = GridBagConstraints.BOTH;

        return button;

    }
    
    public JTextField configurarCampo(String placeHolderText, int gridy){
        JTextField field;
        field = new JTextField();
        field.setFont(fonteCampo);
        configurarFocoDoCampo(placeHolderText, field);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.fill = GridBagConstraints.BOTH;

        return field;

    }

    private void configurarFocoDoCampo(String placeHolder, JTextField campo){
        campo.setText(placeHolder);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                if(campo.getText().isEmpty()){
                    campo.setText(placeHolder);
                    campo.setForeground(Color.GRAY);
                
                }

            }

        });

        campo.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                
                // IMPEDE QUE O USUÁRIO APAGUE O PLACEHOLDER
                if(campo.getText().equals(placeHolder) && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE))
                    e.consume();

            }

            @Override
            public void keyTyped(KeyEvent e){

                if(campo.getText().equals(placeHolder)){
                    campo.setText("");
                    campo.setForeground(Color.BLACK);

                }

            }

        });

    }

    private void resetarCampos(){
        nameField.setText("Nome do produto");
        priceField.setText("Preço unitário");
        weightField.setText("Peso por unidade");
        stockField.setText("Estoque disponível");
        
        nameField.setForeground(Color.GRAY);
        priceField.setForeground(Color.GRAY);
        weightField.setForeground(Color.GRAY);
        stockField.setForeground(Color.GRAY);

    }

    private JScrollPane configurarTabela(){
        JScrollPane scrollableTable;

        listaDeProdutos = new ArrayList<>();
        tableModel = new Tabela(listaDeProdutos);
        productGrid = new JTable(tableModel);

        productGrid.getColumnModel().getColumn(0).setPreferredWidth(700);
        productGrid.getColumnModel().getColumn(1).setPreferredWidth(10);
        productGrid.getColumnModel().getColumn(2).setPreferredWidth(10);
        productGrid.getColumnModel().getColumn(3).setPreferredWidth(10);

        productGrid.getTableHeader().setReorderingAllowed(false);
        productGrid.getTableHeader().setForeground(Color.WHITE);
        productGrid.getTableHeader().setBackground(new Color(50, 50, 50));
        productGrid.getTableHeader().setFont(fonteTabela);

        scrollableTable = new JScrollPane(productGrid);

        return scrollableTable;

    }

    private void adicionarProduto(){
        Produto novoProduto;

        try{    
            novoProduto = new Produto(nameField.getText(), Float.parseFloat(priceField.getText()), Integer.parseInt(weightField.getText()), Integer.parseInt(stockField.getText()));

            if(!(nameField.getText().isEmpty()) && !(priceField.getText().isEmpty()) && !(weightField.getText().isEmpty()) && !(stockField.getText().isEmpty()))
                JOptionPane.showMessageDialog(this, "Novo produto adicionado com êxito:\n" + novoProduto, "Novo produto", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(this, "Existem campos vazios e/ou inválidos. Por favor, preencha-os adequadamente", "Erro ao adicionar o produto", JOptionPane.ERROR_MESSAGE);

        }

        this.resetarCampos();

        // TODO IMPLEMENTAR LÓGICA AO ADICIONAR O PRODUTO


    }
    
    private void removerProduto(){
        // TODO IMPLEMENTAR LÓGICA AO REMOVER PRODUTO

    }

    //  TODO IMPLEMENTAR LÓGICA PARA IMPORTAR DADOS DE FONTES EXTERNAS (ARQUIVOS .csv OU BANCO DE DADOS)
    private void importarDados(){


    }
    
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getShowButton() {
        return importButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getWeightField() {
        return weightField;
    }

    public JTextField getStockField() {
        return stockField;
    }

}
