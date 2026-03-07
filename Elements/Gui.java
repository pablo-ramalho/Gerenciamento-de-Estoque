package Elements;
import javax.swing.JFrame;

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

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
    private Color corBotao;

    private JPanel textFieldSection;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField weightField;
    private JTextField stockField;

    private JPanel productGridSection;
    private JScrollPane scroller;
    
    public Gui(){
        gbc = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        fonteBotao = new Font("Arial", Font.BOLD, 36);
        fonteCampo = new Font("Arial", Font.PLAIN, 24);
        corBotao = new Color(64, 224, 208);

        setSize(1520, 1000);
        setLayout(new BorderLayout());
        setTitle("Gerenciador de estoque");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //  CONFIGURAÇÃO DOS PAINÉIS
        leftPanel = new JPanel(new GridLayout(2, 1));
        rightPanel = new JPanel(new BorderLayout());

        buttonSection = new JPanel(gridBagLayout);
        buttonSection.setBackground(Color.CYAN);
        gbc.weightx = 0.25;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        leftPanel.add(buttonSection, BorderLayout.NORTH);
        
        textFieldSection = new JPanel(gridBagLayout);
        textFieldSection.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 2));
        textFieldSection.setBackground(Color.GRAY);
        gbc.weightx = 0.25;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;

        leftPanel.add(textFieldSection, BorderLayout.CENTER);
        
        // TODO Fazer a tabela no painel direito
        productGridSection = new JPanel(new FlowLayout());
        productGridSection.setBackground(Color.BLACK);
        gbc.weightx = 0.75;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        scroller = new JScrollPane();

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

        // TRATAMENTO DE EVENTOS
        addButton.addActionListener(e -> {
            System.out.println("Adicionar clicado");

        });

        removeButton.addActionListener(e -> {
           System.out.println("Remover clicado");
            
        });

        importButton.addActionListener(e -> {
            System.out.println("Exibir clicado");

        });

        //add(productGridSection, BorderLayout.CENTER);
        
        setVisible(true);

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

    public void configurarFocoDoCampo(String placeHolder, JTextField campo){
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
