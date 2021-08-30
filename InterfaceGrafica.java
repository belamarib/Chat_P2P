package lista3;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;

public class InterfaceGrafica implements ActionListener, KeyListener {
	
    private JButton enviarButton;
    private JTextField textField;
    private JPanel panel;
    private JButton limparButton;
    private JTextArea textArea;
    private Escrita1 escrita1;
    private Escrita2 escrita2;
    private int contador;
    private JLabel nomeLabel;
    private long tempo;
    private boolean inTime;

    public InterfaceGrafica(Escrita1 esc1, Escrita2 esc2, String nome) {
    	
    	nomeLabel = new JLabel();
		nomeLabel.setText(nome);
		nomeLabel.setBounds(20, 10, 100, 30);
    	
    	escrita1 = esc1;
    	escrita2 = esc2;
    	
    	enviarButton = new JButton("Enviar");
		enviarButton.addActionListener(this);
		enviarButton.setActionCommand("enviar");
		enviarButton.setBounds(320, 450, 80, 30);
		
		
		limparButton = new JButton("Limpar");
		limparButton.addActionListener(this);
		limparButton.setActionCommand("limpar");
		limparButton.setBounds(420, 450, 80, 30);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 60, 500, 350);
		
		textField = new JTextField();
		textField.setVisible(true);
		textField.addActionListener(this);
		textField.setEditable(true);
		textField.setBounds(10, 440, 300, 50);
		textField.addKeyListener(this);
    	
    	panel = new JPanel(null);
    	panel.add(enviarButton);
		panel.add(limparButton);
		panel.add(textField);
		panel.add(textArea);
		panel.add(nomeLabel);
		
        JFrame janela = new JFrame();
        janela.setContentPane(panel);
        janela.setVisible(true);
        janela.pack();
        janela.setSize(540,540);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("enviar")) {
			if (escrita1 == null) {
				String content = textField.getText();
					Timestamp hora = new Timestamp(System.currentTimeMillis());
					String hour = hora.toString();
					this.contador++;
		            textArea.append(this.contador+"- eu: "+content+" "+hour+"\n");
		            textField.setText("");
		            escrita2.send(this.contador+"- Pessoa: "+content+" "+hour);
			} else {
				String content = textField.getText();
					Timestamp hora = new Timestamp(System.currentTimeMillis());
					String hour = hora.toString();
					this.contador++;
					textArea.append(this.contador+"- eu: "+content+" "+hour+"\n");
		            textField.setText("");
		            escrita1.send(this.contador+"- Pessoa: "+content+" "+hour);
			
			}
		} else if (command.equals("limpar")) {
			textArea.setText("");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			if (escrita1 == null) {
				String content = textField.getText();
					Timestamp hora = new Timestamp(System.currentTimeMillis());
					String hour = hora.toString();
					this.contador++;
					tempo = System.currentTimeMillis();
		            textArea.append(this.contador+"- eu: "+content+" "+hour+"\n");
		            textField.setText("");
		            escrita2.send(this.contador+"- Pessoa: "+content+" "+hour);
		
			} else {
				String content = textField.getText();
					Timestamp hora = new Timestamp(System.currentTimeMillis());
					String hour = hora.toString();
					this.contador++;
					textArea.append(this.contador+"- eu: "+content+" "+hour+"\n");
		            textField.setText("");
		            escrita1.send(this.contador+"- Pessoa: "+content+" "+hour);
			}
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void getResposta(String msg) {
		textArea.append(msg+"\n");
	}
	
	

}

