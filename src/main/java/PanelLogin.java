import javax.swing.*;

//import javax.swing.text.MaskFormatter;
import java.awt.*; 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;


public class PanelLogin extends JPanel {

	private int width;
	private int height;
	
	private JLabel label1, label2;
	private JButton buttonLogin;
	private JComboBox<String> comboBox;
	private JTextField textField;
	
	private ListenerLogin listenerLogin;
	
	
	public PanelLogin(int width, int height, ListenerLogin listenerLogin) throws ParseException {
		super();
		
		this.width = width;
		this.height = height;
		
		this.listenerLogin = listenerLogin;
		this.listenerLogin.setPanelLogin(this);
		
		setSize(this.width,this.height);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//---panelZipCode----------------------------------------------------------
		JPanel panelZipCode = new JPanel(new FlowLayout(FlowLayout.CENTER,30,30));
		
		label1 = new JLabel("Kod pocztowy");
		panelZipCode.add(label1);
		
		comboBox = new JComboBox<String>();
		//---przykład----------------------------
		comboBox.addItem("52-310");
		comboBox.addItem("52-320");
		comboBox.addItem("52-330");
		comboBox.addItem("52-340");
		//---------------------------------------	
		panelZipCode.add(comboBox);
		
		add(panelZipCode);
		//-------------------------------------------------------------------------
		
		verticalGlue();

		//---panelPesel------------------------------------------------------------
		JPanel panelPesel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,10));
		
		label2 = new JLabel("PESEL");
		panelPesel.add(label2);
		
		//-----------------------------------------------------------
		//MaskFormatter formatter = new MaskFormatter("###########");
		//formatter.setPlaceholderCharacter('0');
		//-----------------------------------------------------------
		//MaskFormatter formatter = new MaskFormatter("***********");
		//formatter.setValidCharacters(" 0123456789");
		//-----------------------------------------------------------
		//textField = new JFormattedTextField(formatter);
		//-----------------------------------------------------------
		
		textField = new JTextField();
		textField.setColumns(11);	
		textField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent event) {
	            	letTypeOnlyDigit(event);
	            }
	    });
		panelPesel.add(textField);
		
		add(panelPesel);
		//-------------------------------------------------------------------------
		
		verticalGlue();

		//---panelButton-----------------------------------------------------------
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		
		buttonLogin = new JButton("Login");		
		buttonLogin.addActionListener(this.listenerLogin);
		panelButton.add(buttonLogin);
		
		add(panelButton);
		//-------------------------------------------------------------------------
	}
	
	
	private void verticalGlue() {
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
	}
	
	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	
	public JTextField getTextField() {
		return textField;
	}
	
	
	private void letTypeOnlyDigit(KeyEvent event) {
		char character = event.getKeyChar();
		
		if (!((character >= '0') && (character <= '9'))) {
			event.consume();
		}
	}
	
}
