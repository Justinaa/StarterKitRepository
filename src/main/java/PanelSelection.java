import javax.swing.*;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelSelection extends JPanel {

	private ButtonGroup group;
	private JRadioButton selectedRadioButton = null;
	private JButton buttonConfirm;
	
	private ListenerSelection listenerSelection;
	
	private ActionListener listenerRadioButton = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			selectedRadioButton = (JRadioButton) event.getSource();
		}
	};
	
	
	public PanelSelection(ListenerSelection listenerSelection) {
		super();
	
		this.listenerSelection = listenerSelection;
		this.listenerSelection.setPanelSelection(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		verticalGlue();
		
		//---panelBottonGroup--------------------------------------------------------------
		JPanel panelBottonGroup = new JPanel();
		panelBottonGroup.setLayout(new BoxLayout(panelBottonGroup, BoxLayout.Y_AXIS));
			
		group = new ButtonGroup();
		
		//---przykład----------------------------------------------------
		JRadioButton radioButton1 = new JRadioButton("Kandydat 1",false);
		radioButton1.addActionListener(listenerRadioButton);
		group.add(radioButton1);
		
		JRadioButton radioButton2 = new JRadioButton("Kandydat 2",false); 
		radioButton2.addActionListener(listenerRadioButton);
		group.add(radioButton2);
		
		JRadioButton radioButton3 = new JRadioButton("Kandydat 3",false); 
		radioButton3.addActionListener(listenerRadioButton);
		group.add(radioButton3);
		//---------------------------------------------------------------	
		
		panelBottonGroup.add(radioButton1);
		panelBottonGroup.add(radioButton2);
		panelBottonGroup.add(radioButton3);
				
		add(panelBottonGroup);
		//---------------------------------------------------------------------------------
				
		verticalGlue();
		
		//---panelButton-------------------------------------------------------------------
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER,30,30));
				
		buttonConfirm = new JButton("Zatwierdź");
		buttonConfirm.addActionListener(this.listenerSelection);
		panelButton.add(buttonConfirm);
				
		add(panelButton);
		//---------------------------------------------------------------------------------	
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
	
	
	public JRadioButton getSelectedRadioButton() {
		return selectedRadioButton;
	}
	
}
