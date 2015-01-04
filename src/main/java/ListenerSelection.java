import java.awt.event.*;
import javax.swing.*;


public class ListenerSelection implements ActionListener {

	private FrameElections frameElections;
	private PanelSelection panelSelection;
	
	private JRadioButton selectedRadioButton;
	
	
	public ListenerSelection(FrameElections frameElections) {
		this.frameElections = frameElections;	
	}
	
	
	public void actionPerformed(ActionEvent event) {			
		selectedRadioButton = panelSelection.getSelectedRadioButton();
		
		if(selectedRadioButton != null) {
			String candidate = selectedRadioButton.getText();
			JOptionPane.showMessageDialog(null,"Został wybrany kandydat "+candidate);
			
			Elections elections = frameElections.getElections();
			elections.setCandidate(candidate);
			
			frameElections.dispose();
		} else {
			JOptionPane.showMessageDialog(null,"Zaznacz kandydata!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void setPanelSelection(PanelSelection panelSelection) {
		this.panelSelection = panelSelection;
	}
	
}
